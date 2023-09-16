package com.bpjs.mediconnet

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bpjs.mediconnet.elements.BottomNav
import com.bpjs.mediconnet.navigation.Screen
import com.bpjs.mediconnet.navigation.ScreenNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediConnectScreen(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailMedicine.route && currentRoute != Screen.DetailPharmacy.route) BottomNav(navController = navController)
        }
    ) { innerPadding ->
        ScreenNavGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}
