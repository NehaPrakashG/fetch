package com.example.fetch.ui.component

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ExitDialog(
    onDismiss: () -> Unit) {
    // Handle back press so dialog is not dismissed
    BackHandler(enabled = true) {
        // Prevent back press from dismissing the dialog
    }

    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = { /* Do nothing on dismiss request to prevent closing on outside touch */ },
        title = {
            Text("Exit App?")
        },
        text = {
            Text("Are you sure you want to leave?")
        },
        confirmButton = {
            TextButton(onClick = {
                (context as? Activity)?.finishAndRemoveTask()
            }) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("No")
            }
        }
    )
}
