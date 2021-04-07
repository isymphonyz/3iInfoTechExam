package com.isymphonyz.a3iinfotech.ui.main.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.isymphonyz.a3iinfotech.data.model.ProductModel
import com.isymphonyz.a3iinfotech.data.repository.ProductsRepository
import com.isymphonyz.a3iinfotech.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import isymphonyz.testmvvm.utils.Resource

class ProductsViewModel(private val repository: ProductsRepository) : BaseViewModel() {
    private val products = MutableLiveData<Resource<List<ProductModel>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        products.postValue(Resource.loading(null))
        compositeDisposable.add(
            repository.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    products.postValue(Resource.success(response))
                }, { throwable ->
                    products.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getProducts(): LiveData<Resource<List<ProductModel>>> {
        return products
    }
}