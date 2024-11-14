package com.devdroid.kikinbo.repository

interface TrackOrderRepository {
    fun getOrderStatus(orderId: String): String?
}

class FakeTrackOrderRepository : TrackOrderRepository {
    private val orderStatusMap = mutableMapOf<String, String>()

    fun setOrderStatus(orderId: String, status: String) {
        orderStatusMap[orderId] = status
    }

    override fun getOrderStatus(orderId: String): String? {
        return orderStatusMap[orderId] // Fixed to use orderStatusMap
    }

}
