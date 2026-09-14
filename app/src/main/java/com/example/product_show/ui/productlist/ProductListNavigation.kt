package com.example.product_show.ui.productlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object ProductListRoute

internal fun NavGraphBuilder.productListDestination(
    navController: NavHostController, onNavToDetail: (id: Int) -> Unit
) {
    composable<ProductListRoute> {
        ProductListScreen(onNavToDetail = onNavToDetail)
    }
}