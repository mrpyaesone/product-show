package com.example.product_show.data.network.repo

import com.example.product_show.data.network.response.ProductListResponse

interface ProductShowNetworkDataSource {
    suspend fun getProductList(limit: Int, skip: Int): ProductListResponse?
}