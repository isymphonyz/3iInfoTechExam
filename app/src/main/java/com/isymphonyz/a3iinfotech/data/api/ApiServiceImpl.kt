package com.isymphonyz.a3iinfotech.data.api

import com.isymphonyz.a3iinfotech.data.model.ProductModel
import com.isymphonyz.a3iinfotech.util.MyConfiguration
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {

    override fun getProducts() : Single<List<ProductModel>> {
        return Rx2AndroidNetworking.get(MyConfiguration.DOMAIN + MyConfiguration.END_POINT_PRODUCTS)
            .build()
            .getObjectListSingle(ProductModel::class.java)
    }

    override fun getProductDetail(productId: Int) : Single<ProductModel> {
        return Rx2AndroidNetworking.get(MyConfiguration.DOMAIN + MyConfiguration.END_POINT_PRODUCT_DETAIL)
            .addPathParameter("productId", productId.toString())
            .build()
            .getObjectSingle(ProductModel::class.java)
    }


}