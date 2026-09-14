package com.example.product_show.ui.productlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.product_show.domain.model.Product
import com.example.product_show.ui.components.EmptyView
import com.example.product_show.ui.components.ErrorView
import com.example.product_show.ui.components.LoadingContent
import com.example.product_show.ui.utils.optimizedLazyList

@Composable
internal fun ProductListScreen(
    onNavToDetail: (id: Int) -> Unit,
    viewModel: ProductListViewModel = hiltViewModel()
) {
    val products = viewModel.products.collectAsLazyPagingItems()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()

    ProductListContent(
        searchText = searchText,
        onSearchProduct = viewModel::searchProduct,
        products = products,
        onItemClick = onNavToDetail
    )
}

@Composable
private fun ProductListContent(
    searchText: String, onSearchProduct: (String) -> Unit,
    products: LazyPagingItems<Product>, onItemClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(topBar = {}) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            // search bar
            OutlinedTextField(
                value = searchText,
                onValueChange = onSearchProduct,
                placeholder = { Text("Search Product...") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardActions = KeyboardActions(onSearch = {}),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            // product list view
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (val refreshState = products.loadState.refresh) {
                    is LoadState.Loading -> {
                        LoadingContent()
                    }

                    is LoadState.Error -> {
                        ErrorView(
                            errorMessage = refreshState.error.message ?: "Something went wrong.",
                            onRetry = { products.retry() },
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    is LoadState.NotLoading -> {
                        if (products.itemCount == 0) {
                            EmptyView(modifier = Modifier.fillMaxSize())
                        } else {
                            ProductListView(productList = products, onItemClick = onItemClick)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductListView(productList: LazyPagingItems<Product>, onItemClick: (id: Int) -> Unit) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
    ) {
        optimizedLazyList(items = productList) { _, product ->
            ProductShowItem(
                product = product, onClick = onItemClick
            )
        }
    }
}