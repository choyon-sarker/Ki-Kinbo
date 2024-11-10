package com.devdroid.kikinbo.model

data class OrderModel(
    val orderId: String,
    val status: String,
    val expectedDeliveryDate: String
)