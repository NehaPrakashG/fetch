package com.example.fetch

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fetch.ui.screens.ListItemScreen
import com.example.fetch.ui.theme.PaddingMedium

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isConnected = isInternetAvailable() // Check internet connection

            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize().padding(PaddingMedium)) {
                    when {
                        !isConnected -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(PaddingMedium)
                            ) {
                                // Show a message if there is no internet
                                Text(
                                    "No internet connection. Please check your network settings.",
                                    modifier = Modifier.align(
                                        Alignment.Center
                                    )
                                )
                            }
                        }
                        else -> {
                            // Show list or other content when connected
                            // Here you can display other content such as a progress bar or list.
                            ListItemScreen()
                        }
                    }
                }
            }
        }
    }

    // Check for internet connection
    private fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

}
