package com.example.product_show.ui.utils

import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems

inline fun <T : Any> LazyStaggeredGridScope.optimizedLazyList(
    items: LazyPagingItems<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyStaggeredGridItemScope.(index: Int, item: T) -> Unit
) {
    items(
        count = items.itemCount,
        key = if (key != null) { index -> items[index]?.let(key) as Any } else null,
        contentType = { index -> items[index]?.let(contentType) }
    ) { index ->
        items[index]?.let { item ->
            itemContent(index, item)
        }
    }
}
