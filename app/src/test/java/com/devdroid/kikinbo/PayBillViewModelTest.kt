package com.devdroid.kikinbo

import com.devdroid.kikinbo.viewmodel.PayBillViewModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Unit test class for testing the validation logic in [PayBillViewModel].
 *
 * This class contains various test cases that validate the functionality of the payment validation methods
 * within the [PayBillViewModel]. The tests include checking for valid and invalid payment amounts and methods.
 */
class PayBillViewModelTest {

    private lateinit var payBillViewModel: PayBillViewModel
    private val viewModel = PayBillViewModel()

    /**
     * Initializes the [PayBillViewModel] before each test.
     */
    @Before
    fun setUp() {
        payBillViewModel = PayBillViewModel()
    }

    /**
     * Test to verify that a payment amount exceeding the maximum limit returns an error.
     *
     * @see [PayBillViewModel.validatePaymentAmount]
     */
    @Test
    fun validatePaymentAmountExceedsLimitReturnsError() {
        val paymentAmount = 15000
        val result = viewModel.validatePaymentAmount(paymentAmount)
        assertFalse("Payment amount should not exceed maximum allowed limit", result)
    }

    /**
     * Test to verify that an invalid payment method returns an error.
     *
     * @see [PayBillViewModel.validatePaymentMethod]
     */
    @Test
    fun validatePaymentMethodInvalidMethodReturnsError() {
        val invalidPaymentMethod = "Nogod"
        val result = viewModel.validatePaymentMethod(invalidPaymentMethod)
        assertFalse("Payment method should be one of the predefined options", result)
    }

    /**
     * Test to verify that the maximum allowed payment amount is accepted as valid.
     *
     * @see [PayBillViewModel.validatePaymentAmount]
     */
    @Test
    fun validatePaymentAmountMaximumLimitReturnsSuccess() {
        val amount = 10000
        val result = payBillViewModel.validatePaymentAmount(amount)
        assertTrue("Payment amount should be accepted as it is within the maximum limit", result)
    }

    /**
     * Test to verify that a non-numeric payment amount returns an error.
     *
     * @see [PayBillViewModel.validatePaymentAmount]
     */
    @Test
    fun validatePaymentAmountNonNumericStringReturnsError() {
        val amount = "fifty"
        val result = payBillViewModel.validatePaymentAmount(amount)
        assertFalse("Payment amount should be numeric", result)  // This should fail now
    }

    /**
     * Test to verify that a payment amount of zero returns an error.
     *
     * @see [PayBillViewModel.validatePaymentAmount]
     */
    @Test
    fun validatePaymentAmountZeroAmountReturnsError() {
        val amount = 0
        val result = payBillViewModel.validatePaymentAmount(amount)
        assertFalse("Payment amount should not be zero or negative", result)
    }

    /**
     * Test to verify that a negative payment amount returns an error.
     *
     * @see [PayBillViewModel.validatePaymentAmount]
     */
    @Test
    fun validatePaymentAmountNegativeAmountReturnsError() {
        val amount = -100
        val result = payBillViewModel.validatePaymentAmount(amount)
        assertFalse("Payment amount should not be zero or negative", result)
    }

    /**
     * Test to verify that an empty payment method returns an error.
     *
     * @see [PayBillViewModel.validatePaymentMethod]
     */
    @Test
    fun validatePaymentMethodEmptyMethodReturnsError() {
        val paymentMethod = ""
        val result = viewModel.validatePaymentMethod(paymentMethod)
        assertFalse("Payment method should not be empty", result)
    }

    /**
     * Test to verify that an uncommon payment method returns an error.
     *
     * @see [PayBillViewModel.validatePaymentMethod]
     */
    @Test
    fun validatePaymentMethodUncommonOptionReturnsError() {
        val paymentMethod = "Gift Card"  // Unsupported payment method
        val result = payBillViewModel.validatePaymentMethod(paymentMethod)
        assertFalse("Payment method should be one of the predefined options", result)  // This should fail
    }
}
