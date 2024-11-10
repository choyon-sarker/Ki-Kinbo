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

    // Rule to execute tasks synchronously on the main thread during testing
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        // Initialize repository and ViewModel with hardcoded data
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
        assertNull(viewModel.orderStatus.value)
    }
}
