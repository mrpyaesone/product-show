package com.example.product_show.domain.usecase

import com.example.product_show.domain.dispatcher.DispatcherProvider
import com.example.product_show.domain.model.Product
import com.example.product_show.domain.repo.ProductShowRepo
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val productShowRepo: ProductShowRepo, private val dispatcherProvider: DispatcherProvider
) {
    suspend operator fun invoke(productId: Int): Result<Product> =
        withContext(dispatcherProvider.io) {
            try {
                Result.success(productShowRepo.getProductDetail(productId = productId))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}