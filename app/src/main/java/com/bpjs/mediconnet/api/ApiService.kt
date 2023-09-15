package com.bpjs.mediconnet.api

import com.bpjs.mediconnet.model.MedicineResponse
import com.bpjs.mediconnet.model.PharmacyResponse
import com.bpjs.mediconnet.model.feedbackModel.ReviewResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("apotek")
    suspend fun getPharmacy(): PharmacyResponse

    @FormUrlEncoded
    @POST("feedback")
    suspend fun postFeedback(@Field("review") review: String, @Field("rating") rating: Double)

    @GET("feedback")
    suspend fun getFeedback(): ReviewResponse

    @GET("obat")
    suspend fun getMedicine(): MedicineResponse
}