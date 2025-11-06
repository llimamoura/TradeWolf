package com.example.tradewolfapp.model

data class NavigationItem(
    val title: String? = null,
    val icon: Painter,
    val route: String,
    val isCustom: Boolean = false
)