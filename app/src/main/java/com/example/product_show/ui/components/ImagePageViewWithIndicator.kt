package com.example.product_show.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.product_show.ui.theme.ProductShowTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePageViewWithIndicator(
    imageUrls: List<String>,
    modifier: Modifier = Modifier.height(350.dp)
) {
    val pagerState = rememberPagerState(pageCount = { imageUrls.size })

    Box(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            SubcomposeAsyncImage(
                model =
                    ImageRequest.Builder(LocalContext.current).data(imageUrls[page])
                        .crossfade(enable = true).build(),
                loading = {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                },
                contentDescription = null, contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .background(Color.Black.copy(alpha = 0.3f), shape = CircleShape)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(imageUrls.size) { index ->
                val isSelected = pagerState.currentPage == index

                Box(
                    modifier = Modifier
                        .size(if (isSelected) 10.dp else 8.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) Color.White else Color.White.copy(alpha = 0.5f)
                        )
                )
            }
        }
    }
}

@Preview
@Composable
private fun ImagePageViewWithIndicatorPreview() {
    ProductShowTheme {
        Surface {
            ImagePageViewWithIndicator(imageUrls = List(10) { "" })
        }
    }
}