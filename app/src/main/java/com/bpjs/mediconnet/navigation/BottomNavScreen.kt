package com.bpjs.mediconnet.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    object Home: BottomNavScreen(
        route = "home",
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    )

    object Pharmacy: BottomNavScreen(
        route = "pharmacy",
        title = "Apotek",
        selectedIcon = Icons.Filled.Place,
        unselectedIcon = Icons.Outlined.Place,
    )

    object Medicine: BottomNavScreen(
        route = "medicine",
        title = "Obat",
        selectedIcon = Icons.Filled.ShoppingCart,
        unselectedIcon = Icons.Outlined.ShoppingCart,
    )

    object Feedback: BottomNavScreen(
        route = "feedback",
        title = "Feedback",
        selectedIcon = Icons.Filled.ThumbUp,
        unselectedIcon = Icons.Outlined.ThumbUp,
    )
}
