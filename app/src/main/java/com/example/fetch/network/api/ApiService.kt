package com.example.fetch.network.api

import com.example.fetch.network.model.Item
import retrofit2.Response
import retrofit2.http.GET

// ApiService - defines network operations
interface ApiService {
        @GET("hiring.json")
        suspend fun getItems(): Response<List<Item>>
}

