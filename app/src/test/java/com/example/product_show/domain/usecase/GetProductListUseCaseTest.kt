package com.example.product_show.domain.usecase

import com.example.product_show.domain.dispatcher.DispatcherProvider
import com.example.product_show.domain.model.Page
import com.example.product_show.domain.model.Product
import com.example.product_show.domain.repo.ProductShowRepo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetProductListUseCaseTest {

    private lateinit var repository: ProductShowRepo
    private lateinit var dispatcherProvider: DispatcherProvider
    private lateinit var useCase: GetProductListUseCase

    @Before
    fun setUp() {
        repository = mockk()
        dispatcherProvider = mockk {
            coEvery { io } returns Dispatchers.Unconfined
        }
        useCase = GetProductListUseCase(repository, dispatcherProvider)
    }

    @Test
    fun `invoke calls repository getProductList and returns result`() = runTest {
        // Given
        val pageSize = 20
        val skip = 0
        val query = "test"
        val expectedPage = Page(items = listOf(Product.mock), nextPage = 20)
        coEvery { repository.getProductList(pageSize, skip, query) } returns expectedPage

        // When
        val result = useCase(pageSize, skip, query)

        // Then
        assertEquals(expectedPage, result)
    }
}
