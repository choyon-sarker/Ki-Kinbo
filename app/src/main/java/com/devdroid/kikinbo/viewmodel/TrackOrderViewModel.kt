package com.devdroid.kikinbo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devdroid.kikinbo.repository.TrackOrderRepository

class TrackOrderViewModel(private val repository: TrackOrderRepository) : ViewModel() {

    private val _orderStatus = MutableLiveData<String>()
    val orderStatus: LiveData<String> get() = _orderStatus

    fun loadOrderStatus(orderId: String) {
        // Use the repository to fetch the order status
        _orderStatus.value = repository.getOrderStatus(orderId)
    }
}