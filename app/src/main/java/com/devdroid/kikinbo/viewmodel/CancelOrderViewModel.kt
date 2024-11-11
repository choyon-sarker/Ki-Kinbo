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
                    toastMessage = "Order not found. Please verify the order number."
                    return false
                }
            }else{
                toastMessage = "Order not found. Please verify the order number."
                return false
            }
        }
        else{
            return false
        }
    }

    fun orderCanceled(orderId: String) :Boolean {
        if(validOrderId(orderId)){
            orderCanceled = true
            toastMessage = "Order canceled successfully"
            return true
        }else{
            orderCanceled = false
            toastMessage = "Order ID is not valid"
            return false
        }
    }
}


