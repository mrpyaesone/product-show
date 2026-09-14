package com.example.product_show

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.product_show.navigation.ProductShowNavHost
import com.example.product_show.ui.theme.ProductShowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            ProductShowTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProductShowNavHost(navController = navController)
                }
            }
        }
    }
}