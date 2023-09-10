package com.bpjs.mediconnet.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ScreenNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = BottomNavScreen.Home.route,
    ) {
        composable(route = BottomNavScreen.Home.route) {

        }

        composable(route = BottomNavScreen.Pharmacy.route) {

        }

        composable(route = BottomNavScreen.Medicine.route) {

        }

        composable(route = BottomNavScreen.Feedback.route) {

        }
    }
}