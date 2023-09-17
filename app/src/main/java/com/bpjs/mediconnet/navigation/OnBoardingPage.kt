package com.bpjs.mediconnet.navigation

import androidx.annotation.DrawableRes
import com.bpjs.mediconnet.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.ai_review,
        title = "Cari Apotek dan Obat",
        description = "Cari apotek dan obat yang kamu butuhkan dengan mudah"
    )

    object Second : OnBoardingPage(
        image = R.drawable.chatbot,
        title = "Konsultasi dengan Chatbot",
        description = "Konsultasi dengan chatbot untuk mengetahui penyakit yang kamu derita"
    )

    object Third : OnBoardingPage(
        image = R.drawable.megaphone,
        title = "Berikan Feedback",
        description = "Berikan feedback untuk membantu kami meningkatkan kualitas aplikasi"
    )
}