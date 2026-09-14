package com.example.product_show.ui.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.product_show.domain.usecase.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val productDetailUseCase: GetProductDetailUseCase) :
    ViewModel() {
    private var _uiState = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getProductDetail(productId: Int) {
        viewModelScope.launch {
            _uiState.update { ProductDetailUiState.Loading }
            productDetailUseCase(productId = productId).onSuccess { data ->
                _uiState.update { ProductDetailUiState.Success(data) }
            }.onFailure { error ->
                _uiState.update { ProductDetailUiState.Error(error.message ?: "Unknown Error") }
            }
        }
    }
}