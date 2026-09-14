package com.example.product_show.domain.usecase

import com.example.product_show.domain.dispatcher.DispatcherProvider
import com.example.product_show.domain.model.Product
import com.example.product_show.domain.repo.ProductShowRepo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetProductDetailUseCaseTest {

    private lateinit var repository: ProductShowRepo
    private lateinit var dispatcherProvider: DispatcherProvider
    private lateinit var useCase: GetProductDetailUseCase

    @Before
    fun setUp() {
        repository = mockk()
        dispatcherProvider = mockk {
            coEvery { io } returns Dispatchers.Unconfined
        }
        useCase = GetProductDetailUseCase(repository, dispatcherProvider)
    }

    @Test
    fun `invoke when success returns success result`() = runTest {
        // Given
        val productId = 1
        val expectedProduct = Product.mock
        coEvery { repository.getProductDetail(productId) } returns expectedProduct

        // When
        val result = useCase(productId)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedProduct, result.getOrNull())
    }

    @Test
    fun `invoke when failure returns failure result`() = runTest {
        // Given
        val productId = 1
        val exception = RuntimeException("Error")
        coEvery { repository.getProductDetail(productId) } throws exception

        // When
        val result = useCase(productId)

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
