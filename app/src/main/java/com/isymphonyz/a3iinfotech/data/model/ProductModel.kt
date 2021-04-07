package com.isymphonyz.a3iinfotech.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductModel (
    @SerializedName("id") val id: Int = 0,
    @SerializedName("title") val title: String = "",
    @SerializedName("image") val image: String = "",
    @SerializedName("content") val content: String = "",
    @SerializedName("isNewProduct") val isNewProduct: Boolean = false,
    @SerializedName("price") val price: Double = 0.0
) : Parcelable