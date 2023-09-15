package com.bpjs.mediconnet.navigation

import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
) {
    object ChatBot: Screen(
        route = "chatbot",
    )

    object DetailMedicine: Screen(
        route = "detail_medicine/{medicineId}",
    ) {
        fun createRoute(medicineId: String) = "detail_medicine/$medicineId"
    }
}
