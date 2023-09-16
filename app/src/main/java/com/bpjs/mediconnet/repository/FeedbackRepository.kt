package com.bpjs.mediconnet.repository

import com.bpjs.mediconnet.api.ApiService
import com.bpjs.mediconnet.model.feedbackModel.Feedback
import com.bpjs.mediconnet.model.feedbackModel.FeedbackData
import com.bpjs.mediconnet.model.feedbackModel.ReviewResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FeedbackRepository @Inject constructor(
    private val apiService: ApiService
) {
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

    fun getFeedbackScreenById(feedbackID: Long): Feedback {
        return feedbacks.first {
            it.id == feedbackID
        }
    }

    suspend fun postFeedback(
        rating: Int, review: String
    ): Flow<ReviewResponse> {
        val data = apiService.postFeedback(rating = rating, review = review)
        return flowOf(data)
    }
}