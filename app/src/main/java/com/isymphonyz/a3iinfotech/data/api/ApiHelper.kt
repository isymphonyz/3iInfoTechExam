package com.isymphonyz.a3iinfotech.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getProducts() = apiService.getProducts()

    fun getProductDetail(productId: Int) = apiService.getProductDetail(productId)

}