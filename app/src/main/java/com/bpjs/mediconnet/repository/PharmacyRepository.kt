package com.bpjs.mediconnet.repository


import com.bpjs.mediconnet.api.ApiConfig
import com.bpjs.mediconnet.model.DataItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PharmacyRepository : Repository {

    private val apiService = ApiConfig.getApiService()

    suspend fun getAllPharmacies(): Flow<List<DataItem>> {
        val data = apiService.getPharmacy()
       return flowOf(data.data)
    }

}