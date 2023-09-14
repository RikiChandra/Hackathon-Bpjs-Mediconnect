package com.bpjs.mediconnet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.feedbackModel.Feedback
import com.bpjs.mediconnet.repository.FeedbackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
    private val repository: FeedbackRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Feedback>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Feedback>>>
        get() = _uiState

    fun getFeedback() {
        viewModelScope.launch {
            repository.getAllFeedback().catch {
                _uiState.value = UiState.Error(it.message.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}