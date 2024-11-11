package com.devdroid.kikinbo.viewmodel

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CancelOrderViewModelTest{
    private lateinit var viewModel:CancelOrderViewModel

    @BeforeEach
    fun setUp() {
        // Initialize the ViewModel before each test
        viewModel = CancelOrderViewModel()
    }

    @Test
    fun testValidOrderID() {
        // Test a valid user ID
        val result = viewModel.validOrderId("user00031")
        assertTrue(result)
        assertEquals("Order ID is invalid", viewModel.toastMessage)
    }
    @Test
    fun invalidOrderId(){
        val result=viewModel.validOrderId("user001")
        assertTrue(result)
        assertEquals("Order ID is valid",viewModel.toastMessage)
    }
}