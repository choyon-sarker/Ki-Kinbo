package com.devdroid.kikinbo.viewmodel

class PayBillViewModel {

    private val maxPaymentLimit = 10000
    private val validPaymentMethods = listOf("Cash on delivery", "Bkash", "Credit/Debit Card")
    fun validatePaymentAmount(amount: Int): Boolean {
        if (amount <= 0) {
            throw Exception("Payment submission failed due to network/server error.")  // Simulate network/server failure
        }
        return amount in 1..maxPaymentLimit
    }

    fun validatePaymentMethod(paymentMethod: String): Boolean {
        if (!validPaymentMethods.contains(paymentMethod)) {
            throw Exception("Payment submission failed. Invalid payment method.")  // Simulate failure
        }
        return true
    }

}
