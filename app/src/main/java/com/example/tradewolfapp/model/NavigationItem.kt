package com.example.tradewolfapp.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String? = null,
    val icon: ImageVector,
    val route: String,
    val isCustom: Boolean = false
)