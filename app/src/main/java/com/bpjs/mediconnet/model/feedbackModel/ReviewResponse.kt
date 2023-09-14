package com.bpjs.mediconnet.model.feedbackModel

data class ReviewResponse(
    val status: String,
    val data: List<ReviewItem>
)

data class ReviewItem(
    val _id: String,
    val rating: Double,
    val review: String,
)
