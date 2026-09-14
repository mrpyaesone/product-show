package com.example.product_show.ui.productlist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.product_show.domain.model.Product
import com.example.product_show.domain.usecase.GetProductListUseCase

class ProductPagingSource(
    private val searchInput: String,
    private val getProductListUseCase: GetProductListUseCase
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: 1

        return try {
            val result = getProductListUseCase(
                skip = page, pageSize = params.loadSize, searchQuery = searchInput
            )

            LoadResult.Page(
                data = result.items,
                prevKey = if (page == 1) {
                    null
                } else {
                    page - 1
                },
                nextKey = result.nextPage
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { position ->

            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}