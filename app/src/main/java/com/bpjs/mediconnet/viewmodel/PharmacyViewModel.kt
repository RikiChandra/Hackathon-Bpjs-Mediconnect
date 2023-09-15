package com.bpjs.mediconnet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.DataItem
import com.bpjs.mediconnet.repository.PharmacyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PharmacyViewModel @Inject constructor(
    private val repository: PharmacyRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<DataItem>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<List<DataItem>>> = _uiState

    fun getPharmacies() {
        viewModelScope.launch {
            repository.getAllPharmacies().catch {
                _uiState.value = UiState.Error(it.message.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}