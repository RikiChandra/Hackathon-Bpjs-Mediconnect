package com.bpjs.mediconnet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bpjs.mediconnet.di.Injection
import com.bpjs.mediconnet.repository.PharmacyRepository
import com.bpjs.mediconnet.screen.PharmacyScreen
import com.bpjs.mediconnet.viewmodel.PharmacyViewModel

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
                PharmacyScreen(viewModel = PharmacyViewModel(Injection.provideRepository()))
        }

        composable(route = BottomNavScreen.Medicine.route) {

        }

        composable(route = BottomNavScreen.Feedback.route) {

        }
    }
}