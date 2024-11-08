package com.devdroid.kikinbo.viewmodel

class PayBillViewModel {


    fun validatePaymentAmount(amount: Int): Boolean {
        return amount > 0
    }
    fun validatePaymentMethod(paymentMethod: String): Boolean {
        return true
    }
}


