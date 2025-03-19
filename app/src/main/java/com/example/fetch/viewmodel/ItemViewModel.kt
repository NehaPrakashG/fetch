package com.example.fetch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetch.network.model.Item
import com.example.fetch.network.api.Result
import com.example.fetch.network.repository.ItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    private val _state = MutableStateFlow<Result<List<Item>>>(Result.Loading)
    val state:  StateFlow<Result<List<Item>>> = _state

    fun fetchItems() {
        viewModelScope.launch {
            _state.value = Result.Loading
            _state.value = repository.getItems()
        }
    }

    fun getGroupedAndSortedItems(items: List<Item>): Map<Int, List<Item>> {
        return items
            .filter { !it.name.isNullOrBlank() } // Filter out items with null or blank names
            .sortedWith(compareBy({ it.listId ?: 0 }, { it.name })) // Sort by listId (with 0 for null), then by name
            .groupBy { it.listId ?: 0 } // Group by listId, replacing null with 0
    }

}
