package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.repository.DummyOrderDatabase
import com.devdroid.kikinbo.model.OrderInfo

/**
 * ViewModel class responsible for handling order cancellation operations.
 * This class interacts with a dummy order database and manages order validation and cancellation
 * based on the provided order ID and current order status.
 */
class CancelOrderViewModel {

    /** Message to be displayed to the user after an order operation, e.g., success or error messages. */
    var toastMessage: String? = null

    /** Boolean flag indicating if an order has been successfully canceled. */
    var orderCanceled: Boolean = false

    /** Boolean indicating if the system is in maintenance mode, preventing cancellations. */
    val maintainstatus: Boolean = false

    /** Data store instance representing a mock order database. */
    val dataStore = DummyOrderDatabase()

    /**
     * Retrieves an order by its ID.
     * @param orderId The ID of the order to retrieve.
     * @return The [OrderInfo] object if found, or null if the order does not exist.
     */
    fun getOrderById(orderId: String): OrderInfo? {
        return dataStore.orders[orderId]
    }

    /**
     * Validates if an order ID is valid and exists in the database.
     * Checks if the order ID is non-empty and matches an order in the database.
     * @param orderId The ID of the order to validate.
     * @return `true` if the order ID is valid and exists; `false` otherwise, updating [toastMessage] accordingly.
     */
    fun validOrderId(orderId: String): Boolean {
        if (orderId.isEmpty()) {
            toastMessage = "Order ID is empty"
            return false
        } else if (orderId != null) {
            val order = getOrderById(orderId)
            if (order != null) {
                return if (orderId == order.orderId) {
                    toastMessage = "Order ID is valid"
                    true
                } else {
                    toastMessage = "Order not found. Please verify the order number"
                    false
                }
            } else {
                toastMessage = "Order not found. Please verify the order number"
                return false
            }
        } else {
            return false
        }
    }

    /**
     * Attempts to cancel an order based on its ID and current status.
     * The cancellation is successful if the order status is "Pending". Specific messages are set in [toastMessage]
     * based on the order status or if the system is in maintenance mode.
     *
     * @param orderId The ID of the order to cancel.
     * @return `true` if the cancellation was successful; `false` otherwise.
     *
     * Possible outcomes:
     * - "Pending": Order is canceled successfully, setting [toastMessage] to "Order canceled successfully".
     * - "Shipping": Order requires a 10% penalty fee, setting [toastMessage] to reflect this.
     * - "Delivered": Order cannot be canceled after delivery, setting [toastMessage] to indicate this.
     * - "Cancelled": Order is already canceled, setting [toastMessage] accordingly.
     * - Other statuses: Order is not valid for cancellation, setting [toastMessage] to indicate this.
     * - Maintenance mode: Cancellation is unavailable due to technical issues, with an appropriate message.
     */
    fun orderCanceled(orderId: String): Boolean {
        if (validOrderId(orderId)) {
            val order = getOrderById(orderId)
            if (order != null) {
                if (!order.maintainStatus) {
                    return when (order.orderStatus) {
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
                        "Cancelled" -> {
                            orderCanceled = false
                            toastMessage = "Your order has already been cancelled"
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
                    toastMessage = "We are unable to cancel your order at the moment due to a technical issue. Please try again later"
                    return false
                }
            }
            orderCanceled = false
            toastMessage = "Order not found. Please verify the order number"
            return false
        } else {
            orderCanceled = false
            toastMessage = "Order not found. Please verify the order number"
            return false
        }
    }
}
