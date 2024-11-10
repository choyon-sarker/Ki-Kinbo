package com.devdroid.kikinbo.viewmodel

class PayBillViewModel {

    private val maxPaymentLimit = 10000
    private val validPaymentMethods = listOf("Cash on delivery", "Bkash", "Credit/Debit Card")
    fun validatePaymentAmount(amount: Any): Boolean {
        // Convert to Int if it's a string, otherwise check if it's an Int
        val amountInt = (amount as? String)?.toIntOrNull()
        return (amountInt ?: amount as? Int)?.let {
            it in 1..maxPaymentLimit
        } ?: false
    }
    fun validatePaymentMethod(paymentMethod: String): Boolean {
        return paymentMethod.isNotEmpty() && validPaymentMethods.contains(paymentMethod)
    }
}
