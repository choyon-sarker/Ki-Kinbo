package com.devdroid.kikinbo

import com.devdroid.kikinbo.viewmodel.PayBillViewModel
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class PayBillViewModelTest {

    private lateinit var payBillViewModel: PayBillViewModel
    private val viewModel = PayBillViewModel()

    @Before
    fun setUp() {
        payBillViewModel = PayBillViewModel()

    }

    @Test
    fun validatePaymentAmountZeroAmountReturnsError() {
        // Arrange: Define the test input (amount = 0)
        val amount = 0

        // Act: Call the validatePaymentAmount method
        val result = payBillViewModel.validatePaymentAmount(amount)

        // Assert: Check that the result indicates an error
        assertFalse("Payment amount should not be zero or negative", result)
    }

    @Test
    fun validatePaymentAmountNegativeAmountReturnsError() {
        // Arrange: Define the test input (amount = -100)
        val amount = -100

        // Act: Call the validatePaymentAmount method
        val result = payBillViewModel.validatePaymentAmount(amount)

        // Assert: Check that the result indicates an error
        assertFalse("Payment amount should not be zero or negative", result)
    }
    @Test
    fun validatePaymentMethodEmptyMethodReturnsError() {
        val paymentMethod = ""
        val result = viewModel.validatePaymentMethod(paymentMethod)
        assertFalse("Payment method should not be empty", result)
    }
    @Test
    fun validatePaymentAmountExceedsLimitReturnsError() {
        val paymentAmount = 15000 // Exceeds the maximum limit of 10,000
        val result = viewModel.validatePaymentAmount(paymentAmount)
        assertFalse("Payment amount should not exceed maximum allowed limit", result)
    }

}
