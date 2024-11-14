package com.devdroid.kikinbo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.devdroid.kikinbo.repository.FakeTrackOrderRepository
import com.devdroid.kikinbo.viewmodel.TrackOrderViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for the [TrackOrderViewModel] class.
 * Tests the functionality of loading, resetting, and refreshing order status.
 */
class TrackOrderViewModelTest {

    // Instance of the ViewModel under test
    private lateinit var viewModel: TrackOrderViewModel
    // Fake repository to simulate data interactions
    private lateinit var repository: FakeTrackOrderRepository
    // Sample order ID for testing
    private val orderId = "ORDER123"

    /**
     * Rule to allow LiveData to be executed synchronously for testing.
     */
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    /**
     * Sets up the necessary test environment, including initializing the ViewModel and fake repository.
     */
    @Before
    fun setUp() {
        repository = FakeTrackOrderRepository()
        viewModel = TrackOrderViewModel(repository)
    }

    /**
     * Tests that loading an initial order status sets the LiveData to the expected value.
     */
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

    /**
     * Tests that loading an invalid order ID updates the LiveData with an error status.
     */
    @Test
    fun loadOrderStatusInvalidOrderIdUpdatesLiveDataWithError() {
        val invalidOrderId = "INVALID123"
        val expectedStatus = "Order not found"
        repository.setOrderStatus(invalidOrderId, expectedStatus)
        viewModel.loadOrderStatus(invalidOrderId)
        assertEquals(expectedStatus, viewModel.orderStatus.value)
    }

    /**
     * Tests that loading a valid order ID updates the LiveData with the correct status.
     */
    @Test
    fun loadOrderStatusValidOrderIdUpdatesLiveDataWithStatus() {
        repository.setOrderStatus(orderId, "Pending")
        viewModel.loadOrderStatus(orderId)
        assertEquals("Pending", viewModel.orderStatus.value)
    }

    /**
     * Tests that calling `resetOrderStatus` clears the current LiveData value.
     */
    @Test
    fun resetOrderStatusClearsLiveData() {
        repository.setOrderStatus(orderId, "Pending")
        viewModel.loadOrderStatus(orderId)
        assertEquals("Pending", viewModel.orderStatus.value)
        viewModel.resetOrderStatus()
        assertNull(viewModel.orderStatus.value)
    }

    /**
     * Tests that calling `refreshOrderStatus` updates LiveData with the latest order status.
     */
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
