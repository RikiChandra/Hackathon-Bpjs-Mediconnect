package com.bpjs.mediconnet.repository

import com.bpjs.mediconnet.model.feedbackModel.Feedback
import com.bpjs.mediconnet.model.feedbackModel.FeedbackData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
class FeedbackRepository {
    private val feedbacks = mutableListOf<Feedback>()
    init {
        if (feedbacks.isEmpty()) {
            FeedbackData.feedback.forEach {
                feedbacks.add(it)
            }
        }
    }
    fun getAllFeedback(): Flow<List<Feedback>> {
        return flowOf(feedbacks)
    }
}