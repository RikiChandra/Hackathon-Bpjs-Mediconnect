package com.bpjs.mediconnet.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.Medicine
import com.bpjs.mediconnet.repository.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(private val repository: MedicineRepository): ViewModel() {

    private val _dataMedicine: MutableStateFlow<UiState<List<Medicine>>> = MutableStateFlow(UiState.Loading)
    val dataMedicine: MutableStateFlow<UiState<List<Medicine>>> = _dataMedicine

    fun getMedicine() {
        viewModelScope.launch {
            repository.getMedicine().catch { exception ->
                _dataMedicine.value = UiState.Error(exception.message.toString())
            }.collect { data ->
                _dataMedicine.value = UiState.Success(data)
            }
        }
    }
}