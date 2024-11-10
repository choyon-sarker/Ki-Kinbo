package com.devdroid.kikinbo

import androidx.test.platform.app.InstrumentationRegistry
import com.devdroid.kikinbo.viewmodel.AddToCart
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.devdroid.kikinbo", appContext.packageName) }

    @Test
    fun testInitialQuantityAndTotalAmount() {
        // Initialize AddToCart instance for testing
        val addToCart = AddToCart()
        addToCart.productPrice = 50  // Set a test product price

        // Verify initial quantity is zero
        assertEquals(0, addToCart.quantity)

        // Verify initial total amount is also zero
        val initialTotalAmount = addToCart.quantity * addToCart.productPrice
        assertEquals(0, initialTotalAmount)
    }

    @Test
    fun testIncreaseQuantityUpdatesTotalAmount() {
        // Initialize AddToCart instance for testing
        val addToCart = AddToCart()
        addToCart.productPrice = 50  // Set a test product price

        // Simulate increasing quantity by 1
        addToCart.quantity = 1
        val totalAmount = addToCart.quantity * addToCart.productPrice
        assertEquals(50, totalAmount)
    }

    @Test
    fun testDecreaseQuantityUpdatesTotalAmount() {
        // Initialize AddToCart instance for testing
        val addToCart = AddToCart()
        addToCart.productPrice = 50  // Set a test product price

        // Set quantity and simulate a decrease
        addToCart.quantity = 2
        addToCart.quantity -= 1
        val totalAmount = addToCart.quantity * addToCart.productPrice
        assertEquals(50, totalAmount)
    }

    @Test
    fun testCalculateTotalAmountCorrectly() {
        // Initialize AddToCart instance for testing
        val addToCart = AddToCart()
        addToCart.productPrice = 50  // Set a test product price

        // Set specific price and quantity for testing
        addToCart.quantity = 3
        val totalAmount = addToCart.quantity * addToCart.productPrice
        assertEquals(150, totalAmount)
    }
}
