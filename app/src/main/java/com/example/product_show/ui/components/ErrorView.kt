package com.example.product_show.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.product_show.R
import com.example.product_show.ui.theme.ProductShowTheme

@Composable
internal fun ErrorView(
    errorMessage: String, onRetry: () -> Unit, modifier: Modifier = Modifier.fillMaxSize()
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(R.drawable.img_error),
            contentDescription = null,
            modifier = Modifier
                .padding(48.dp)
                .size(176.dp)
        )
        Text(errorMessage, style = typography.bodyLarge, textAlign = TextAlign.Center)

        RectangularButton(
            text = "Try again", textStyle = typography.bodyMedium,
            onClick = onRetry, enable = true,
            enableBgColor = colorScheme.primary,
            enableTxtColor = colorScheme.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        )
    }
}

@Preview
@Composable
private fun ErrorViewPreview() {
    ProductShowTheme {
        Surface {
            ErrorView(errorMessage = "Something went wrong", onRetry = {})
        }
    }
}