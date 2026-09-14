package com.example.product_show.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.product_show.ui.theme.PreviewForBothTheme
import com.example.product_show.ui.theme.ProductShowTheme

@Composable
internal fun LoadingContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = colorScheme.primary)
    }
}

@PreviewForBothTheme
@Composable
private fun LoadingContentPreview() {
    ProductShowTheme { Surface { LoadingContent() } }
}