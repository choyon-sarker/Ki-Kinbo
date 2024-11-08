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
        // Setup an observer for the LiveData from the ViewModel
        val observer = Observer<String> { status ->
            // Assert that the status is "Pending" for the hardcoded order
            assertEquals("Pending", status)
        }

        // Observe the LiveData
        viewModel.orderStatus.observeForever(observer)

        // Call the function to load order status for the valid orderId
        viewModel.loadOrderStatus(orderId)

        // Clean up the observer
        viewModel.orderStatus.removeObserver(observer)
    }
}
