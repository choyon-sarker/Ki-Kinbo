package com.devdroid.kikinbo.repository

/**
 * Repository interface for tracking the status of an order.
 */
interface TrackOrderRepository {
    /**
     * Retrieves the status of an order by its unique identifier.
     *
     * @param orderId Unique identifier for the order.
     * @return The status of the order if found, or `null` if not found.
     */
    fun getOrderStatus(orderId: String): String?
}

/**
 * A fake implementation of [TrackOrderRepository] used for testing purposes.
 * Stores order statuses in a local map to simulate repository behavior.
 */
class FakeTrackOrderRepository : TrackOrderRepository {
    // Map to store the status of orders by their unique identifiers.
    private val orderStatusMap = mutableMapOf<String, String>()

    /**
     * Sets the status of an order in the repository.
     *
     * @param orderId Unique identifier for the order.
     * @param status The status to be associated with the order.
     */
    fun setOrderStatus(orderId: String, status: String) {
        orderStatusMap[orderId] = status
    }

    /**
     * Retrieves the status of an order by its unique identifier.
     *
     * @param orderId Unique identifier for the order.
     * @return The status of the order if found, or `null` if not found.
     */
    override fun getOrderStatus(orderId: String): String? {
        return orderStatusMap[orderId]
    }
}
