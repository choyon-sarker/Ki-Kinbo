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
            return when (order?.orderStatus) {
                "Pending" -> {
                    orderCanceled = true
                    toastMessage = "Order canceled successfully"
                    true
                }
                "Shipping" -> {
                    orderCanceled = false
                    toastMessage = "Your order can be canceled with a penalty. A 10% charge will be applied to your product price"
                    false
                }
                "Delivered" -> {
                    orderCanceled = false
                    toastMessage = "Your order cannot be cancelled because it has already been delivered"
                    false
                }
                else -> {
                    orderCanceled = false
                    toastMessage = "Order ID is not valid for cancellation"
                    false
                }
            }
        } else {
            orderCanceled = false
            toastMessage = "Order not found. Please verify the order number"
            return false
        }
    }
}


