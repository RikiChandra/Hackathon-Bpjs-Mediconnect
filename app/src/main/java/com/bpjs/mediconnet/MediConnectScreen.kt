package com.bpjs.mediconnet

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bpjs.mediconnet.elements.BottomNav
import com.bpjs.mediconnet.navigation.Screen
import com.bpjs.mediconnet.navigation.ScreenNavGraph
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun MediConnectScreen(navController: NavHostController = rememberNavController(), screen: String) {


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val screenWihoutNavBar = listOf(
        Screen.DetailMedicine.route,
        Screen.ChatBot.route,
        Screen.DetailPharmacy.route,
        com.bpjs.mediconnet.navigation.Screen.DetailMedicine.route,
        com.bpjs.mediconnet.navigation.Screen.FeedbackDetail.route,
    )

    Scaffold(
        bottomBar = {
            if (currentRoute !in screenWihoutNavBar) BottomNav(navController = navController)
        }
    ) { innerPadding ->
        ScreenNavGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = screen
        )
    }
}
