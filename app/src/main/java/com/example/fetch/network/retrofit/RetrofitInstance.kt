package com.example.fetch.network.retrofit

import android.content.Context
import com.example.fetch.network.api.ApiService
import com.example.fetch.network.api.LoggingInterceptor
import com.example.fetch.network.api.RetryInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

    // Use lazy to ensure thread safety
    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor.getLoggingInterceptor())
            .addInterceptor(RetryInterceptor.interceptor)
            .build()
    }

    @Volatile
    private var apiService: ApiService? = null

    fun createApi(): ApiService {
        return apiService ?: synchronized(this) {
            apiService ?: buildRetrofit().create(ApiService::class.java).also {
                apiService = it
            }
        }
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.newBuilder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
