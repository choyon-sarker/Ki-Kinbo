package com.devdroid.kikinbo.viewmodel

class PayBillViewModel {

    private val maxPaymentLimit = 10000

    fun validatePaymentAmount(amount: Int): Boolean {
        return amount > 0 && amount <= maxPaymentLimit
    }
    fun validatePaymentMethod(paymentMethod: String): Boolean {
        return paymentMethod.isNotEmpty()
    }

}
