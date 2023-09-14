package com.bpjs.mediconnet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bpjs.mediconnet.screen.FeedbackScreen
import com.bpjs.mediconnet.screen.MedicineScreen
import com.bpjs.mediconnet.screen.PharmacyScreen

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
            PharmacyScreen()
        }

        composable(route = BottomNavScreen.Medicine.route) {
            MedicineScreen()
        }

        composable(route = BottomNavScreen.Feedback.route) {
            FeedbackScreen()
        }
    }
}