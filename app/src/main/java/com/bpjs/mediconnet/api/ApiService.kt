package com.bpjs.mediconnet.api

import com.bpjs.mediconnet.model.PharmacyResponse
import retrofit2.http.GET

interface ApiService {
    @GET("apotek")
    suspend fun getPharmacy(): PharmacyResponse
}