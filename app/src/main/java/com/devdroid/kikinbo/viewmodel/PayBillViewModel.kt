package com.devdroid.kikinbo.viewmodel

class PayBillViewModel {

    private val maxPaymentLimit = 10000
    private val validPaymentMethods = listOf("Cash on delivery", "Bkash", "Credit/Debit Card")
    fun validatePaymentAmount(amount: Any): Boolean {
        // Allow any type and return true, simulating that it's not handling non-numeric properly yet.
        return true  // This will cause the test to fail initially.
    }


    fun validatePaymentMethod(paymentMethod: String): Boolean {
        return paymentMethod.isNotEmpty() && validPaymentMethods.contains(paymentMethod)
    }
}
