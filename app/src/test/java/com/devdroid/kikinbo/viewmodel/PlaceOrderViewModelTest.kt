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

    /**
     * Tests the `isProductInStock` function when there is sufficient stock for the specified product.
     *
     * This test verifies that the function correctly returns `true` when the product is present
     * in the list and has sufficient stock to meet the requested quantity.
     *
     * Expected Result:
     * - The function should return `true`, indicating that the product has sufficient stock.
     */
    @Test
    fun testIsProductInStockSufficientStock() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 30, 5, 20)  // Available stock is 5, requested quantity is 3
        )
        val result = viewModel.isProductInStock("p001", 3, orderItems)
        assertTrue(result)  // The product should be in stock
    }

    /**
     * Tests the `isProductInStock` function when there is insufficient stock for the specified product.
     *
     * This test verifies that the function correctly returns `false` when the product is present
     * in the list but does not have enough stock to meet the requested quantity.
     *
     * Expected Result:
     * - The function should return `false`, indicating that the product has insufficient stock.
     */
    @Test
    fun testIsProductInStockInsufficientStock() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 3, 5, 20)  // Available stock is 2, requested quantity is 3
        )
        val result = viewModel.isProductInStock("p001", 5, orderItems)
        assertFalse(result)  // The product should not be in stock
    }

    /**
     * Tests the `isProductInStock` function when the specified product is not found in the list.
     *
     * This test verifies that the function correctly returns `false` when the product ID does not
     * match any item in the order list.
     *
     * Expected Result:
     * - The function should return `false`, indicating that the product is not found in the list.
     */
    @Test
    fun testIsProductInStockProductNotFound() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 5, 3, 20)
        )
        val result = viewModel.isProductInStock("Noproduct", 5, orderItems)
        assertFalse(result)  // The product should not be found
    }

    /**
     * Tests the validation of stock availability when all products have sufficient stock.
     *
     * This test verifies that the `validateStockAvailability` function correctly identifies
     * that all products in the order list have sufficient stock. It ensures that the function
     * returns `true` and that no `toastMessage` is set, as there are no stock issues.
     *
     * Expected Result:
     * - The function should return `true`, indicating that all products have sufficient stock.
     * - The `toastMessage` should be `null`, as there is no error related to stock.
     */
    @Test
    fun testValidateStockAvailabilityAllProductsInStock() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 30, 5, 150),  // Available stock is 5, requested quantity is 3
            OrderItemDataModel("p002", "Mouse", 20, 4, 200)       // Available stock is 4, requested quantity is 2
        )

        val result = viewModel.validateStockAvailability(orderItems)

        assertTrue(result)  // All products should be in stock
        assertNull(viewModel.toastMessage)  // No error message should be set
    }

    /**
     * Tests the validation of stock availability when one product has insufficient stock.
     *
     * This test verifies that the `validateStockAvailability` function correctly identifies a single
     * product with insufficient stock in the order list. It ensures that the function returns `false`
     * and that the `toastMessage` provides an error message indicating the product name and available stock.
     *
     * Expected Result:
     * - The function should return `false`, indicating insufficient stock for one product.
     * - The `toastMessage` should display "Insufficient stock for product: Headphone Only 10 available."
     *   to inform about the stock limitation.
     */
    @Test
    fun testValidateStockAvailabilityInsufficientStockForOneProduct() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 10, 15, 100),  // Available stock is 10, requested quantity is 15
            OrderItemDataModel("p002", "Mouse", 20, 4, 30)         // Available stock is 20, requested quantity is 4
        )

        val result = viewModel.validateStockAvailability(orderItems)

        assertFalse(result)  // One product has insufficient stock
        assertEquals("Insufficient stock for product: Headphone Only 10 available.", viewModel.toastMessage)
    }

    /**
     * Tests the validation of stock availability when multiple products have insufficient stock.
     *
     * This test verifies that the `validateStockAvailability` function correctly identifies insufficient
     * stock for multiple products in the order list. It ensures that the function returns `false`
     * and that the `toastMessage` provides error messages for each product with insufficient stock.
     *
     * Expected Result:
     * - The function should return `false`, indicating that some products do not have enough stock.
     * - The `toastMessage` should contain error messages for each product with insufficient stock,
     *   specifying the product name and the quantity available.
     */
    @Test
    fun testValidateStockAvailabilityInsufficientStockForMultipleProducts() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 3, 20, 100),  // Available stock is 3, requested quantity is 20
            OrderItemDataModel("p002", "Mouse", 3, 10, 55)        // Available stock is 3, requested quantity is 10
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

    /**
     * Tests the validation of stock availability when multiple products have insufficient stock.
     *
     * This test verifies that the `validateStockAvailability` function correctly identifies and
     * reports insufficient stock for multiple products in the order list. Specifically, it checks
     * that each product with insufficient stock is reflected in the `toastMessage` with the correct
     * available quantity.
     *
     * Expected Result:
     * - The function should return `false`, indicating that some products do not have sufficient stock.
     * - The `toastMessage` should contain error messages for each product with insufficient stock,
     *   specifying the product name and the quantity available.
     */
    @Test
    fun testValidateStockAvailabilityInsufficientStockForMultipleProducts2() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 3, 20, 600),  // Available stock is 3, requested quantity is 3
            OrderItemDataModel("p002", "Mouse", 3, 10, 300),      // Available stock is 3, requested quantity is 3
            OrderItemDataModel("p003", "Keyboard", 5, 10, 1200)   // Available stock is 5, requested quantity is 10
        )
        val result = viewModel.validateStockAvailability(orderItems)
        assertFalse(result)  // Both products have insufficient stock

        // Check that the toastMessage contains all error messages
        val expectedMessage = """
        Insufficient stock for product: Headphone Only 3 available.
        Insufficient stock for product: Mouse Only 3 available.
        Insufficient stock for product: Keyboard Only 5 available.""".trimIndent()

        assertEquals(expectedMessage, viewModel.toastMessage)
    }

    /**
     * Tests the validation of stock availability with an empty order list.
     *
     * This test verifies that when the `validateStockAvailability` function is called with an empty
     * list of order items, it correctly considers the scenario as valid since there are no products
     * to check. The function should return `true` and no toast message should be set.
     *
     * Expected Result:
     * - The function should return `true`, indicating that an empty order list is valid.
     * - The `toastMessage` should be `null` as there is no error to display.
     */
    @Test
    fun testValidateStockAvailabilityEmptyOrderList() {
        val orderItems = arrayListOf<OrderItemDataModel>()  // Empty order list

        val result = viewModel.validateStockAvailability(orderItems)

        assertTrue(result)  // An empty order list should be valid (no products to check)
        assertNull(viewModel.toastMessage)  // No error message should be set
    }
    
    /**
     * Tests stock availability with mixed availability across multiple products.
     *
     * Expected Result:
     * - Function returns `false` indicating insufficient stock.
     * - Toast message: "Insufficient stock for product: Mouse Only 2 available."
     */
    @Test
    fun testValidateStockAvailabilityMixedStockAvailability() {
        val orderItems = arrayListOf(
            OrderItemDataModel("p001", "Headphone", 5, 3, 30),
            OrderItemDataModel("p002", "Mouse", 2, 3, 60)
        )
        val result = viewModel.validateStockAvailability(orderItems)
        assertFalse(result)
        assertEquals("Insufficient stock for product: Mouse Only 2 available.", viewModel.toastMessage)
    }
}
