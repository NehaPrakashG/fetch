package com.example.fetch.network.api

import com.example.fetch.utils.LogUtils
import okhttp3.logging.HttpLoggingInterceptor


object LoggingInterceptor {

    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            LogUtils.logNetworkMessage(message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}