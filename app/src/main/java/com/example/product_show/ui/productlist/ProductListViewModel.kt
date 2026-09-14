package com.example.product_show.ui.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.product_show.domain.model.Product
import com.example.product_show.domain.usecase.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class ProductListViewModel @Inject constructor(private val getProductListUseCase: GetProductListUseCase) :
    ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val products: Flow<PagingData<Product>> =
        _searchText
            .debounce(300.milliseconds)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                Pager(
                    config = PagingConfig(
                        initialLoadSize = 20,
                        pageSize = 20,
                        enablePlaceholders = false,
                        prefetchDistance = 10,
                    ),
                    pagingSourceFactory = {
                        ProductPagingSource(searchInput = query, getProductListUseCase)
                    }
                ).flow.cachedIn(viewModelScope)
            }

    fun searchProduct(input: String) {
        _searchText.update { input }
    }
}