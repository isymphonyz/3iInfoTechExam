package com.isymphonyz.a3iinfotech.ui.main.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.isymphonyz.a3iinfotech.data.model.ProductModel
import com.isymphonyz.a3iinfotech.data.repository.ProductDetailRepository
import com.isymphonyz.a3iinfotech.data.repository.ProductsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import isymphonyz.testmvvm.utils.Resource

class ProductDetailViewModel(private val repository: ProductDetailRepository) : ViewModel() {
    private val productDetail = MutableLiveData<Resource<ProductModel>>()
    private val compositeDisposable = CompositeDisposable()
    private var productId = 0

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun fetchProductDetail() {
        productDetail.postValue(Resource.loading(null))
        compositeDisposable.add(
            repository.getProductDetail(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    productDetail.postValue(Resource.success(response))
                }, { throwable ->
                    productDetail.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    fun getProductDetail(): LiveData<Resource<ProductModel>> {
        return productDetail
    }

    fun setProductId(productId: Int) {
        this.productId = productId
    }
}