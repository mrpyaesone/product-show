package com.example.product_show.data.network.repo

import com.example.product_show.data.network.response.ProductListResponse
import com.example.product_show.data.network.response.ProductResponse

interface ProductShowNetworkDataSource {
    suspend fun getProductList(limit: Int, skip: Int): ProductListResponse?

    suspend fun searchProductList(limit: Int, skip: Int, q: String): ProductListResponse?

    suspend fun getProductDetail(id: Int): ProductResponse?
}