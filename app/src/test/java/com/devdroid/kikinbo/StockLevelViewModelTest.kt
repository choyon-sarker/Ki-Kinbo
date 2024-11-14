package com.devdroid.kikinbo

import com.devdroid.kikinbo.viewmodel.StockLevelViewModel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Unit tests for the StockLevelViewModel class.
 * This test suite validates the behavior of the StockLevelViewModel class,
 * focusing on stock level management and input validation.
 */
class StockLevelViewModelTest {

    // Instance of StockLevelViewModel used in tests
    private lateinit var viewModel: StockLevelViewModel

    /**
     * Initializes the ViewModel instance before each test case.
     */
    @BeforeEach
    fun setUp() {
        viewModel = StockLevelViewModel()
    }

    /**
     * Tests the visibility of an alert message when stock level is below the restock threshold.
     * Expected behavior: The alert message should be visible when stock level is below the threshold.
     */
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

    /**
     * Tests the validation for restock threshold input.
     * Expected behavior: Negative values should be considered invalid, while positive values are valid.
     */
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

    /**
     * Tests the validation for stock level input.
     * Expected behavior: Negative values should be considered invalid, while positive values are valid.
     */
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

    /**
     * Tests the validation for product name input.
     * Expected behavior: Empty or blank product names should be considered invalid.
     */
    @Test
    fun testProductNameValidation() {
        val emptyProductName = ""     // Invalid product name (empty)
        val blankProductName = "     " // Invalid product name (blank spaces)
        val validProductName = "Test Product" // Valid product name

        // Trying to set an empty product name (will fail initially)
        val isValidEmptyProductName = viewModel.setStockLevel(emptyProductName, 10, 10)
        assertFalse(isValidEmptyProductName)  // Should return false because empty product names are invalid

        // Trying to set a blank product name (will fail initially)
        val isValidBlankProductName = viewModel.setStockLevel(blankProductName, 10, 10)
        assertFalse(isValidBlankProductName)  // Should return false because blank product names are invalid

        // Trying to set a valid product name (should pass)
        val isValidProductName = viewModel.setStockLevel(validProductName, 10, 10)
        assertTrue(isValidProductName)   // Should return true because non-empty product names are valid
    }

    /**
     * Tests that setStockLevel should return false if the product name is empty.
     * Expected behavior: Returns false when an invalid (empty) product name is provided.
     */
    @Test
    fun `setStockLevel should return false for empty product name`() {
        // Arrange
        val productName = "" // empty product name
        val stockLevel = 10
        val restockThreshold = 5

        // Act
        val result = viewModel.setStockLevel(productName, stockLevel, restockThreshold)

        // Assert
        assertFalse(result) // Expecting false because the product name is invalid
    }
}
