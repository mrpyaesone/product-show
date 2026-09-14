package com.example.product_show.data.network.repo

import com.example.product_show.data.network.api.ProductShowApi
import com.example.product_show.data.network.di.NetworkErrorHandler
import com.example.product_show.data.network.response.ProductListResponse
import com.example.product_show.data.network.response.ProductResponse
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

    override suspend fun searchProductList(limit: Int, skip: Int, q: String): ProductListResponse? {
        return try {
            networkApi.searchProductList(limit = limit, skip = skip, q = q)
        } catch (e: Exception) {
            throw networkErrorHandler.handleError(e)
        }
    }

    override suspend fun getProductDetail(id: Int): ProductResponse? {
        return try {
            networkApi.getProductDetail(id = id)
        } catch (e: Exception) {
            throw networkErrorHandler.handleError(e)
        }
    }

}