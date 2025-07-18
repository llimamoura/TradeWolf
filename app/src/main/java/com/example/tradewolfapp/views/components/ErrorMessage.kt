package com.example.tradewolfapp.views.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
 fun ErrorMessage(message: String) {
    Text(text = message, color = Color.Red)
    Button(onClick = { /* Resetar estado */ }) {
        Text("Tentar novamente")
    }
}