package com.devdroid.kikinbo.viewmodel

/**
 * ViewModel for handling the validation logic related to bill payment.
 *
 * This ViewModel provides methods to validate the payment amount and method before proceeding with the payment process.
 */
class PayBillViewModel {

    // The maximum allowed payment limit
    private val maxPaymentLimit = 10000

    // List of valid payment methods supported by the application
    private val validPaymentMethods = listOf("Cash on delivery", "Bkash", "Credit/Debit Card")

    /**
     * Validates the payment amount to ensure it is within a valid range (1 to maxPaymentLimit).
     * It handles both string and integer input types for the amount.
     *
     * @param amount The payment amount, which can be provided as either a String or an Int.
     * @return `true` if the amount is valid (between 1 and maxPaymentLimit), `false` otherwise.
     */
    fun validatePaymentAmount(amount: Any): Boolean {
        // Convert to Int if it's a string, otherwise check if it's an Int
        val amountInt = (amount as? String)?.toIntOrNull()
        return (amountInt ?: amount as? Int)?.let {
            it in 1..maxPaymentLimit
        } ?: false
    }

    /**
     * Validates the payment method to check if it is supported by the application.
     *
     * @param paymentMethod The payment method to be validated (e.g., "Bkash", "Cash on delivery").
     * @return `true` if the payment method is valid, `false` if it is not in the list of supported methods.
     */
    fun validatePaymentMethod(paymentMethod: String): Boolean {
        return paymentMethod.isNotEmpty() && validPaymentMethods.contains(paymentMethod)
    }
}
