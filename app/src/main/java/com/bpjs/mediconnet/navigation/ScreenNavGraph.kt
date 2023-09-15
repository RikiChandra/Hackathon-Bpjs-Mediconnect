package com.bpjs.mediconnet.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bpjs.mediconnet.screen.FeedbackScreen
import com.bpjs.mediconnet.screen.MedicineDetailScreen
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
            MedicineScreen(
                navigateToDetail = { medicineId ->
                    navController.navigate(Screen.DetailMedicine.createRoute(medicineId))
                }
            )
        }

        composable(
            route = Screen.DetailMedicine.route,
            arguments = listOf(navArgument("medicineId") { type = NavType.StringType })
        ) {
            val medicineId = it.arguments?.getString("medicineId") ?: ""
            MedicineDetailScreen(medicineId = medicineId, navController = navController)
        }

        composable(route = BottomNavScreen.Feedback.route) {
            FeedbackScreen()
        }
    }
}