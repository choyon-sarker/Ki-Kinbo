package com.devdroid.kikinbo.model

import java.io.Serializable

/**
 * Represents an item in an order.
 *
 * @property productId The unique identifier for the product being ordered.
 * @property productName The name of the product.
 * @property quantity The number of units of the product being ordered.
 * @property price The price per unit of the product.
 */
data class OrderItemDataModel(
    val productId: String,
    val productName: String,
    val availableStock: Int,
    val quantity: Int,
    val price: Int
):Serializable
