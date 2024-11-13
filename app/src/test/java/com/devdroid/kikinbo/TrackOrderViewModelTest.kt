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
    private lateinit var repository: TrackOrderRepository
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
        val observer = Observer<String> { status ->
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
        viewModel.loadOrderStatus(invalidOrderId)
        assertEquals(expectedStatus, viewModel.orderStatus.value)
    }

    @Test
    fun loadOrderStatusValidOrderIdUpdatesLiveDataWithStatus() {
        val validOrderId = "ORDER123"
        val expectedStatus = "Pending"
        viewModel.loadOrderStatus(validOrderId)
        assertEquals(expectedStatus, viewModel.orderStatus.value)
    }

    @Test
    fun resetOrderStatusClearsLiveData() {
        viewModel.loadOrderStatus("ORDER123")
        assertEquals("Pending", viewModel.orderStatus.value)
        viewModel.resetOrderStatus()
        assertEquals("", viewModel.orderStatus.value)
    }

    @Test
    fun `when refreshOrderStatus is called, it should update the status to the latest data from repository`() {
        val repository = FakeTrackOrderRepository()
        val viewModel = TrackOrderViewModel(repository)
        repository.setOrderStatus("ORDER123", "Shipped")
        viewModel.loadOrderStatus("ORDER123")
        assertEquals("Shipped", viewModel.orderStatus.value)
        repository.setOrderStatus("ORDER123", "Delivered")
        assertNotEquals("Delivered", viewModel.orderStatus.value)
        viewModel.refreshOrderStatus()
        assertEquals("Delivered", viewModel.orderStatus.value)
    }



}
