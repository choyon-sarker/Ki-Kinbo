package com.devdroid.kikinbo.model

/**
 * Represents an order placed by a user.
 *
 * @property userId The unique identifier for the user placing the order.
 * @property items A list of items included in the order. Each item contains details about the product.
 * @property shippingAddress The shipping address where the order will be delivered.
// * @property paymentInfo The payment details for processing the order.
 * @property orderDate The timestamp indicating when the order was placed.
 * @property totalAmount The total amount for the order, including all items and applicable fees.
 */
data class OrderDataModel(
    val userId: String?=null,
    val items: List<OrderItemDataModel?>,
    val shippingAddress: ShippingAddressDataModel?=null,
    val orderDate: Long?=null,
    val totalAmount: Int?=null
)
