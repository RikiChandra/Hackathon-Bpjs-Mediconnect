package com.bpjs.mediconnet.model.feedbackModel

data class Feedback(
    val id: Long,
    val name: String,
    val question: String,
    val imageSource: Int,
    val imageHeader: Int
)