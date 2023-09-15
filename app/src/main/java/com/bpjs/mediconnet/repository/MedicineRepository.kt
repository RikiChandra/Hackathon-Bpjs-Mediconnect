package com.bpjs.mediconnet.repository

import android.util.Log
import com.bpjs.mediconnet.api.ApiService
import com.bpjs.mediconnet.helper.UiState
import com.bpjs.mediconnet.model.Medicine
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@ActivityScoped
class MedicineRepository @Inject constructor(private val api: ApiService){
    suspend fun getMedicine(): Flow<List<Medicine>> {
        val dataMedicine = api.getMedicine().data
        return flowOf(dataMedicine)
    }
}