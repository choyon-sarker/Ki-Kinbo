package com.devdroid.kikinbo.model

/**
 * Represents the stock information of a product in inventory.
 *
 * This data class holds details about a product's unique identifier, its name,
 * and the current available stock quantity.
 *
 * @property productId The unique identifier of the product.
 * @property productName The name of the product.
 * @property availableStock The number of units available in stock for this product.
 */
data class ProductStockInto(
    val productId: String,
    val productName: String,
    val availableStock: Int
)
