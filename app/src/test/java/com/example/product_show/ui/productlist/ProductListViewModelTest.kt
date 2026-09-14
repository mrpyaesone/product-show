package com.example.product_show.ui.productlist

import app.cash.turbine.test
import com.example.product_show.domain.usecase.GetProductListUseCase
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {

    private lateinit var getProductListUseCase: GetProductListUseCase
    private lateinit var viewModel: ProductListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getProductListUseCase = mockk()
        viewModel = ProductListViewModel(getProductListUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchProduct updates searchText flow`() = runTest {
        val query = "phone"
        viewModel.searchText.test {
            assertEquals("", awaitItem())
            viewModel.searchProduct(query)
            assertEquals(query, awaitItem())
        }
    }
}
