package com.bpjs.mediconnet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.feedbackModel.Feedback
import com.bpjs.mediconnet.model.feedbackModel.ReviewResponse
import com.bpjs.mediconnet.repository.FeedbackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedbackDetailViewModel @Inject constructor(
    private val repository: FeedbackRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Feedback>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Feedback>>
        get() = _uiState

    private val _postState: MutableStateFlow<UiState<ReviewResponse>> =
        MutableStateFlow(UiState.Loading)
    val postState: StateFlow<UiState<ReviewResponse>>
        get() = _postState

    fun getFeedbackScreenById(feedbackId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getFeedbackScreenById(feedbackId))
        }
    }

    fun postFeedback(rating: Int, review: String) {
        viewModelScope.launch {
            _postState.emit(UiState.Loading)
            repository.postFeedback(rating, review).catch { exception ->
                _postState.value = UiState.Error(exception.message.toString())
            }.collect { data ->
                _postState.value = UiState.Success(data)
            }
        }
    }
}