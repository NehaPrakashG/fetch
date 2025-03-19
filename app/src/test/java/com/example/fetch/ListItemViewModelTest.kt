package com.example.fetch

import com.example.fetch.network.model.Item
import com.example.fetch.network.repository.ItemRepository
import com.example.fetch.viewmodel.ItemViewModel
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import junit.framework.TestCase.assertEquals


class ItemViewModelTest {

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var repository: ItemRepository

    @Before
    fun setUp() {
        // Mock the repository or pass a simple test implementation
        repository = mockk<ItemRepository>()

        // Create the ViewModel instance with the repository
        itemViewModel = ItemViewModel(repository)
    }

    @Test
    fun testGroupedAndSortedItems() {
        // Define the test data
        val items = listOf(
            Item(755, 2, ""),
            Item(684, 1, "Item 684"),
            Item(276, 1, "Item 276"),
            Item(736, 3, null),
            Item(808, 4, "Item 808"),
            Item(510, 2, null),
            Item(394, 2, "Item 394"),
            Item(364, 2, "Item 364"),
            Item(920, 1, "Item 920")
        )

        // Define the expected output
        val expected = mapOf(
            1 to listOf(
                Item(276, 1, "Item 276"),
                Item(684, 1, "Item 684"),
                Item(920, 1, "Item 920")
            ),
            2 to listOf(
                Item(364, 2, "Item 364"),
                Item(394, 2, "Item 394")
            ),
            4 to listOf(
                Item(808, 4, "Item 808")
            )
        )

        // Mock the repository if necessary to return test data (not needed in this case since we are directly testing the method)
        // every { repository.getItems() } returns items

        // Call the method on ViewModel and get the actual result
        val actual = itemViewModel.getGroupedAndSortedItems(items)

        // Assert that the expected result matches the actual result
        assertEquals(expected, actual)
    }

    @Test
    fun testNoValidItems() {
        val itemsWithNoValidNames = listOf(
            Item(755, 2, ""),
            Item(736, 3, null),
            Item(510, 2, null)
        )

        val expected = emptyMap<Int, List<Item>>()

        val actual = itemViewModel.getGroupedAndSortedItems(itemsWithNoValidNames)

        assertEquals(expected, actual)
    }

    @Test
    fun testEmptyList() {
        val emptyList = emptyList<Item>()

        val expected = emptyMap<Int, List<Item>>()

        val actual = itemViewModel.getGroupedAndSortedItems(emptyList)

        assertEquals(expected, actual)
    }

    @Test
    fun testItemsWithNullListId() {
        val itemsWithNullListId = listOf(
            Item(1, null, "Item 1"),
            Item(2, null, "Item 2")
        )

        val expected = mapOf(
            0 to listOf(
                Item(1, null, "Item 1"),
                Item(2, null, "Item 2")
            )
        )

        val actual = itemViewModel.getGroupedAndSortedItems(itemsWithNullListId)

        assertEquals(expected, actual)
    }
}
