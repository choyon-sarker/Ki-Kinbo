package com.devdroid.kikinbo.viewmodel

class PayBillViewModel {


    fun validatePaymentAmount(amount: Int): Boolean {
        return amount > 0  // Only positive amounts are considered valid
    }
}


