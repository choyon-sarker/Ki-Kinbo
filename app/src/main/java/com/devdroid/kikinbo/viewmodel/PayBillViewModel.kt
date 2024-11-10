package com.devdroid.kikinbo.viewmodel

class PayBillViewModel {

    private val maxPaymentLimit = 10000
    private val validPaymentMethods = listOf("Cash on delivery", "Bkash", "Credit/Debit Card")
    fun validatePaymentAmount(amount: Int): Boolean {
        return amount > 0 && amount < maxPaymentLimit
    }
    fun validatePaymentMethod(paymentMethod: String): Boolean {
        return paymentMethod.isNotEmpty() && validPaymentMethods.contains(paymentMethod)
    }
}
