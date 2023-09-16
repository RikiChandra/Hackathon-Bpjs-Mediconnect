package com.bpjs.mediconnet.model.feedbackModel

import com.bpjs.mediconnet.R

object FeedbackData {
    val feedback = listOf(
        Feedback(
            1,
            "Kualitas Pelayanan",
            "Bagaimana pendapat anda terhadap\nfitur Apotek dan Informasi Obat?",
            R.drawable.megaphone,
            R.drawable.review_feedback
        ),
        Feedback(
            2,
            "Fitur dan Fungsi Aplikasi",
            "Bagaimana pendapat anda terhadap\nkeseluruhan fitur dan fungsi aplikasi?",
            R.drawable.puzzle,
            R.drawable.puzzle_feedback
        ),
        Feedback(
            3,
            "Pengalaman Chatbot",
            "Bagaimana pendapat anda terhadap\nfitur chatbot?",
            R.drawable.chatbot,
            R.drawable.ai_review
        ),
        Feedback(
            4,
            "Saran dan Perbaikan",
            "Bagaimana pendapat anda terhadap\naplikasi ini secara keseluruhan?",
            R.drawable.lamp,
            R.drawable.idea_review
        )
    )
}