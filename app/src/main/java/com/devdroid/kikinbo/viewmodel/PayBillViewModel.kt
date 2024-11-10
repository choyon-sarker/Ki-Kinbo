package com.devdroid.kikinbo.viewmodel

class PayBillViewModel {

    private val maxPaymentLimit = 10000

    fun validatePaymentAmount(amount: Int): Boolean {
        // Initially, this does not check the maximum limit
        return amount > 0
    }

    fun validatePaymentMethod(paymentMethod: String): Boolean {
        return paymentMethod.isNotEmpty()
    }
}
