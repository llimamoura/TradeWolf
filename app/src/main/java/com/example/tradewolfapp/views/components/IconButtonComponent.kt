package com.example.tradewolfapp.views.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp


@Composable
fun IconButtonComponent(
    onClick:  () -> Unit,
    icon: Int,
) {
    IconButton (
      onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(60.dp).background(color = Color.Black)

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
