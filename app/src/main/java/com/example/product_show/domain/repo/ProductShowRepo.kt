package com.example.product_show.domain.repo

import com.example.product_show.domain.model.Page
import com.example.product_show.domain.model.Product

interface ProductShowRepo {
    suspend fun getProductList(pageSize: Int, skip: Int, searchQuery: String): Page<Product>
}