package com.example.tradewolfapp.views.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tradewolfapp.R

@Composable
fun IconButtonComponent(
    icon: Int,
) {
    Card (
        Modifier.height(55.dp).fillMaxWidth(),
        border = BorderStroke(color = Color.Gray, width = 1.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "IconButton"
            )
        }

    }
}

@Preview
@Composable
fun IconButtonComponentPreview() {
    IconButtonComponent(icon = R.drawable.google_logo)
}