package com.example.product_show.ui.productdetail

import app.cash.turbine.test
import com.example.product_show.domain.model.Product
import com.example.product_show.domain.usecase.GetProductDetailUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductDetailViewModelTest {

    private lateinit var getProductDetailUseCase: GetProductDetailUseCase
    private lateinit var viewModel: ProductDetailViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getProductDetailUseCase = mockk()
        viewModel = ProductDetailViewModel(getProductDetailUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getProductDetail when success updates uiState to Success`() = runTest {
        // Given
        val productId = 1
        val product = Product.mock
        coEvery { getProductDetailUseCase(productId) } returns Result.success(product)

        // When & Then
        viewModel.uiState.test {
            assertEquals(ProductDetailUiState.Loading, awaitItem())
            
            viewModel.getProductDetail(productId)
            
            // Success state
            val result = awaitItem()
            assertTrue(result is ProductDetailUiState.Success)
            assertEquals(product, (result as ProductDetailUiState.Success).product)
        }
    }

    @Test
    fun `getProductDetail when failure updates uiState to Error`() = runTest {
        // Given
        val productId = 1
        val errorMessage = "Error message"
        coEvery { getProductDetailUseCase(productId) } returns Result.failure(RuntimeException(errorMessage))

        // When & Then
        viewModel.uiState.test {
            assertEquals(ProductDetailUiState.Loading, awaitItem())
            
            viewModel.getProductDetail(productId)
            
            // Error state
            val result = awaitItem()
            assertTrue(result is ProductDetailUiState.Error)
            assertEquals(errorMessage, (result as ProductDetailUiState.Error).message)
        }
    }
}
