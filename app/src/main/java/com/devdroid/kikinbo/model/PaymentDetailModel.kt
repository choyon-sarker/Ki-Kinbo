package com.devdroid.kikinbo.model

/**
 * Data model representing payment details for an order.
 *
 * @property amount The total amount of the payment in the relevant currency.
 * @property paymentMethod The method used for the payment (e.g., "Credit Card", "PayPal").
 */
data class PaymentDetails(
    val amount: Int,
    val paymentMethod: String
)
