package com.devdroid.kikinbo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devdroid.kikinbo.repository.TrackOrderRepository

/**
 * ViewModel for tracking the status of an order.
 * Manages the data and communication with the repository to fetch order status.
 *
 * @property repository The repository used to access order status data.
 */
class TrackOrderViewModel(private val repository: TrackOrderRepository) : ViewModel() {

    // LiveData to observe the current order status.
    private val _orderStatus = MutableLiveData<String?>()
    val orderStatus: LiveData<String?> get() = _orderStatus

    // The ID of the order currently being tracked.
    private var _orderId: String? = null

    /**
     * Loads the status of a specific order by its ID.
     *
     * @param orderId The unique identifier of the order to be tracked.
     */
    fun loadOrderStatus(orderId: String) {
        _orderId = orderId
        _orderStatus.value = repository.getOrderStatus(orderId)
    }

    /**
     * Resets the current order status to `null`.
     * Useful for clearing any existing status data.
     */
    fun resetOrderStatus() {
        _orderStatus.value = null
    }

    /**
     * Refreshes the status of the currently loaded order by re-fetching it from the repository.
     * If no order ID is set, this function does nothing.
     */
    fun refreshOrderStatus() {
        _orderId?.let {
            _orderStatus.value = repository.getOrderStatus(it)
        }
    }
}
