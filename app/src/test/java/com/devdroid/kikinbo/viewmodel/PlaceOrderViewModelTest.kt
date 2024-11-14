package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.OrderItemDataModel
import com.devdroid.kikinbo.model.ShippingAddressDataModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Unit test class for [PlaceOrderViewModel].
 * Contains tests to validate user ID, phone number, city/division/country,
 * and the place order functionality in various scenarios.
 */
class PlaceOrderViewModelTest {

    private lateinit var viewModel: PlaceOrderViewModel

    /**
     * Initializes the [PlaceOrderViewModel] instance before each test.
     */
    @BeforeEach
    fun setUp() {
        // Initialize the ViewModel before each test
        viewModel = PlaceOrderViewModel()
    }

    /**
     * Tests the validation of user ID is Null or Empty.
     * Asserts that the user ID is Empty and the correct toast message is set.
     */
    @Test
    fun testNullUserId() {
        // Test a Null user ID
        val result = viewModel.validUserId("")
        assertFalse(result)
        assertEquals("User ID is empty", viewModel.toastMessage)
    }

    /**
     * Tests the validation of a valid user ID.
     * Asserts that the user ID is valid and the correct toast message is set.
     */
    @Test
    fun testValidUserId() {
        // Test a valid user ID
        val result = viewModel.validUserId("user1")
        assertTrue(result)
        assertEquals("User ID is valid", viewModel.toastMessage)
    }

    /**
     * Tests the validation of an invalid user ID.
     * Asserts that the user ID is invalid and the correct toast message is set.
     */
    @Test
    fun testInvalidUserId() {
        // Test an invalid user ID
        val result = viewModel.validUserId("invalidUser")
        assertFalse(result)
        assertEquals("User ID is not valid", viewModel.toastMessage)
    }

    /**
     * Tests the validation of a valid phone number.
     * Asserts that the phone number is valid and no error message is set.
     */
    @Test
    fun testValidPhoneNumber() {
        // Test a valid phone number
        val result = viewModel.validUserPhone("01712345678")
        assertTrue(result)
        assertNull(viewModel.toastMessage)
    }

    /**
     * Tests the validation of an invalid phone number (not starting with '01').
     * Asserts that the phone number is invalid and the correct error message is set.
     */
    @Test
    fun testInvalidPhoneNumber() {
        // Test an invalid phone number (not starting with '01')
        val result = viewModel.validUserPhone("02312345678")
        assertFalse(result)
        assertEquals("Phone number must start with '01'", viewModel.toastMessage)
    }

    /**
     * Tests the validation of a valid city, division, and country combination.
     * Asserts that the combination is valid and the correct success message is set.
     */
    @Test
    fun testValidCityDivisionAndCountry() {
        // Test a valid city, division, and country combination
        val result = viewModel.validCityDivisionCountry("Dhaka", "Dhaka", "Bangladesh")
        assertTrue(result)
        assertEquals("Valid city, division, and country!", viewModel.toastMessage)
    }

    /**
     * Tests the validation of an invalid city, division, and country combination.
     * Asserts that the combination is invalid and the correct error message is set.
     */
    @Test
    fun testInvalidCityDivisionAndCountry() {
        // Test an invalid city, division, and country combination
        val result = viewModel.validCityDivisionCountry("UnknownCity", "Dhaka", "Bangladesh")
        assertFalse(result)
        assertEquals("Invalid city: UnknownCity under division: Dhaka and country: Bangladesh", viewModel.toastMessage)
    }

    /**
     * Tests the place order functionality with valid inputs.
     * Asserts that the order is successfully placed and the correct message is set.
     */
    @Test
    fun testPlacingOrderWithValidInputs() {
        // Create dummy order items
        val orderItems = arrayListOf(OrderItemDataModel("product1", "a", 100, 20,50))

        // Create a shipping address
        val shippingAddress = ShippingAddressDataModel("Dhaka", "Dhaka", "Bangladesh")

        // Call the placeOrder function with valid data
        viewModel.placeOrder(
            "user1",
            "01712345678",
            "alice@example.com",
            orderItems,
            shippingAddress,
            100
        )

        assertTrue(viewModel.orderPlaced)
        assertEquals("Order placed successfully", viewModel.toastMessage)
    }

    /**
     * Tests the place order functionality with an invalid phone number.
     * Asserts that the order is not placed and the correct error message is set.
     */
    @Test
    fun testPlacingOrderWithInvalidPhoneNumber() {
        // Create dummy order items
        val orderItems = arrayListOf(OrderItemDataModel("product1", "aa", 30, 20,60))

        // Create a shipping address
        val shippingAddress = ShippingAddressDataModel("Dhaka", "Dhaka", "Bangladesh")

        // Call the placeOrder function with invalid phone number
        viewModel.placeOrder(
            "user1",
            "invalidPhone",
            "alice@example.com",
            orderItems,
            shippingAddress,
            100
        )

        assertFalse(viewModel.orderPlaced)
        assertEquals("Phone number must start with '01'", viewModel.toastMessage)
    }

