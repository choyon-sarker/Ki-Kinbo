package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.repository.DummyOrderDatabase
import com.devdroid.kikinbo.model.OrderInfo

class CancelOrderViewModel {
    // Regular variables to store messages and order status
    var toastMessage: String? = null

    // Boolean variable to track if the order has been placed
    var orderCanceled: Boolean = false

    // Create an instance of DummyDataStore
    val dataStore = DummyOrderDatabase()

    // Method to retrieve an order by its ID
    fun getOrderById(orderId: String): OrderInfo? {
        return dataStore.orders[orderId]
    }

    fun validOrderId(orderId: String): Boolean {
        if (orderId.isEmpty()) {
            toastMessage = "Order ID is empty"
            return false
        }else if(orderId!=null){
            val order = getOrderById(orderId)
            //val storedOrder = dataStore.orders[orderId]
            // Check if the user exists in the dummyUserDatabase and matches the retrieved user
            if (order != null) {
                if (orderId==order.orderId) {
                    toastMessage = "Order ID is valid"
                    return true
                } else {
                    toastMessage = "Order not found. Please verify the order number"
                    return false
                }
            }else{
                toastMessage = "Order not found. Please verify the order number"
                return false
            }
        }
        else{
            return false
        }
    }

    fun orderCanceled(orderId: String) :Boolean {
        if (validOrderId(orderId)) {
            val order = getOrderById(orderId)
            if (order?.orderStatus == "Pending") {
                orderCanceled = true
                toastMessage = "Your order is cancelled"
                return true
            } else {
                toastMessage = "Order cannot be canceled because it is not in 'Pending' status."
                return false
            }
        } else {
            orderCanceled = false
            toastMessage = "Order not found. Please verify the order number"
            return false
        }
    }
}


