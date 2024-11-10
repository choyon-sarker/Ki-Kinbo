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

}
