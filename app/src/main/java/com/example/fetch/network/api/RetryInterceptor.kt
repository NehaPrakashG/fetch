package com.example.fetch.network.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okio.IOException
import okhttp3.Response
import kotlin.math.pow

object RetryInterceptor {
    private const val MAX_RETRIES = 3
    private const val INITIAL_DELAY_MILLIS = 1000L
    private const val MAX_DELAY_MILLIS = 5000L

    val interceptor = Interceptor { chain ->
        // Use suspend function within the interceptor context
        val response = runBlocking {
            retryRequest(chain)
        }

        return@Interceptor response
    }

    private suspend fun retryRequest(chain: Interceptor.Chain): Response {
        var attempt = 0
        var response: Response? = null
        var lastException: Exception? = null

        while (attempt < MAX_RETRIES) {
            try {
                // Try to execute the request
                response = withContext(Dispatchers.IO) {
                    chain.proceed(chain.request())
                }

                if (response.isSuccessful) {
                    break
                }
            } catch (e: IOException) {
                lastException = e
            }

            // Retry if an exception occurred or if response was unsuccessful
            attempt++
            if (attempt < MAX_RETRIES) {
                // Perform exponential backoff with delay
                val delayMillis = (INITIAL_DELAY_MILLIS * 2.0.pow(attempt.toDouble())).toLong()
                    .coerceAtMost(MAX_DELAY_MILLIS)
                delay(delayMillis) // Use delay() within coroutine context
            }
        }

        // If still not successful, throw the last exception encountered
        lastException?.let {
            throw it
        }

        return response ?: throw IOException("Unknown error occurred")
    }
}