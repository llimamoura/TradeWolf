package com.example.tradewolfapp.views.navigations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tradewolfapp.ui.theme.BackgroundDark
import com.example.tradewolfapp.ui.theme.DullGray
import com.example.tradewolfapp.views.navigations.navigationItems


@Composable
fun NavigationBar(
    navController: NavController,
) {
    val navigationItems= navigationItems() 
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    androidx.compose.material3.NavigationBar(
        containerColor = Color.White
    ) {
        navigationItems.forEachIndexed { _, item ->
            if (item.isCustom) {
                NavigationBarItem(
                    selected = currentDestination?.route == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier.offset(y = (-4).dp),
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(66.dp)
                                .background(Color.White)
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = item.icon,
                                contentDescription = item.title,
                                modifier = Modifier.size(46.dp)
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Blue.copy(alpha = 0.5f),
                        unselectedIconColor = Color(0xFF00234F),
                        indicatorColor = Color.Transparent
                    )

                )

            } else {
                NavigationBarItem(
                    selected = currentDestination?.route == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = item.icon, 
                            contentDescription = item.title,
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Blue.copy(alpha = 0.5f),
                        unselectedIconColor = Color(0xFF00234F),
                        indicatorColor = Color.Transparent
                    )

                )
            }
        }
    }
}
