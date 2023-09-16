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
class PharmacyDetailViewModel
@Inject constructor(private val repository: PharmacyRepository) : ViewModel()
{
    private val _dataPharmacy: MutableStateFlow<UiState<DataItem>> = MutableStateFlow(UiState.Loading)
    val dataPharmacy: MutableStateFlow<UiState<DataItem>> = _dataPharmacy

    fun getDetailPharmacy(id: String){
        viewModelScope.launch {
            repository.getDetailPharmacies(id).catch {
                _dataPharmacy.value = UiState.Error(it.message.toString())
            }.collect {
                _dataPharmacy.value = UiState.Success(it)
            }
        }
    }


}