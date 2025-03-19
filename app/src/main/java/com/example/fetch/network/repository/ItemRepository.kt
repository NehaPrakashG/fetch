package com.example.fetch.network.repository


import com.example.fetch.network.api.ApiService
import com.example.fetch.utils.GlobalErrorManager
import com.example.fetch.network.api.Result
import com.example.fetch.network.model.Item

class ItemRepository(private val apiService: ApiService) {

    // This function returns Result, including the possibility of success or error
    suspend fun getItems(): Result<List<Item>> {
        return try {
            // Start by making the network call
            val response = apiService.getItems()

            // Check if the response is successful
            if (response.isSuccessful) {
                // If successful, return the data wrapped in Result.Success
                response.body()?.let {
                    Result.Success(it)
                } ?: Result.Error(Exception("Empty response"))
            } else {
                // If the response was not successful, return an error result
                val errorMessage = "Error: ${response.code()}"
                GlobalErrorManager.setError(errorMessage) // Set error globally
                Result.Error(Exception(errorMessage))
            }
        } catch (e: Exception) {
            // In case of an exception (e.g., network error), return the error
            val errorMessage = e.message ?: "Unknown error"
            GlobalErrorManager.setError(errorMessage) // Set error globally
            Result.Error(Exception(errorMessage))
        }
    }
}
