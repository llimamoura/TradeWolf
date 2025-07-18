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

@Composable
fun NavigationBar(
    navController: NavController,
) {

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    androidx.compose.material3.NavigationBar(
        containerColor = BackgroundDark
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
                                .shadow(
                                    elevation = 12.dp,
                                   shape = CircleShape,
                                    clip = true,
                                    ambientColor = Color.Black.copy(alpha = 0.5f),
                                    spotColor = Color.Black.copy(alpha = 0.5f),
                                )
                                .size(66.dp)
                                .clip(CircleShape)
                                .background(DullGray)
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                modifier = Modifier.size(34.dp)
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Blue,
                        unselectedIconColor = MaterialTheme.colorScheme.surface,
                        indicatorColor = Color.Transparent,
                        selectedTextColor = Color.Blue,
                        unselectedTextColor = MaterialTheme.colorScheme.surface
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
                        Icon(imageVector = item.icon, contentDescription = item.title)
                    },
                    label = {
                        Text(
                            text = item.title ?: "",

                            )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.surface,
                        indicatorColor = Color.Transparent,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.surface
                    )

                )
            }
        }
    }
}