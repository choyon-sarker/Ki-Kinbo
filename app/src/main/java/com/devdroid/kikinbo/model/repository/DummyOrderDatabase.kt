package com.devdroid.kikinbo.model.repository

import com.devdroid.kikinbo.model.OrderInfo

class DummyOrderDatabase {
    // Initialize the dummy order database with sample data
    val orders = hashMapOf(
        "order001" to OrderInfo(
            userId = "user1",
            orderId = "order001",
            orderDateId = "2024-11-01",
            orderStatus = "Pending",
            maintainStatus = false
        ),
        "order002" to OrderInfo(
            userId = "user456",
            orderId = "order002",
            orderDateId = "2024-11-02",
            orderStatus = "Shipping",
            maintainStatus = false

        ),
        "order003" to OrderInfo(
            userId = "user789",
            orderId = "order003",
            orderDateId = "2024-11-03",
            orderStatus = "Delivered",
            maintainStatus = false
        ),
        "order004" to OrderInfo(
            userId = "user101",
            orderId = "order004",
            orderDateId = "2024-11-04",
            orderStatus = "Cancelled",
            maintainStatus = false
        ),
        "order005" to OrderInfo(
            userId = "user202",
            orderId = "order005",
            orderDateId = "2024-11-05",
            orderStatus = "Pending",
            maintainStatus = false
        ),
        "order006M" to OrderInfo(
            userId = "user203",
            orderId = "order006M",
            orderDateId = "2024-11-05",
            orderStatus = "Pending",
            maintainStatus = true
        )
    )

    // Method to retrieve an order by its ID
    fun getOrderById(orderId: String): OrderInfo? {
        return orders[orderId]
    }

    // Method to get all orders for a specific user
    fun getOrdersByUserId(userId: String): List<OrderInfo> {
        return orders.values.filter { it.userId == userId }
    }
}

val dummyDatabase = DummyOrderDatabase()

// Get a specific order by ID
val order = dummyDatabase.getOrderById("order001")

// Get all orders for a specific user
val userOrders = dummyDatabase.getOrdersByUserId("user1")