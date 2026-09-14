package com.example.product_show.ui.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.product_show.domain.model.Product
import com.example.product_show.ui.components.ErrorView
import com.example.product_show.ui.components.ImagePageViewWithIndicator
import com.example.product_show.ui.components.LoadingContent
import com.example.product_show.ui.theme.PreviewForBothTheme
import com.example.product_show.ui.theme.ProductShowTheme

@Composable
internal fun ProductDetailScreen(
    productId: Int,
    canNavBack: Boolean, onNavBack: () -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getProductDetail(productId = productId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductDetailContent(
        uiState = uiState, canNavBack = canNavBack, onNavBack = onNavBack,
        onRetry = { viewModel.getProductDetail(productId = productId) })
}

@Composable
private fun ProductDetailContent(
    uiState: ProductDetailUiState, canNavBack: Boolean = false, onNavBack: () -> Unit,
    onRetry: () -> Unit, modifier: Modifier = Modifier
) {
    Scaffold(topBar = {}) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            when (uiState) {
                is ProductDetailUiState.Error -> ErrorView(
                    errorMessage = uiState.message, onRetry = onRetry,
                    modifier = Modifier.fillMaxSize()
                )

                ProductDetailUiState.Loading -> LoadingContent()
                is ProductDetailUiState.Success -> ProductDetailView(
                    product = uiState.product,
                    canNavBack = canNavBack, onNavBack = onNavBack
                )
            }
        }
    }
}

@Composable
private fun ProductDetailView(
    product: Product, canNavBack: Boolean = false, onNavBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .height(450.dp)
                .fillMaxWidth()
        ) {
            ImagePageViewWithIndicator(
                imageUrls = product.images, modifier = Modifier.fillMaxSize()
            )
            IconButton(
                onClick = { if (canNavBack) onNavBack() else Unit }, modifier = Modifier
                    .clip(CircleShape)
                    .background(colorScheme.primary)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back", tint = colorScheme.onPrimary
                )
            }
        }


        Text(
            product.title,
            style = typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            "$${product.price}",
            style = typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Star, contentDescription = null,
                tint = colorScheme.primaryContainer, modifier = Modifier.size(24.dp)
            )
            Text("${product.rating}", modifier = Modifier.padding(horizontal = 2.dp))
        }
        Text(
            product.description, style = typography.bodyMedium,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}

@PreviewForBothTheme
@Composable
private fun ProductDetailContentPreview() {
    ProductShowTheme {
        Surface {
            ProductDetailContent(
                uiState = ProductDetailUiState.Success(Product.mock),
                canNavBack = true, onNavBack = {}, onRetry = {})
        }
    }
}