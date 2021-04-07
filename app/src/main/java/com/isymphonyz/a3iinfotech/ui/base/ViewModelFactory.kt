package com.isymphonyz.a3iinfotech.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.isymphonyz.a3iinfotech.data.api.ApiHelper
import com.isymphonyz.a3iinfotech.data.repository.ProductDetailRepository
import com.isymphonyz.a3iinfotech.data.repository.ProductsRepository
import com.isymphonyz.a3iinfotech.ui.main.productdetail.ProductDetailViewModel
import com.isymphonyz.a3iinfotech.ui.main.products.ProductsViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            return ProductsViewModel(ProductsRepository(apiHelper)) as T
        } else if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
            return ProductDetailViewModel(ProductDetailRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}