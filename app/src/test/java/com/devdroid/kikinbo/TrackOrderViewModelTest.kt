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
    fun loadOrderStatus_invalidOrderId_updatesLiveDataWithError() {
        // Arrange
        val invalidOrderId = "INVALID123" // An order ID that doesn't exist in our "repository"
        val expectedStatus = "Order not found"

        // Act
        viewModel.loadOrderStatus(invalidOrderId)

        // Assert
        assertEquals(expectedStatus, viewModel.orderStatus.value)
    }

}
