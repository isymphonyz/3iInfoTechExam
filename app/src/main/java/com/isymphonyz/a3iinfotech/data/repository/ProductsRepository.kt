package com.isymphonyz.a3iinfotech.data.repository

import com.isymphonyz.a3iinfotech.data.api.ApiHelper
import com.isymphonyz.a3iinfotech.data.model.ProductModel
import io.reactivex.Single

class ProductsRepository(private val apiHelper: ApiHelper) {

    fun getProducts() : Single<List<ProductModel>> {
        return apiHelper.getProducts()
    }

}