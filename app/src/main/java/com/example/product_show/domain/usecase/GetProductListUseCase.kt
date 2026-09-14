package com.example.product_show.domain.usecase

import com.example.product_show.domain.dispatcher.DispatcherProvider
import com.example.product_show.domain.model.Page
import com.example.product_show.domain.model.Product
import com.example.product_show.domain.repo.ProductShowRepo
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productShowRepo: ProductShowRepo, private val dispatcherProvider: DispatcherProvider
) {
    suspend operator fun invoke(
        pageSize: Int = 20, skip: Int = 0, searchQuery: String
    ): Page<Product> =
        withContext(dispatcherProvider.io) {
            productShowRepo.getProductList(
                pageSize = pageSize, skip = skip, searchQuery = searchQuery
            )
        }
}