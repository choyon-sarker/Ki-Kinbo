package com.devdroid.kikinbo.viewmodel

/**
 * ViewModel for managing and validating stock levels.
 * This ViewModel is responsible for setting and validating the stock level and restock threshold,
 * as well as determining whether an alert message should be shown.
 */
class StockLevelViewModel {

    private var stockLevel: Int = 0
    private var restockThreshold: Int = 0
    private var isLowStock: Boolean = false

    /**
     * Sets the stock level and restock threshold for a product, with validation.
     *
     * @param productName The name of the product (currently not used for validation but can be extended).
     * @param stockLevel The current stock level of the product.
     * @param restockThreshold The minimum stock level that triggers a restock alert.
     *
     * @return Returns true if the stock level and restock threshold are valid, false if any are invalid.
     */
    fun setStockLevel(productName: String, stockLevel: Int, restockThreshold: Int): Boolean {
        // Validate the stock level and restock threshold
        if (stockLevel < 0) {
            return false  // Invalid stock level
        }

        if (restockThreshold < 0) {
            return false  // Invalid restock threshold
        }

        // Assign the values to the properties
        this.stockLevel = stockLevel
        this.restockThreshold = restockThreshold

        // Check if stock level is below the restock threshold
        checkStockLevel()

        return true  // Valid stock level and threshold
    }

    /**
     * Checks if the current stock level is below the restock threshold.
     * This method updates the value of the `isLowStock` property.
     */
    private fun checkStockLevel() {
        isLowStock = stockLevel < restockThreshold
    }

    /**
     * Determines whether the alert message should be visible.
     * The alert message is shown when the stock level is below the restock threshold.
     *
     * @return Returns true if the stock level is below the restock threshold, false otherwise.
     */
    fun isAlertMessageVisible(): Boolean {
        return isLowStock
    }
}
