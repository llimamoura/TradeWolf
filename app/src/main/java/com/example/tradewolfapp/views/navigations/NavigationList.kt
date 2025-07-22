
package com.example.tradewolfapp.views.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import com.example.tradewolfapp.model.NavigationItem

val navigationItems = listOf(
    NavigationItem(
        title = "Home",
        icon = Icons.Default.Home,
        route = Screens.Home.rout
    ),
    NavigationItem(
        title = "Markets",
        icon = Icons.Default.ShoppingCart,
        route = Screens.Markets.rout
    ),
    NavigationItem(
        icon = Icons.Default.AddCircle,
        route = Screens.Transaction.rout,
        isCustom = true
    ),
    NavigationItem(
        title = "Wallets",
        icon = Icons.Default.Info,
        route = Screens.Wallets.rout
    ),
    NavigationItem(
        title = "Profile",
        icon = Icons.Default.Person,
        route = Screens.Profile.rout
    )
)
