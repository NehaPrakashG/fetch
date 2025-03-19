package com.example.fetch.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fetch.ui.theme.*

@Composable
fun ItemCard(itemName: String) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = ElevationSmall
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingMedium, vertical = PaddingTiny)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "- $itemName",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(WeightOne)
            )
        }
    }
}