    /**
     * Tests the place order functionality with an invalid user ID.
     * Asserts that the order is not placed and the correct error message is set.
     */
    @Test
    fun testPlacingOrderWithInvalidUserId() {
        // Create dummy order items
        val orderItems = arrayListOf(OrderItemDataModel("product1", "this", 80, 10,30))

        // Create a shipping address
        val shippingAddress = ShippingAddressDataModel("Dhaka", "Dhaka", "Bangladesh")

        // Call the placeOrder function with invalid user id
        viewModel.placeOrder(
            "invalidUser",
            "01712345678",
            "alice@example.com",
            orderItems,
            shippingAddress,
            100
        )

        assertFalse(viewModel.orderPlaced)
        assertEquals("User ID is not valid", viewModel.toastMessage)
    }

    /**
     * Tests a valid city, division, and country combination with mixed upper and lower case letters.
     * The test checks if the `validCityDivisionCountry` function correctly validates a valid input
     * and sets the appropriate toast message.
     *
     * Expected Result:
     * - The function should return `true` indicating the input is valid.
     * - The toast message should be "Valid city, division, and country!".
     */
    @Test
    fun testValidCityDivisionAndCountryWithMixedUpperCaseAndLowerCase() {
        // Test a valid city, division, and country combination
        val result = viewModel.validCityDivisionCountry("dHaKa", "DhakA", "banglaDESH")
        assertTrue(result)
        assertEquals("Valid city, division, and country!", viewModel.toastMessage)
    }

    /**
     * Tests an invalid email with uppercase letters in the email address.
     * The test checks if the `validUserEmail` function correctly identifies an email with uppercase letters
     * and sets the appropriate toast message indicating the allowed characters.
     *
     * Expected Result:
     * - The function should return `false` indicating the email is invalid.
     * - The toast message should be "Email address must contain only lowercase letters, digits, and '@', '.', '-', or '_'"
     *   to indicate the invalid input.
     */
    @Test
    fun testInvalidEmailByUppercaseLetter() {
        val result = viewModel.validUserEmail("CHOYON@gmail.com")
        assertFalse(result)
        assertEquals("Email address must contain only lowercase letters, digits, and '@', '.', '-', or '_'", viewModel.toastMessage)
    }

    /**
     * Tests an invalid email due to missing "@" symbol.
     * The test checks if the `validUserEmail` function correctly identifies an email without "@" and sets
     * the appropriate toast message indicating the invalid format.
     *
     * Expected Result:
     * - The function should return `false` indicating the email is invalid.
     * - The toast message should be "Email address pattern is invalid" to indicate the invalid format.
     */
    @Test
    fun testInvalidEmailByNotProperFormate1() {
        val result = viewModel.validUserEmail("choyongmail.com")
        assertFalse(result)
        assertEquals("Email address pattern is invalid", viewModel.toastMessage)
    }

    /**
     * Tests an invalid email due to double "@" symbols.
     * The test checks if the `validUserEmail` function correctly identifies an email with multiple "@" symbols
     * and sets the appropriate toast message indicating the invalid format.
     *
     * Expected Result:
     * - The function should return `false` indicating the email is invalid.
     * - The toast message should be "Email address pattern is invalid" to indicate the invalid format.
     */
    @Test
    fun testInvalidEmailByNotProperFormate2() {
        val result = viewModel.validUserEmail("choyon@@gmail.com")
        assertFalse(result)
        assertEquals("Email address pattern is invalid", viewModel.toastMessage)
    }

    /**
     * Tests an invalid email due to missing top-level domain (TLD).
     * The test checks if the `validUserEmail` function correctly identifies an email with no TLD after "@"
     * and sets the appropriate toast message indicating the invalid format.
     *
     * Expected Result:
     * - The function should return `false` indicating the email is invalid.
     * - The toast message should be "Email address pattern is invalid" to indicate the invalid format.
     */
    @Test
    fun testInvalidEmailByNotProperFormate3() {
        val result = viewModel.validUserEmail("choyon@gmailcom")
        assertFalse(result)
        assertEquals("Email address pattern is invalid", viewModel.toastMessage)
    }

