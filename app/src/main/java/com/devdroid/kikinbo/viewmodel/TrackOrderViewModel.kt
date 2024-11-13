package com.devdroid.kikinbo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devdroid.kikinbo.repository.TrackOrderRepository


class TrackOrderViewModel(private val repository: TrackOrderRepository) : ViewModel() {

    private val _orderStatus = MutableLiveData<String>()
    val orderStatus: LiveData<String> get() = _orderStatus
    private var _orderId: String? = null

    fun loadOrderStatus(orderId: String) {
        _orderId = orderId
        _orderStatus.value = repository.getOrderStatus(orderId)
    }

    fun resetOrderStatus() {
        _orderStatus.value = ""
    }

    // Method to manually refresh the order status
    fun refreshOrderStatus() {
        _orderId?.let {
            _orderStatus.value = repository.getOrderStatus(it) // Refresh the order status
        }
    }
}

