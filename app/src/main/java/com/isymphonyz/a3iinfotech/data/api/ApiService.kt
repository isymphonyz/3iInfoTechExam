package com.isymphonyz.a3iinfotech.data.api

import com.isymphonyz.a3iinfotech.data.model.ProductModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Single

interface ApiService {

    fun getProducts() : Single<List<ProductModel>>

    fun getProductDetail(productId: Int) : Single<ProductModel>

}