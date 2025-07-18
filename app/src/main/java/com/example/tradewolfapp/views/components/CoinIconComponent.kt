package com.example.tradewolfapp.views.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun CoinIcon(
    iconUrl: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = iconUrl,
        contentDescription = null,
        modifier = modifier.clip(CircleShape),
        contentScale = ContentScale.Crop,

        )
}

