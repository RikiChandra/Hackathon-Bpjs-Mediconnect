package com.bpjs.mediconnet.navigation

import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
) {
    object ChatBot: Screen(
        route = "chatbot",
    )
}
