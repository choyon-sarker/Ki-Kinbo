package com.devdroid.kikinbo.viewmodel

class StockLevelViewModel {

    private var stockLevel: Int = 0
    private var restockThreshold: Int = 0
    private var isLowStock: Boolean = false

    // Method to set stock level and threshold with validation
    fun setStockLevel(productName: String, stockLevel: Int, restockThreshold: Int): Boolean {
        if (stockLevel < 0) {
            return false  // Invalid stock level
        }

        if (restockThreshold < 0) {
            return false  // Invalid restock threshold
        }

        this.stockLevel = stockLevel
        this.restockThreshold = restockThreshold
        checkStockLevel()
        return true  // Valid stock level and threshold
    }

    // Check if the stock level is below the restock threshold
    private fun checkStockLevel() {
        isLowStock = stockLevel < restockThreshold
    }

    // Method to get if the alert message should be visible
    fun isAlertMessageVisible(): Boolean {
        return isLowStock
    }
}
