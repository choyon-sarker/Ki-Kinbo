package com.devdroid.kikinbo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.devdroid.kikinbo.repository.FakeTrackOrderRepository
import com.devdroid.kikinbo.repository.TrackOrderRepository
import com.devdroid.kikinbo.viewmodel.TrackOrderViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TrackOrderViewModelTest {

    private lateinit var viewModel: TrackOrderViewModel
    private lateinit var repository: FakeTrackOrderRepository
    private val orderId = "ORDER123"

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = FakeTrackOrderRepository()
        viewModel = TrackOrderViewModel(repository)
    }

    @Test
    fun testInitialOrderStatusLoading() {
        repository.setOrderStatus(orderId, "Pending")
        val observer = Observer<String?> { status ->
            assertEquals("Pending", status)
        }
        viewModel.orderStatus.observeForever(observer)
        viewModel.loadOrderStatus(orderId)
        viewModel.orderStatus.removeObserver(observer)
    }

    @Test
    fun loadOrderStatusInvalidOrderIdUpdatesLiveDataWithError() {
        val invalidOrderId = "INVALID123"
        val expectedStatus = "Order not found"
        repository.setOrderStatus(invalidOrderId, expectedStatus)
        viewModel.loadOrderStatus(invalidOrderId)
        assertEquals(expectedStatus, viewModel.orderStatus.value)
    }

    @Test
    fun loadOrderStatusValidOrderIdUpdatesLiveDataWithStatus() {
        repository.setOrderStatus(orderId, "Pending")
        viewModel.loadOrderStatus(orderId)
        assertEquals("Pending", viewModel.orderStatus.value)
    }

    @Test
    fun resetOrderStatusClearsLiveData() {
        repository.setOrderStatus(orderId, "Pending")
        viewModel.loadOrderStatus(orderId)
        assertEquals("Pending", viewModel.orderStatus.value)
        viewModel.resetOrderStatus()
        assertNull(viewModel.orderStatus.value)
    }

    @Test
    fun whenRefreshOrderStatusIsCalled_orderStatusShouldBeUpdatedWithLatestData() {
        repository.setOrderStatus(orderId, "Shipped")
        viewModel.loadOrderStatus(orderId)
        assertEquals("Shipped", viewModel.orderStatus.value)
        repository.setOrderStatus(orderId, "Delivered")
        viewModel.refreshOrderStatus()
        assertEquals("Delivered", viewModel.orderStatus.value)
    }
}
