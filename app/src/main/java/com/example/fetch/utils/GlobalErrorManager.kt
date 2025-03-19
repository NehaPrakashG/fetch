package com.example.fetch.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object GlobalErrorManager {
    // StateFlow to hold the global error message
    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    // Function to set the error message
    fun setError(message: String?) {
        _errorState.value = message
    }

    // Function to clear the error message
    fun clearError() {
        _errorState.value = null
    }
}
