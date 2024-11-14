package com.devdroid.kikinbo.model

/**
 * Represents an order with relevant details such as order ID, status, and expected delivery date.
 *
 * @property orderId Unique identifier for the order.
 * @property status Current status of the order (e.g., pending, shipped, delivered).
 * @property expectedDeliveryDate Estimated date of delivery for the order.
 */
data class OrderModel(
    val orderId: String,
    val status: String,
    val expectedDeliveryDate: String
)
