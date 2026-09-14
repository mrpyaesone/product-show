package com.example.product_show.ui.productlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.product_show.domain.model.Product
import com.example.product_show.ui.theme.PreviewForBothTheme
import com.example.product_show.ui.theme.ProductShowTheme

@Composable
internal fun ProductShowItem(
    product: Product, onClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors().copy(containerColor = colorScheme.surface),
        border = BorderStroke(1.dp, color = colorScheme.outlineVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(size = 8.dp),
        modifier = modifier
            .clickable { onClick(product.id) }
            .padding(6.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            SubcomposeAsyncImage(
                model =
                    ImageRequest.Builder(LocalContext.current).data(product.thumbnail)
                        .crossfade(enable = true).build(),
                loading = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                },
                contentDescription = null, contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(size = 4.dp)),

                )

            Text(
                product.title, textAlign = TextAlign.Center,
                maxLines = 3, overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(12.dp)
            )
            Text(
                "$${product.price}",
                style = typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 12.dp)
            )

        }
    }
}

@PreviewForBothTheme
@Composable
private fun ProductShowItemPreview() {
    ProductShowTheme { Surface { ProductShowItem(product = Product.mock, onClick = {}) } }
}
