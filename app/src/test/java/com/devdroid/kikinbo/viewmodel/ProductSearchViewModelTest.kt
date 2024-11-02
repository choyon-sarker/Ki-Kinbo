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
    fun filterProductsByName_returns_list_for_exact_name_match() {
        val result = viewModel.filterProductsByName("Apple")
        assertEquals(1, result.size)
        assertEquals("Apple", result[0].productName)
    }

    // Test for searching by partial product name (case-insensitive)
    @Test
    fun filterProductsByName_returns_list_for_partial_name_match() {
        val result = viewModel.filterProductsByName("app") // should match "Apple"
        assertEquals(1, result.size)
        assertEquals("Apple", result[0].productName)
    }

    // Test for searching by full category name
    @Test
    fun filterProductsByCategory_returns_list_for_exact_category_match() {
        val result = viewModel.filterProductsByCategory("Fruit")
        assertEquals(3, result.size)
        assertEquals("Apple", result[0].productName)
        assertEquals("Banana", result[1].productName)
    }

    // Test for searching by partial category name (case-insensitive)
    @Test
    fun filterProductsByCategory_returns_list_for_partial_category_match() {
        val result = viewModel.filterProductsByCategory("fru") // should match "Fruit"
        assertEquals(3, result.size)
        assertEquals("Apple", result[0].productName)
        assertEquals("Banana", result[1].productName)
    }
    // Test for case-insensitive search by product name
    @Test
    fun filterProductsByName_is_case_insensitive() {
        val result = viewModel.filterProductsByName("banana") // lowercase search
        assertEquals(1, result.size)
        assertEquals("Banana", result[0].productName)
    }
    // Test for case-insensitive search by category name
    @Test
    fun filterProductsByCategory_is_case_insensitive() {
        val result = viewModel.filterProductsByCategory("fruit") // lowercase search
        assertEquals(3, result.size)
        assertEquals("Apple", result[0].productName)
        assertEquals("Banana", result[1].productName)
    }
    // Test for partial match with multiple results
    @Test
    fun filterProductsByName_returns_multiple_matches_for_partial_name() {
        val result = viewModel.filterProductsByName("a") // should match "Apple" and "Banana"
        assertEquals(4, result.size)
        assertEquals("Apple", result[0].productName)
        assertEquals("Banana", result[1].productName)
        assertEquals("Carrot",result[2].productName)
        assertEquals("Orange",result[3].productName)
    }
    // Test for case-insensitive partial match with multiple results
    @Test
    fun filterProductsByName_returns_multiple_matches_for_partial_name_case_insentitive() {
        val result = viewModel.filterProductsByName("A") // should match "Apple" and "Banana"
        assertEquals(4, result.size)
        assertEquals("Apple", result[0].productName)
        assertEquals("Banana", result[1].productName)
        assertEquals("Carrot",result[2].productName)
        assertEquals("Orange",result[3].productName)
    }
    // Test when searching for a name that does not exist
    @Test
    fun filterProductsByName_returns_empty_list_for_no_name_matches() {
        val result = viewModel.filterProductsByName("Nonexistent")
        assertEquals(0, result.size)
    }
    // Test when searching for a category that does not exist
    @Test
    fun filterProductsByCategory_returns_empty_list_for_no_category_matches() {
        val result = viewModel.filterProductsByCategory("Pet")
        assertEquals(0, result.size)
    }

    // Test filtering products by an empty string for name
    @Test
    fun filterProductsByName_with_empty_query_returns_all_products() {
        val result = viewModel.filterProductsByName("")
        assertEquals(productList.size, result.size) // Should return all products
    }

    // Test filtering products by an empty string for category
    @Test
    fun filterProductsByCategory_with_empty_query_returns_all_products() {
        val result = viewModel.filterProductsByCategory("")
        assertEquals(productList.size, result.size) // Should return all products
    }

    // Test for filtering products by a numeric value in product name (not expected to match)
    @Test
    fun filterProductsByName_with_numeric_value_returns_empty() {
        val result = viewModel.filterProductsByName("123")
        assertEquals(0, result.size)
    }

    // Test for filtering products by a numeric value in category (not expected to match)
    @Test
    fun filterProductsByCategory_with_numeric_value_returns_empty() {
        val result = viewModel.filterProductsByCategory("456")
        assertEquals(0, result.size)
    }

    // Test filtering by a name that is a prefix to multiple product names
    @Test
    fun filterProductsByName_prefix_returns_multiple_matches() {
        val result = viewModel.filterProductsByName("an") // should match "Apple" and "apple cider"
        assertEquals(2, result.size)
        assertEquals("Banana", result[0].productName)
        assertEquals("Orange", result[1].productName)
    }

    // Test filtering products with whitespace in search query
    @Test
    fun filterProductsByName_with_whitespace_in_query() {
        val result = viewModel.filterProductsByName(" Apple ") // should match "Apple"
        assertEquals(1, result.size)
        assertEquals("Apple", result[0].productName)
    }

    // Test filtering products by category with leading and trailing spaces
    @Test
    fun filterProductsByCategory_with_whitespace_in_query() {
        val result = viewModel.filterProductsByCategory("  Fruit  ") // should match "Fruit"
        assertEquals(3, result.size)
        assertEquals("Apple", result[0].productName)
        assertEquals("Banana", result[1].productName)
        assertEquals("Orange", result[2].productName)
    }

//    // Test for filtering products by a numeric range in product price
//    @Test
//    fun `filterProductsByPrice returns list within price range`() {
//        val result = viewModel.filterProductsByPrice(minPrice = 50, maxPrice = 120)
//        assertEquals(5, result.size)
//        assertEquals("Apple", result[0].productName)
//        assertEquals("Banana", result[1].productName)
//    }
//    // Test for filtering by a very low price limit
//    @Test
//    fun `filterProductsByPrice returns empty list for very low min price`() {
//        val result = viewModel.filterProductsByPrice(minPrice = -100)
//        assertEquals(6, result.size) // All products should be included
//    }
//    @Test
//    fun `filterProductsByExactRating returns matching product`() {
//        val result = viewModel.filterProductsByRating(4.5)
//        assertEquals(2, result.size) // should return Apple
//        assertEquals("Apple", result[0].productName)
//    }
//    // Test for filtering products by a specific price
//    @Test
//    fun `filterProductsByExactPrice returns matching product`() {
//        val result = viewModel.filterProductsByPrice(60)
//        assertEquals(4, result.size) // should return Banana
//        assertEquals("Apple", result[0].productName)
//    }

}


//val productId: String? = null,
//val productName: String? = null,
//val productPrice: Int? = null,
//val productRating: Double? = null,
//val productDetail: String? = null,
//val productReview: String? = null,
//val productCategory: String? = null