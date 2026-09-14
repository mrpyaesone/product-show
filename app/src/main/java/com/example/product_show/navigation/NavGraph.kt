package com.example.product_show.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.product_show.ui.productdetail.navToProductDetail
import com.example.product_show.ui.productdetail.productDetailDestination
import com.example.product_show.ui.productlist.productListDestination

internal fun NavGraphBuilder.navGraph(
    navController: NavHostController
) {
    productListDestination(navController, onNavToDetail = navController::navToProductDetail)
    productDetailDestination(navController, onNavToBack = navController::navigateUp)
}