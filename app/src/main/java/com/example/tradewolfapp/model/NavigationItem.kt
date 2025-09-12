package com.example.tradewolfapp.model
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource


data class NavigationItem(
    val title: String? = null,
    val icon: Painter,
    val route: String,
    val isCustom: Boolean = false
)