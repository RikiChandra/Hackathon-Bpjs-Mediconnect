package com.bpjs.mediconnet.model.feedbackModel

data class ReviewResponse(
    val status: String,
    val data: Data
)

data class Data(
    val rating: Int,
    val review: String,
    val _id: String,
    val __v: Int
)