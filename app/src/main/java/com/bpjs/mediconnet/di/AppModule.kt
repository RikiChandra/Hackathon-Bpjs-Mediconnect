package com.bpjs.mediconnet.di

import com.bpjs.mediconnet.api.ApiService
import com.bpjs.mediconnet.repository.FeedbackRepository
import com.bpjs.mediconnet.repository.PharmacyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePharmacyRepository(api: ApiService) = PharmacyRepository(api)

    @Singleton
    @Provides
    fun provideFeedbackRepository() = FeedbackRepository()

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        val loggingInterceptor = okhttp3.logging.HttpLoggingInterceptor().apply {
            level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
        }
        val client = okhttp3.OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        val retrofit =
            retrofit2.Retrofit.Builder().baseUrl("https://api-mediconnect.vercel.app/api/")
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .client(client)
                .build()

        return retrofit.create(ApiService::class.java)
    }
}


