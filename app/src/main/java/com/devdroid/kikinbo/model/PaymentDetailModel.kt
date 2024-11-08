package com.devdroid.kikinbo.model

// Data class with primary constructor parameters
data class PaymentDetails(
    val amount: Int,          // Payment amount
    val paymentMethod: String // Payment method (e.g., Credit Card, Cash, etc.)
)
