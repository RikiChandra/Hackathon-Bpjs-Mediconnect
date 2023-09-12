package com.bpjs.mediconnet.api


class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor = okhttp3.logging.HttpLoggingInterceptor().apply {
                level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
            }

            val client = okhttp3.OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

            val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl("https://api-mediconnect.vercel.app/api/")
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

}