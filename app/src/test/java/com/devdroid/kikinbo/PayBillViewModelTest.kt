package com.devdroid.kikinbo

import com.devdroid.kikinbo.viewmodel.PayBillViewModel
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class PayBillViewModelTest {

    private lateinit var payBillViewModel: PayBillViewModel

    @Before
    fun setUp() {
        // Initialize ViewModel before each test
        payBillViewModel = PayBillViewModel()
    }

    @Test
    fun validatePaymentAmount_zeroAmount_returnsError() {
        // Arrange: Define the test input (amount = 0)
        val amount = 0

        // Act: Call the validatePaymentAmount method
        val result = payBillViewModel.validatePaymentAmount(amount)

        // Assert: Check that the result indicates an error
        assertFalse("Payment amount should not be zero or negative", result)
    }

    @Test
    fun validatePaymentAmount_negativeAmount_returnsError() {
        // Arrange: Define the test input (amount = -100)
        val amount = -100

        // Act: Call the validatePaymentAmount method
        val result = payBillViewModel.validatePaymentAmount(amount)

        // Assert: Check that the result indicates an error
        assertFalse("Payment amount should not be zero or negative", result)
    }
}
