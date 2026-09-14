package com.example.product_show.data

import com.example.product_show.data.network.repo.ProductShowNetworkDataSource
import com.example.product_show.data.network.response.ProductListResponse
import com.example.product_show.data.network.response.ProductResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductShowRepoImplTest {

    private lateinit var networkDataSource: ProductShowNetworkDataSource
    private lateinit var repository: ProductShowRepoImpl

    @Before
    fun setUp() {
        networkDataSource = mockk()
        repository = ProductShowRepoImpl(networkDataSource)
    }

    @Test
    fun `getProductList when searchQuery is empty calls getProductList on networkDataSource`() = runTest {
        // Given
        val pageSize = 20
        val skip = 0
        val query = ""
        val mockResponse = ProductListResponse(
            products = listOf(
                ProductResponse(1, "Product 1", "Desc", 10.0, 4.5, emptyList(), "")
            ),
            total = 1,
            skip = 0,
            limit = 20
        )
        coEvery { networkDataSource.getProductList(pageSize, skip) } returns mockResponse

        // When
        val result = repository.getProductList(pageSize, skip, query)

        // Then
        assertEquals(1, result.items.size)
        assertEquals("Product 1", result.items[0].title)
        assertEquals(20, result.nextPage)
        coVerify { networkDataSource.getProductList(pageSize, skip) }
    }

    @Test
    fun `getProductList when searchQuery is not empty calls searchProductList on networkDataSource`() = runTest {
        // Given
        val pageSize = 20
        val skip = 0
        val query = "phone"
        val mockResponse = ProductListResponse(
            products = listOf(
                ProductResponse(1, "Phone 1", "Desc", 10.0, 4.5, emptyList(), "")
            ),
            total = 1,
            skip = 0,
            limit = 20
        )
        coEvery { networkDataSource.searchProductList(pageSize, skip, query) } returns mockResponse

        // When
        val result = repository.getProductList(pageSize, skip, query)

        // Then
        assertEquals(1, result.items.size)
        assertEquals("Phone 1", result.items[0].title)
        coVerify { networkDataSource.searchProductList(pageSize, skip, query) }
    }

    @Test
    fun `getProductDetail calls getProductDetail on networkDataSource`() = runTest {
        // Given
        val productId = 1
        val mockResponse = ProductResponse(1, "Product 1", "Desc", 10.0, 4.5, emptyList(), "")
        coEvery { networkDataSource.getProductDetail(productId) } returns mockResponse

        // When
        val result = repository.getProductDetail(productId)

        // Then
        assertEquals(1, result.id)
        assertEquals("Product 1", result.title)
        coVerify { networkDataSource.getProductDetail(productId) }
    }
}
