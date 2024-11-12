package com.devdroid.kikinbo.viewmodel

/**
 * ViewModel for managing and validating stock levels.
 * This ViewModel is responsible for setting and validating the product name, stock level, and restock threshold,
 * as well as determining whether an alert message should be shown when stock is low.
 */
class StockLevelViewModel {

    private var stockLevel: Int = 0
    private var restockThreshold: Int = 0
    private var isLowStock: Boolean = false
    private var productName: String = ""

    /**
     * Sets the product name, stock level, and restock threshold with validation.
     *
     * @param productName The name of the product. Must not be empty or blank.
     * @param stockLevel The current stock level of the product. Must be a non-negative integer.
     * @param restockThreshold The minimum stock level that triggers a restock alert. Must be a non-negative integer.
     *
     * @return Returns true if all inputs are valid; returns false if any input is invalid.
     */
    fun setStockLevel(productName: String, stockLevel: Int, restockThreshold: Int): Boolean {
        // Validate the product name, stock level, and restock threshold
        if (productName.isBlank()) {
            return false  // Invalid product name (empty or blank)
        }

        if (stockLevel < 0) {
            return false  // Invalid stock level (negative value)
        }

        if (restockThreshold < 0) {
            return false  // Invalid restock threshold (negative value)
        }

        // Assign the values to the properties
        this.productName = productName
        this.stockLevel = stockLevel
        this.restockThreshold = restockThreshold

        // Check if stock level is below the restock threshold
        checkStockLevel()

        return true  // Valid product name, stock level, and threshold
    }

    /**
     * Checks if the current stock level is below the restock threshold.
     * Updates the `isLowStock` property based on this comparison.
     */
    private fun checkStockLevel() {
        isLowStock = stockLevel < restockThreshold
    }

    /**
     * Determines whether the alert message should be visible.
     * The alert message is shown when the stock level is below the restock threshold.
     *
     * @return Returns true if the stock level is below the restock threshold; returns false otherwise.
     */
    fun isAlertMessageVisible(): Boolean {
        return isLowStock
    }
}
