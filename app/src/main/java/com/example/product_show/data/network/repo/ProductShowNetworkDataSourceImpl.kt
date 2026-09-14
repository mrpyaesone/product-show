package com.example.product_show.data.network.repo

import com.example.product_show.data.network.api.ProductShowApi
import com.example.product_show.data.network.di.NetworkErrorHandler
import com.example.product_show.data.network.response.ProductListResponse
import jakarta.inject.Inject

class ProductShowNetworkDataSourceImpl @Inject constructor(
    private val networkApi: ProductShowApi, private val networkErrorHandler: NetworkErrorHandler
) : ProductShowNetworkDataSource {
    override suspend fun getProductList(limit: Int, skip: Int): ProductListResponse? {
        return try {
            networkApi.getProductList(limit = limit, skip = skip)
        } catch (e: Exception) {
            throw networkErrorHandler.handleError(e)
        }
    }

}