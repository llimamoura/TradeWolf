
package com.example.tradewolfapp.views.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.tradewolfapp.model.NavigationItem
import com.example.tradewolfapp.R 


@Composable
fun navigationItems(): List<NavigationItem> {   
    return listOf(
        NavigationItem(
            icon = painterResource(id = R.drawable.home_home),
            route = Screens.Home.rout
        ),
        NavigationItem(
            icon = painterResource(id = R.drawable.chartline_home),
            route = Screens.Markets.rout
         ),
        NavigationItem(
            icon = painterResource(id = R.drawable.circleplus_home),
            route = Screens.Transaction.rout,
            isCustom = true
        ),
        NavigationItem(
            icon = painterResource(id = R.drawable.hourglass_home),
            route = Screens.Wallets.rout
        ),
        NavigationItem(
            icon = painterResource(id = R.drawable.settings_home),
            route = Screens.Profile.rout
        )
    )
}

