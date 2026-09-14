package com.example.product_show.data.network.api

import com.example.product_show.data.network.response.ProductListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductShowApi {
    @GET("products")
    suspend fun getProductList(
        @Query("limit") limit: Int, @Query("skip") skip: Int,
    ): ProductListResponse?
}