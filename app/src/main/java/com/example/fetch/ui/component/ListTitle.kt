package com.example.fetch.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fetch.ui.theme.*

@Composable
fun ListTitle(listId: Int) {
    Text(
        text = "List ID: $listId",
        modifier = Modifier
            .padding(vertical = PaddingSmall, horizontal = PaddingMedium)
            .padding(PaddingTiny)
    )
}