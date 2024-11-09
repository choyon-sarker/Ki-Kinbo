package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.ProductDataModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProductSearchViewModelTest {

    private lateinit var viewModel: ProductSearchViewModel
    private val productList = listOf(
        ProductDataModel(
            productId = "1", productName = "Apple", productPrice = 120, productRating = 4.5, productDetail = "Fresh red apple", productReview = "Crisp and sweet", productCategory = "Fruit"
        ),
        ProductDataModel(
            productId = "2", productName = "Banana", productPrice = 60, productRating = 4.2, productDetail = "Organic banana", productReview = "Soft and ripe", productCategory = "Fruit"
        ),
        ProductDataModel(
            productId = "3", productName = "Carrot", productPrice = 30, productRating = 4.0, productDetail = "Fresh orange carrot", productReview = "Crunchy and sweet", productCategory = "Vegetable"
        ),
        ProductDataModel(
            productId = "4", productName = "Orange", productPrice = 100, productRating = 4.3, productDetail = "Juicy orange", productReview = "Perfect for juice", productCategory = "Fruit"
        ),
        ProductDataModel(
            productId = "5", productName = "Broccoli", productPrice = 50, productRating = 3.8, productDetail = "Organic green broccoli", productReview = "Healthy and fresh", productCategory = "Vegetable"
        ),
        ProductDataModel(
            productId = "6", productName = "Milk", productPrice = 90, productRating = 4.7, productDetail = "Full cream milk", productReview = "Rich and creamy", productCategory = "Dairy"
        )
    )
    @BeforeEach
    fun setup() {
        viewModel = ProductSearchViewModel(productList)
    }


    // Test for searching by full product name
    @Test
    fun filterProductsByNameReturnsListForExactNameMatch() {
        val result = viewModel.filterProductsByName("Apple")
        assertEquals(1, result.size)
        assertEquals("Apple", result[0].productName)
    }

    // Test for searching by partial product name (case-insensitive)
    @Test
    fun filterProductsByNameReturnsListForPartialNameMatch() {
        val result = viewModel.filterProductsByName("app") // should match "Apple"
        assertEquals(1, result.size)
        assertEquals("Apple", result[0].productName)
    }

    // Test for searching by full category name
    @Test
    fun filterProductsByCategoryReturnsListForExactCategoryMatch() {
        val result = viewModel.filterProductsByCategory("Fruit")
        assertEquals(3, result.size)
        assertEquals("Apple", result[0].productName)
        assertEquals("Banana", result[1].productName)
    }

    // Test for searching by partial category name (case-insensitive)
    @Test
    fun filterProductsByCategoryReturnsListForPartialCategoryMatch() {
        val result = viewModel.filterProductsByCategory("fru") // should match "Fruit"
        assertEquals(3, result.size)
        assertEquals("Apple", result[0].productName)
        assertEquals("Banana", result[1].productName)
    }
    // Test for case-insensitive search by product name
    @Test
    fun filterProductsByNameIsCaseInsensitive() {
        val result = viewModel.filterProductsByName("banana") // lowercase search
        assertEquals(1, result.size)
        assertEquals("Banana", result[0].productName)
    }
    // Test for case-insensitive search by category name
    @Test
    fun filterProductsByCategoryIsCaseInsensitive() {
        val result = viewModel.filterProductsByCategory("fruit") // lowercase search
        assertEquals(3, result.size)
        assertEquals("Apple", result[0].productName)
        assertEquals("Banana", result[1].productName)
    }
    // Test for partial match with multiple results
    @Test
    fun filterProductsByNameReturnsMultipleMatchesForPartialName() {
        val result = viewModel.filterProductsByName("a") // should match "Apple" and "Banana"
        assertEquals(4, result.size)
        assertEquals("Apple", result[0].productName)
        assertEquals("Banana", result[1].productName)
        assertEquals("Carrot",result[2].productName)
        assertEquals("Orange",result[3].productName)
    }
    // Test for case-insensitive partial match with multiple results
    @Test
    fun filterProductsByNameReturnsMultipleMatchesForPartialNameCaseInsentitive() {
        val result = viewModel.filterProductsByName("A") // should match "Apple" and "Banana"
        assertEquals(4, result.size)
        assertEquals("Apple", result[0].productName)
        assertEquals("Banana", result[1].productName)
        assertEquals("Carrot",result[2].productName)
        assertEquals("Orange",result[3].productName)
    }
    // Test when searching for a name that does not exist
    @Test
    fun filterProductsByNameReturnsEmptyListForNoNameMatches() {
        val result = viewModel.filterProductsByName("Nonexistent")
        assertEquals(0, result.size)
    }
    // Test when searching for a category that does not exist
    @Test
    fun filterProductsByCategoryReturnsEmptyListForNoCategoryMatches() {
        val result = viewModel.filterProductsByCategory("Pet")
        assertEquals(0, result.size)
    }

    // Test filtering products by an empty string for name
    @Test
    fun filterProductsByNameWithEmptyQueryReturnsAllProducts() {
        val result = viewModel.filterProductsByName("")
        assertEquals(productList.size, result.size) // Should return all products
    }

    // Test filtering products by an empty string for category
    @Test
    fun filterProductsByCategoryWithEmptyQueryReturnsAllProducts() {
        val result = viewModel.filterProductsByCategory("")
        assertEquals(productList.size, result.size) // Should return all products
    }

    // Test for filtering products by a numeric value in product name (not expected to match)
    @Test
    fun filterProductsByNameWithNumericValueReturnsEmpty() {
        val result = viewModel.filterProductsByName("123")
        assertEquals(0, result.size)
    }

    // Test for filtering products by a numeric value in category (not expected to match)
    @Test
    fun filterProductsByCategoryWithNumericValueReturnsEmpty() {
        val result = viewModel.filterProductsByCategory("456")
        assertEquals(0, result.size)
    }

    // Test filtering by a name that is a prefix to multiple product names
    @Test
    fun filterProductsByNamePrefixReturnsMultipleMatches() {
        val result = viewModel.filterProductsByName("an") // should match "Apple" and "apple cider"
        assertEquals(2, result.size)
        assertEquals("Banana", result[0].productName)
        assertEquals("Orange", result[1].productName)
    }

    // Test filtering products with whitespace in search query
    @Test
    fun filterProductsByNameWithWhitespaceInQuery() {
        val result = viewModel.filterProductsByName(" Apple ") // should match "Apple"
        assertEquals(1, result.size)
        assertEquals("Apple", result[0].productName)
    }

    // Test filtering products by category with leading and trailing spaces
    @Test
    fun filterProductsByCategoryWithWhitespaceInQuery() {
        val result = viewModel.filterProductsByCategory("  Fruit  ") // should match "Fruit"
        assertEquals(3, result.size)
        assertEquals("Apple", result[0].productName)
        assertEquals("Banana", result[1].productName)
        assertEquals("Orange", result[2].productName)
    }
}