    /**
     * Tests an invalid email due to an incomplete TLD.
     * The test checks if the `validUserEmail` function correctly identifies an email with an incomplete TLD (e.g., missing characters after ".")
     * and sets the appropriate toast message indicating the invalid format.
     *
     * Expected Result:
     * - The function should return `false` indicating the email is invalid.
     * - The toast message should be "Email address pattern is invalid" to indicate the invalid format.
     */
    @Test
    fun testInvalidEmailByNotProperFormate4() {
        val result = viewModel.validUserEmail("choyon@gmail.")
        assertFalse(result)
        assertEquals("Email address pattern is invalid", viewModel.toastMessage)
    }

    @Test
    fun testIsProductInStockSufficientStock() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 30, 5,20)  // Available stock is 5, requested quantity is 3
        )
        val result = viewModel.isProductInStock("p001", 3, orderItems)
        assertTrue(result)  // The product should be in stock
    }

    @Test
    fun testIsProductInStockInsufficientStock() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 3, 5,20)  // Available stock is 2, requested quantity is 3
        )

        val result = viewModel.isProductInStock("p001", 5, orderItems)

        assertFalse(result)  // The product should not be in stock
    }


    @Test
    fun testIsProductInStockProductNotFound() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 5, 3,20)
        )
        val result = viewModel.isProductInStock("Noproduct", 5, orderItems)
        assertFalse(result)  // The product should not be found
    }

    @Test
    fun testValidateStockAvailabilityAllProductsInStock() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 30, 5,150),  // Available stock is 5, requested quantity is 3
            OrderItemDataModel("p002", "Mouse", 20, 4,200)   // Available stock is 4, requested quantity is 2
        )

        val result = viewModel.validateStockAvailability(orderItems)

        assertTrue(result)  // All products should be in stock
        assertNull(viewModel.toastMessage)  // No error message should be set
    }

    @Test
    fun testValidateStockAvailabilityInsufficientStockForOneProduct() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 10, 15,100),  // Available stock is 2, requested quantity is 3
            OrderItemDataModel("p002", "Mouse", 20, 4,30)   // Available stock is 4, requested quantity is 2
        )

        val result = viewModel.validateStockAvailability(orderItems)

        assertFalse(result)  // One product has insufficient stock
        assertEquals("Insufficient stock for product: Headphone Only 10 available.", viewModel.toastMessage)
    }

    @Test
    fun testValidateStockAvailabilityInsufficientStockForMultipleProducts() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 3, 20, 100),  // Available stock is 100, requested quantity is 3
            OrderItemDataModel("p002", "Mouse", 3, 10, 55)    // Available stock is 55, requested quantity is 3
        )

        val result = viewModel.validateStockAvailability(orderItems)

        // Assert that the result is false because the stock is insufficient for both products
        assertFalse(result)

        // Prepare the expected toast message with both errors
        val expectedMessage = """
        Insufficient stock for product: Headphone Only 3 available.
        Insufficient stock for product: Mouse Only 3 available.
    """.trimIndent()

        // Assert that the toast message contains both error messages
        assertEquals(expectedMessage, viewModel.toastMessage)
    }

    @Test
    fun testValidateStockAvailabilityInsufficientStockForMultipleProducts2() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 3, 20, 600),  // Available stock is 3, requested quantity is 3
            OrderItemDataModel("p002", "Mouse", 3, 10, 300),    // Available stock is 3, requested quantity is 3
            OrderItemDataModel("p003","Keyboard",5,10,1200)      // Available stock is 5 , requested quantity is 10
        )
        val result = viewModel.validateStockAvailability(orderItems)
        assertFalse(result)  // Both products have insufficient stock
        // Check that the toastMessage contains both error messages
        val expectedMessage = """
        Insufficient stock for product: Headphone Only 3 available.
        Insufficient stock for product: Mouse Only 3 available.
        Insufficient stock for product: Keyboard Only 5 available.""".trimIndent()

        assertEquals(expectedMessage, viewModel.toastMessage)
    }

    @Test
    fun testValidateStockAvailabilityEmptyOrderList() {
        val orderItems = arrayListOf<OrderItemDataModel>()  // Empty order list

        val result = viewModel.validateStockAvailability(orderItems)

        assertTrue(result)  // An empty order list should be valid (no products to check)
        assertNull(viewModel.toastMessage)  // No error message should be set
    }

    @Test
    fun testValidateStockAvailabilityMixedStockAvailability() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 5, 3,30),  // Available stock is 5, requested quantity is 3
            OrderItemDataModel("p002", "Mouse", 2, 3,60)   // Available stock is 2, requested quantity is 3
        )

        val result = viewModel.validateStockAvailability(orderItems)

        assertFalse(result)  // Second product has insufficient stock
        assertEquals("Insufficient stock for product: Mouse Only 2 available.", viewModel.toastMessage)
    }
}
