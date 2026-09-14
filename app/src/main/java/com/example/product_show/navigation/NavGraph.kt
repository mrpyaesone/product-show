package com.example.product_show.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.product_show.ui.productlist.productListDestination

internal fun NavGraphBuilder.navGraph(
    navController: NavHostController
) {
    productListDestination(navController, onNavToDetail = {})
}