package com.example.tradewolfapp.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconButtonComponent(
    onClick: () -> Unit,
    icon: Int,
    contentDescription: String,
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = Color.White)
            .background(Color.White, shape = RoundedCornerShape(12.dp))

    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "IconButton"
        )


    }
}
