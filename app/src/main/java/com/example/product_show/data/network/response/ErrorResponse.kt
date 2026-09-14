package com.example.product_show.data.network.response

import com.google.gson.annotations.SerializedName

// I did not find out error response, so this one is for just template
data class ErrorResponse(
    @SerializedName("code") val code: String?,
    @SerializedName("message") val message: String?
)