package com.example.product_show.ui.productdetail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
internal data class ProductDetailRoute(@Serializable val productId: Int)

internal fun NavGraphBuilder.productDetailDestination(
    navController: NavHostController, onNavToBack: () -> Unit,
) {
    composable<ProductDetailRoute> { backStackEntry ->
        val args = backStackEntry.toRoute<ProductDetailRoute>()

        ProductDetailScreen(
            productId = args.productId,
            canNavBack = navController.previousBackStackEntry != null, onNavBack = onNavToBack,
        )
    }
}

fun NavHostController.navToProductDetail(productId: Int) {
    navigate(ProductDetailRoute(productId = productId))
}