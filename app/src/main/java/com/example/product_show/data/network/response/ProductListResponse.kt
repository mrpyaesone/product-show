package com.example.product_show.data.network.response

import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    @SerializedName("products")
    val products: List<ProductResponse>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int
)
