package com.bpjs.mediconnet.api

import com.bpjs.mediconnet.model.ChatGPTRequestBody
import com.bpjs.mediconnet.model.ChatGPTResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatGPTService {
    @Headers("Content-Type: application/json", "Authorization: Bearer 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")
    @POST("v1/chat/completions")
    suspend fun sendMessage(@Body requestBody: ChatGPTRequestBody): ChatGPTResponse
}