package com.example.fetch.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fetch.ui.component.ExitDialog
import com.example.fetch.ui.component.ItemCard
import com.example.fetch.ui.component.ListTitle
import com.example.fetch.ui.theme.PaddingMedium
import com.example.fetch.viewmodel.ItemViewModel
import com.example.fetch.network.api.Result
import com.example.fetch.network.model.Item
import com.example.fetch.network.repository.ItemRepository
import com.example.fetch.network.retrofit.RetrofitInstance


@Composable
fun ListItemScreen() {
    val viewModel = remember {
        ItemViewModel(
            ItemRepository(RetrofitInstance.createApi())
        )
    }

    // Trigger the data fetch when the screen is composed
    LaunchedEffect(Unit) { viewModel.fetchItems() }

    val state by viewModel.state.collectAsState()

    // Track dialog state
    var showExitDialog by remember { mutableStateOf(false) }

    // Intercept back press to show exit dialog
    BackHandler { showExitDialog = true }

    // Show exit dialog
    if (showExitDialog) {
        ExitDialog(onDismiss = { showExitDialog = false })
    }

    when (state) {
        is Result.Loading -> LoadingIndicator()
        is Result.Success -> ItemList((state as Result.Success).data, viewModel)
        else -> {}
    }
}

@Composable
private fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingMedium),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ItemList(items: List<Item>, viewModel: ItemViewModel) {
    val filteredAndSortedItems = remember(items) {
        viewModel.getGroupedAndSortedItems(items)
    }

    LazyColumn(modifier = Modifier.padding(PaddingMedium)) {
        filteredAndSortedItems.forEach { (listId, items) ->
            // Display the title for each group
            listId.let { id ->
                item { ListTitle(id) }
            }

            // Display items within each group
            items(items) { item ->
                // Only display items with non-blank names
                item.name?.let { name ->
                    if (name.isNotBlank()) {
                        ItemCard(itemName = name)
                    }
                }
            }
        }
    }
}


