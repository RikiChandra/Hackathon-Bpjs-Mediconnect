package com.bpjs.mediconnet.navigation

sealed class Screen(
    val route: String,
) {
    object ChatBot : Screen(
        route = "chatbot",
    )

    object FeedbackDetail : Screen(
        route = "feedbackDetail/{feedbackId}"
    ) {
        fun createRoute(feedbackId: Long) = "feedbackDetail/$feedbackId"
    }

    object DetailMedicine : Screen(
        route = "detail_medicine/{medicineId}",
    ) {
        fun createRoute(medicineId: String) = "detail_medicine/$medicineId"
    }

    object DetailPharmacy : Screen(
        route = "detail_pharmacy/{pharmacyId}",
    ) {
        fun createRoute(pharmacyId: String) = "detail_pharmacy/$pharmacyId"
    }
}
