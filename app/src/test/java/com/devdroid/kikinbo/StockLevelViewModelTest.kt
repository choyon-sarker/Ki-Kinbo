package com.devdroid.kikinbo

    import com.devdroid.kikinbo.viewmodel.StockLevelViewModel
    import org.junit.jupiter.api.Assertions.*
    import org.junit.jupiter.api.BeforeEach
    import org.junit.jupiter.api.Test


    class StockLevelViewModelTest {

        private lateinit var viewModel: StockLevelViewModel

        @BeforeEach
        fun setUp() {
            // Initialize the ViewModel before each test
            viewModel = StockLevelViewModel()
        }

        @Test
        fun testAlertMessageVisibilityOnLowStock() {
            // Input Data (hardcoded values)
            val productName = "Test Product"
            val stockLevel = 5
            val restockThreshold = 10

            // Set stock level and threshold in the ViewModel
            viewModel.setStockLevel(productName, stockLevel, restockThreshold)

            // Get the visibility of the alert message
            val alertMessageVisible = viewModel.isAlertMessageVisible()

            // Assert that the alert message should be visible (stock is below threshold)
            assertTrue(alertMessageVisible)
        }


        // Second Test Case: Input Validation for Restock Threshold
        @Test
        fun testRestockThresholdValidation() {
            val productName = "Test Product"
            val stockLevel = 5
            val invalidRestockThreshold = -1  // Invalid restock threshold (negative value)
            val validRestockThreshold = 10    // Valid restock threshold (positive value)

            // Trying to set a negative restock threshold (will fail initially)
            val isValidNegative = viewModel.setStockLevel(productName, stockLevel, invalidRestockThreshold)
            assertFalse(isValidNegative)  // Should return false because negative values are invalid

            // Trying to set a valid restock threshold (should pass)
            val isValidPositive = viewModel.setStockLevel(productName, stockLevel, validRestockThreshold)
            assertTrue(isValidPositive)   // Should return true because positive values are valid
        }

        @Test
        fun testStockLevelValidation() {
            val productName = "Test Product"
            val invalidStockLevel = -5  // Invalid stock level (negative value)
            val validStockLevel = 20    // Valid stock level (positive value)

            // Trying to set a negative stock level (will fail initially)
            val isStockLevelValidNegative = viewModel.setStockLevel(productName, invalidStockLevel, 10)
            assertFalse(isStockLevelValidNegative)  // Should return false because negative values are invalid

            // Trying to set a valid stock level (should pass)
            val isStockLevelValidPositive = viewModel.setStockLevel(productName, validStockLevel, 10)
            assertTrue(isStockLevelValidPositive)   // Should return true because positive values are valid
        }

    }


