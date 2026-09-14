package com.example.product_show.data.network.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("thumbnail")
    val thumbnail: String,
)
