package com.bpjs.mediconnet.elements

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bpjs.mediconnet.navigation.BottomNavScreen

@Composable
fun BottomNav(
    navController: NavHostController
) {
    val navItems = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.Pharmacy,
        BottomNavScreen.Medicine,
        BottomNavScreen.Feedback,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    NavigationBar {
        navItems.forEach { navItem ->
            NavigationBarItem(
                label = {
                    Text(text = navItem.title)
                },
                selected = currentDestination == navItem.route,
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.Transparent.copy(alpha = 0.5f),
                    unselectedTextColor = Color.Transparent.copy(alpha = 0.5f),
                ),
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentDestination == navItem.route) navItem.selectedIcon else navItem.unselectedIcon,
                        contentDescription = navItem.title,
                    )
                }
            )
        }
    }
}