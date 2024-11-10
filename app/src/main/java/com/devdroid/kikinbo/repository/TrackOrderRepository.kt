package com.devdroid.kikinbo.repository

interface TrackOrderRepository {
    fun getOrderStatus(orderId: String): String
}

class FakeTrackOrderRepository : TrackOrderRepository {
    override fun getOrderStatus(orderId: String): String {
        // Return "Order not found" for any invalid order ID
        return if (orderId == "ORDER123") {
            "Pending"
        } else {
            "Order not found" // For invalid order ID
        }
    }
}

