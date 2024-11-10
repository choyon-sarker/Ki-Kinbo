package com.devdroid.kikinbo

import com.devdroid.kikinbo.viewmodel.PayBillViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
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
    fun validatePaymentAmountExceedsLimitReturnsError() {
        val paymentAmount = 15000
        val result = viewModel.validatePaymentAmount(paymentAmount)
        assertFalse("Payment amount should not exceed maximum allowed limit", result)
    }
    @Test
    fun validatePaymentMethodInvalidMethodReturnsError() {
        val invalidPaymentMethod = "Nogod"
        val result = viewModel.validatePaymentMethod(invalidPaymentMethod)
        assertFalse("Payment method should be one of the predefined options", result)
    }
    @Test
    fun validatePaymentAmountMaximumLimitReturnsSuccess() {
        val amount = 10000
        val result = payBillViewModel.validatePaymentAmount(amount)
        assertTrue("Payment amount should be accepted as it is within the maximum limit", result)
    }
//    @Test
//    fun validatePaymentAmountNonNumericStringReturnsError() {
//        val amount = "fifty"
//        val result = payBillViewModel.validatePaymentAmount(amount)
//        assertFalse("Payment amount should be numeric", result)  // This should fail now
//    }
    @Test
    fun validatePaymentAmountZeroAmountReturnsError() {
    val amount = 0
    val result = payBillViewModel.validatePaymentAmount(amount)
    assertFalse("Payment amount should not be zero or negative", result)
    }

    @Test
    fun validatePaymentAmountNegativeAmountReturnsError() {
        val amount = -100
        val result = payBillViewModel.validatePaymentAmount(amount)
        assertFalse("Payment amount should not be zero or negative", result)
    }
    @Test
    fun validatePaymentMethodEmptyMethodReturnsError() {
        val paymentMethod = ""
        val result = viewModel.validatePaymentMethod(paymentMethod)
        assertFalse("Payment method should not be empty", result)
    }
    @Test
    fun validatePaymentMethodUncommonOptionReturnsError() {
        val paymentMethod = "Gift Card"  // Unsupported payment method
        val result = payBillViewModel.validatePaymentMethod(paymentMethod)
        assertFalse("Payment method should be one of the predefined options", result)  // This should fail
    }
    @Test
    fun validatePaymentSubmissionFailureWithInvalidDetails() {
        val amount = -500  // Invalid amount
        val paymentMethod = "InvalidMethod"  // Unsupported payment method

        try {
            val resultAmount = payBillViewModel.validatePaymentAmount(amount)
            val resultMethod = payBillViewModel.validatePaymentMethod(paymentMethod)

            // Simulate failure due to invalid details
            assertFalse("Payment submission failed. Invalid amount or method.", resultAmount && resultMethod)
        } catch (e: Exception) {
            // Expected to catch the exception
            assertEquals("Payment submission failed. Invalid amount or method.", e.message)
        }
    }
}
