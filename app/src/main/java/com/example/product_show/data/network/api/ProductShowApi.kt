package com.example.product_show.data.network.api

import com.example.product_show.data.network.response.ProductListResponse
import com.example.product_show.data.network.response.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductShowApi {
    @GET("products")
    suspend fun getProductList(
        @Query("limit") limit: Int, @Query("skip") skip: Int,
    ): ProductListResponse?

    @GET("products/search")
    suspend fun searchProductList(
        @Query("limit") limit: Int, @Query("skip") skip: Int, @Query("q") q: String,
    ): ProductListResponse?

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") id: Int): ProductResponse?
}