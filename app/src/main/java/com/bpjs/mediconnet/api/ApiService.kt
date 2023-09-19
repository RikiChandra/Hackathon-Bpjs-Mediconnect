package com.bpjs.mediconnet.api

import com.bpjs.mediconnet.model.MedicineDetailResponse
import com.bpjs.mediconnet.model.MedicineResponse
import com.bpjs.mediconnet.model.PharmacyDetailResponses
import com.bpjs.mediconnet.model.PharmacyResponse
import com.bpjs.mediconnet.model.feedbackModel.ReviewResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("apotek")
    suspend fun getPharmacy(): PharmacyResponse

    @FormUrlEncoded
    @POST("feedback")
    suspend fun postFeedback(
        @Field("rating") rating: Int,
        @Field("review") review: String
    ): ReviewResponse

    @GET("obat")
    suspend fun getMedicine(): MedicineResponse

    @GET("obat/{id}")
    suspend fun getDetailMedicine(
        @Path("id") id: String,
    ): MedicineDetailResponse

    @GET("apotek/{id}")
    suspend fun getPharmacyById(
        @Path("id") id: String
    ): PharmacyDetailResponses
}