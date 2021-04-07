package com.isymphonyz.a3iinfotech.util

class MyConfiguration {
    companion object {
        const val DOMAIN = "https://ecommerce-product-app.herokuapp.com/"
        const val END_POINT_PRODUCTS = "products"
        const val END_POINT_PRODUCT_DETAIL = "$END_POINT_PRODUCTS/{productId}"
    }
}