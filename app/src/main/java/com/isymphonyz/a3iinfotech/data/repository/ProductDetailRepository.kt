package com.isymphonyz.a3iinfotech.data.repository

import com.isymphonyz.a3iinfotech.data.api.ApiHelper
import com.isymphonyz.a3iinfotech.data.model.ProductModel
import io.reactivex.Single

class ProductDetailRepository(private val apiHelper: ApiHelper) {

    fun getProductDetail(productId: Int) : Single<ProductModel> {
        return apiHelper.getProductDetail(productId)
    }

}