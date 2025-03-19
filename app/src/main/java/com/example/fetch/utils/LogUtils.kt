package com.example.fetch.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Locale

object LogUtils {
    private const val TAG = "Retrofit"
    private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS"

    // Color codes for log levels
    private const val RESET = "\u001B[0m"
    private const val GREEN = "\u001B[32m"
    private const val YELLOW = "\u001B[33m"
    private const val BLUE = "\u001B[34m"

    private fun getTimestamp(): String {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(java.util.Date())
    }

    fun logNetworkMessage(message: String) {
        val timestamp = getTimestamp()
        when {
            message.startsWith(">") -> {
                Log.d(TAG, "$BLUE[$timestamp] -------------------- REQUEST --------------------$RESET")
            }
            message.startsWith("<") -> {
                Log.d(TAG, "$GREEN[$timestamp] -------------------- RESPONSE --------------------$RESET")
            }
            else -> {
                Log.d(TAG, "$YELLOW[$timestamp] $message$RESET")
            }
        }
    }

    fun logDetail(className: String, message: String) {
        Log.d(TAG, "$BLUE[$className: $message]")
    }
}