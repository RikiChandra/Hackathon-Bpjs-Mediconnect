package com.bpjs.mediconnet.viewmodel

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
class MedicineDetailViewModel
@Inject constructor(private val repository: MedicineRepository) :
    ViewModel() {

    private val _dataMedicine: MutableStateFlow<UiState<Medicine>> = MutableStateFlow(UiState.Loading)
    val dataMedicine: MutableStateFlow<UiState<Medicine>> = _dataMedicine

    fun getDetailMedicine(id: String) {
        viewModelScope.launch {
            repository.getDetailMedicine(id).catch { exception ->
                _dataMedicine.value = UiState.Error(exception.message.toString())
            }.collect { data ->
                _dataMedicine.value = UiState.Success(data)
            }
        }
    }
}