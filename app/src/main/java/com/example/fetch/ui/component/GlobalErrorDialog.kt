package com.example.fetch.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.fetch.utils.GlobalErrorManager

@Composable
fun GlobalErrorDialog(errorMessage: String) {
    AlertDialog(
        onDismissRequest = {
            GlobalErrorManager.clearError() // Clear error when dialog is dismissed
        },
        title = { Text("Error") },
        text = { Text(errorMessage) },
        confirmButton = {
            TextButton(onClick = {
                GlobalErrorManager.clearError() // Clear error when OK is clicked
            }) {
                Text("OK")
            }
        }
    )
}
