package com.devdroid.kikinbo.repository

interface TrackOrderRepository {
    fun getOrderStatus(orderId: String): String
}

class FakeTrackOrderRepository : TrackOrderRepository {
    override fun getOrderStatus(orderId: String): String {
        // Temporarily set all values to "Order not found" to make the test fail initially
        return "Order not found"
    }
}

