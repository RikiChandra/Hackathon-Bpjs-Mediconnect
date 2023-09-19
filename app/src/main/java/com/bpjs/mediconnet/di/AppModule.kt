package com.bpjs.mediconnet.di

import android.content.Context
import com.bpjs.mediconnet.api.ApiService
import com.bpjs.mediconnet.api.ChatGPTService
import com.bpjs.mediconnet.repository.DataStoreRepository
import com.bpjs.mediconnet.repository.FeedbackRepository
import com.bpjs.mediconnet.repository.MedicineRepository
import com.bpjs.mediconnet.repository.PharmacyRepository
import com.bpjs.mediconnet.viewmodel.ChatGPTViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePharmacyRepository(api: ApiService) = PharmacyRepository(api)

    @Singleton
    @Provides
    fun provideFeedbackRepository(api: ApiService) = FeedbackRepository(api)

    @Singleton
    @Provides
    fun provideMedicineRepository(api: ApiService) = MedicineRepository(api)

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)

    @Singleton
    @Provides
    fun provideChatGPTViewModel(api: ChatGPTService) = ChatGPTViewModel(api)

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

    @Singleton
    @Provides
    fun provideChatGPTApiService(): ChatGPTService {
        val loggingInterceptor = okhttp3.logging.HttpLoggingInterceptor().apply {
            level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
        }
        val client = okhttp3.OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor).build()
        val retrofit =
            retrofit2.Retrofit.Builder().baseUrl("https://api.openai.com/")
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .client(client)
                .build()

        return retrofit.create(ChatGPTService::class.java)
    }
}


