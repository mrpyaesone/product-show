package com.example.product_show.ui.productdetail

import com.example.product_show.domain.model.Product

sealed interface ProductDetailUiState {
    object Loading : ProductDetailUiState
    data class Success(val product: Product) : ProductDetailUiState
    data class Error(val message: String) : ProductDetailUiState
}