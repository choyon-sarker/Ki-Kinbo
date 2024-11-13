package com.devdroid.kikinbo.repository

interface TrackOrderRepository {
    fun getOrderStatus(orderId: String): String?
}

class FakeTrackOrderRepository : TrackOrderRepository {
    private val orderStatuses = mutableMapOf<String, String>()

    // Method for testing: Set order status manually
    fun setOrderStatus(orderId: String, status: String) {
        orderStatuses[orderId] = status
    }

    // Method to get the order status from the fake repository
    override fun getOrderStatus(orderId: String): String? {
        return orderStatuses[orderId]
    }
}



