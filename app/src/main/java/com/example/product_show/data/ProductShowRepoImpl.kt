package com.example.product_show.data

import com.example.product_show.data.mapper.toModel
import com.example.product_show.data.network.repo.ProductShowNetworkDataSource
import com.example.product_show.domain.model.Page
import com.example.product_show.domain.model.Product
import com.example.product_show.domain.repo.ProductShowRepo
import javax.inject.Inject

class ProductShowRepoImpl @Inject constructor(private val networkDataSource: ProductShowNetworkDataSource) :
    ProductShowRepo {
    override suspend fun getProductList(
        pageSize: Int, skip: Int, searchQuery: String
    ): Page<Product> {
        val response = if (searchQuery.isEmpty()) {
            networkDataSource.getProductList(limit = pageSize, skip = skip)
        } else {
            networkDataSource.getProductList(limit = pageSize, skip = skip)
        }

        val productList = response?.products?.toModel() ?: emptyList()
        val nextSkip = if (productList.isEmpty()) null else {
            (response?.skip ?: 0) + (response?.limit ?: 0)
        }
        return Page(items = productList, nextPage = nextSkip)
    }
}