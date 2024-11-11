package com.devdroid.kikinbo.viewmodel

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Unit test class for testing the [CancelOrderViewModel].
 *
 * This class provides unit tests for various functionalities within the [CancelOrderViewModel].
 * The tests cover scenarios like validating empty, invalid, and valid order IDs.
 */
class CancelOrderViewModelTest {

    // Instance of the ViewModel to be tested
    private lateinit var viewModel: CancelOrderViewModel

    /**
     * Initializes the ViewModel instance before each test.
     *
     * This setup method is executed before each test to ensure a fresh instance of
     * [CancelOrderViewModel] for consistent test results.
     */
    @BeforeEach
    fun setUp() {
        // Initialize the ViewModel before each test
        viewModel = CancelOrderViewModel()
    }

    /**
     * Tests the behavior of [CancelOrderViewModel.validOrderId] when an empty order ID is provided.
     *
     * This test verifies that an empty order ID results in a validation failure,
     * with [validOrderId] returning `false` and `toastMessage` containing an appropriate error message.
     */
    @Test
    fun testNullOrderId() {
        val result = viewModel.validOrderId("")
        assertFalse(result)
        assertEquals("Order ID is empty", viewModel.toastMessage)
    }

    /**
     * Tests the behavior of [CancelOrderViewModel.validOrderId] when an invalid order ID is provided.
     *
     * This test verifies that an invalid order ID (e.g., "order000") results in a validation failure,
     * with [validOrderId] returning `false` and `toastMessage` containing an appropriate error message.
     */
    @Test
    fun testInvalidOrderID() {
        // Test an invalid order ID
        val result = viewModel.validOrderId("order000")
        assertFalse(result)
        assertEquals("Order not found. Please verify the order number", viewModel.toastMessage)
    }

    /**
     * Tests the behavior of [CancelOrderViewModel.validOrderId] when a valid order ID is provided.
     *
     * This test verifies that a valid order ID (e.g., "order001") results in a successful validation,
     * with [validOrderId] returning `true` and `toastMessage` containing a success message.
     */
    @Test
    fun validOrderId() {
        val result = viewModel.validOrderId("order001")
        assertTrue(result)
        assertEquals("Order ID is valid", viewModel.toastMessage)
    }

    /**
     * Tests the behavior of [CancelOrderViewModel.orderCanceled] when a valid order ID with a "Pending" status is provided.
     *
     * This test verifies that providing a valid order ID (e.g., "order001") with a "Pending" status in [DummyOrderDatabase]
     * allows for a successful order cancellation. It checks that [orderCanceled] returns `true` and `toastMessage`
     * contains "Order canceled successfully".
     *
     * Expected Result:
     * - [orderCanceled] returns `true`.
     * - `toastMessage` contains "Order canceled successfully".
     */
    @Test
    fun validOrderIdOrderStatusPending() {
        val result = viewModel.orderCanceled("order001")
        assertTrue(result)
        assertEquals("Order canceled successfully", viewModel.toastMessage)
    }
}
