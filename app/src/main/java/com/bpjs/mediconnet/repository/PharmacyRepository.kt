package com.bpjs.mediconnet.repository


import com.bpjs.mediconnet.api.ApiService
import com.bpjs.mediconnet.model.DataItem
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@ActivityScoped
class PharmacyRepository @Inject constructor(
    private val apiService:ApiService
){
    suspend fun getAllPharmacies(): Flow<List<DataItem>> {
        val data = apiService.getPharmacy()
       return flowOf(data.data)
    }
}