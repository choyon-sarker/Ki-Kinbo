package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.DummyDataStore
import com.devdroid.kikinbo.model.OrderItemDataModel
import com.devdroid.kikinbo.model.ShippingAddressDataModel
import com.devdroid.kikinbo.model.UserDataModel

/**
 * ViewModel class responsible for handling the logic of placing an order.
 */
class PlaceOrderViewModel {

    // Regular variables to store messages and order status
    var toastMessage: String? = null

    // Boolean variable to track if the order has been placed
    var orderPlaced: Boolean = false

    // Create an instance of DummyDataStore
    val dataStore = DummyDataStore()

    /**
     * Retrieves a user by their ID from the dummy user database.
     *
     * @param userId The unique identifier for the user.
     * @return The UserDataModel associated with the userId, or null if not found.
     */
    fun getUserById(userId: String): UserDataModel? {
        return dataStore.dummyUserDatabase[userId] // Returns the UserDataModel or null if userId doesn't exist
    }

    /**
     * Retrieves a list of cities located within a specific division and country.
     * The division and country are formatted to title case before comparison.
     *
     * @param division The division (e.g., state, province) in which to search for cities.
     *                 It will be converted to title case for comparison.
     * @param country The country in which to search for cities.
     *                It will be converted to title case for comparison.
     * @return A list of city names within the specified division and country, formatted to title case.
     *
     * The function filters the `dummyShippingAddresses` data store to find entries
     * that match the specified division and country. It then maps the filtered results
     * to a list of cities, each formatted to title case.
     */
    fun getCitiesInDivisionAndCountry(division: String, country: String): List<String> {
        // Convert division and country to title case (first letter uppercase, rest lowercase)
        val formattedDivision = division.replaceFirstChar { it.uppercase() }.lowercase().replaceFirstChar { it.uppercase() }
        val formattedCountry = country.replaceFirstChar { it.uppercase() }.lowercase().replaceFirstChar { it.uppercase() }

        return dataStore.dummyShippingAddresses.filter {
            it.division.replaceFirstChar { it.uppercase() }.lowercase().replaceFirstChar { it.uppercase() } == formattedDivision &&
                    it.country.replaceFirstChar { it.uppercase() }.lowercase().replaceFirstChar { it.uppercase() } == formattedCountry
        }.map { it.city.replaceFirstChar { it.uppercase() }.lowercase().replaceFirstChar { it.uppercase() } }
    }

    /**
     * Checks if the provided city, division, and country combination is valid by comparing
     * it with entries in the data store. Converts each input to title case before validation.
     *
     * @param city The city name to validate, e.g., "New York".
     * @param division The division or state name to validate, e.g., "New York".
     * @param country The country name to validate, e.g., "USA".
     * @return True if the combination of city, division, and country is valid; false otherwise.
     *
     * Validation process:
     * - Converts city, division, and country to title case.
     * - Checks if the country exists in `dataStore.dummyShippingAddresses`.
     * - Checks if the division exists within the provided country.
     * - Checks if the city exists within the specified division and country.
     *
     * Side effect:
     * - Updates `toastMessage` with validation feedback.
     */
    fun validCityDivisionCountry(city: String, division: String, country: String): Boolean {
        // Convert each string to title case (first letter uppercase, rest lowercase)
        val formattedCity = city.lowercase().replaceFirstChar { it.uppercase() }
        val formattedDivision = division.lowercase().replaceFirstChar { it.uppercase() }
        val formattedCountry = country.lowercase().replaceFirstChar { it.uppercase() }

        val validCountries = dataStore.dummyShippingAddresses
            .map { it.country.lowercase().replaceFirstChar { it.uppercase() } }
            .distinct()
        if (formattedCountry !in validCountries) {
            toastMessage = "Invalid country: $country"
            return false
        }

        val validDivisions = dataStore.dummyShippingAddresses
            .filter { it.country.lowercase().replaceFirstChar { it.uppercase() } == formattedCountry }
            .map { it.division.lowercase().replaceFirstChar { it.uppercase() } }
            .distinct()
        if (formattedDivision !in validDivisions) {
            toastMessage = "Invalid division: $division for country: $country"
            return false
        }

        val validCities = getCitiesInDivisionAndCountry(formattedDivision, formattedCountry)
            .map { it.lowercase().replaceFirstChar { it.uppercase() } }
        if (formattedCity !in validCities) {
            toastMessage = "Invalid city: $city under division: $division and country: $country"
            return false
        }

        toastMessage = "Valid city, division, and country!"
        return true
    }

    /**
     * Validates the user inputs for phone number and email.
     *
     * @param userPhone The user's phone number.
     * @param userEmail The user's email address.
     * @return True if both the phone number and email are valid, false otherwise.
     */
    fun validateInputs(userPhone: String, userEmail: String): Boolean {
        if (userPhone.isEmpty()) {
            toastMessage = "User Phone number is required"
            return false
        }
        if (userEmail.isEmpty()) {
            toastMessage = "User Email is required"
            return false
        }
        return true
    }

    /**
     * Validates the user ID.
     *
     * @param userId The user ID to validate.
     * @return True if the user ID is valid, false otherwise.
     */
    fun validUserId(userId: String): Boolean {
        if (userId.isEmpty()) {
            toastMessage = "User ID is empty"
            return false
        }
        val user = getUserById(userId)
        val storedUser = dataStore.dummyUserDatabase[userId]

        // Check if the user exists in the dummyUserDatabase and matches the retrieved user
        return if (user != null && user == storedUser) {
            toastMessage = "User ID is valid"
            true
        } else {
            toastMessage = "User ID is not valid"
            false
        }
    }

    /**
     * Validates the user's phone number.
     *
     * @param userPhone The phone number to validate.
     * @return True if the phone number is valid, false otherwise.
     */
    fun validUserPhone(userPhone: String?): Boolean {
        if (userPhone.isNullOrEmpty()) {
            toastMessage = "User phone number is required"
            return false
        } else if (!userPhone.startsWith("01")) {
            toastMessage = "Phone number must start with '01'"
            return false
        } else if (!userPhone.all { it.isDigit() }) {
            toastMessage = "Phone number must contain only digits"
            return false
        } else if (userPhone.length != 11) {
            toastMessage = "Phone number must be exactly 11 digits"
            return false
        }
        return true
    }

    /**
     * Validates the given email address according to the following rules:
     *
     * 1. The email should not be null or empty.
     * 2. The email should only contain lowercase letters, digits, and the special characters '@', '.', '-', or '_'.
     * 3. The email should match the pattern of a valid email address, in the format of `username@domain.extension`,
     *    where:
     *    - `username` can contain lowercase letters, digits, and special characters `._-`.
     *    - `domain` can contain lowercase letters, digits, and special characters `.-`.
     *    - `extension` must be at least two lowercase letters.
     *
     * @param userEmail The email address to be validated. It can be null or empty.
     *
     * @return `true` if the email is valid according to the rules, `false` otherwise.
     *
     * @throws IllegalArgumentException if an unexpected error occurs during validation.
     *
     * The function also updates the `toastMessage` variable with an appropriate message in case of an invalid email:
     * - "User email address is required" if the email is null or empty.
     * - "Email address must contain only lowercase letters, digits, and '@', '.', '-', or '_'" if the email contains invalid characters.
     * - "Email address pattern is invalid" if the email does not match a valid email format.
     */
    fun validUserEmail(userEmail: String?): Boolean {
        if (userEmail.isNullOrEmpty()) {
            toastMessage = "User email address is required"
            return false
        }

        if (!userEmail.all { it.isLowerCase() || it.isDigit() || it == '@' || it == '.' || it == '-' || it == '_' }) {
            toastMessage = "Email address must contain only lowercase letters, digits, and '@', '.', '-', or '_'"
            return false
        }

        val emailPattern = Regex("^[a-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,}$")

        if (!emailPattern.matches(userEmail)) {
            toastMessage = "Email address pattern is invalid"
            return false
        }

        return true
    }

    /**
     * Places an order if all validation checks pass.
     *
     * @param userId The user ID placing the order.
     * @param userPhone The phone number of the user placing the order.
     * @param userEmail The email address of the user placing the order.
     * @param items A list of order items.
     * @param shippingAddress The shipping address for the order.
     * @param totalAmount The total amount for the order.
     */
    fun placeOrder(
        userId: String,
        userPhone: String,
        userEmail: String,
        items: ArrayList<OrderItemDataModel>,
        shippingAddress: ShippingAddressDataModel,
        totalAmount: Int
    ) :Boolean{
        if (validUserId(userId) &&
            validUserPhone(userPhone) &&
            validCityDivisionCountry(shippingAddress.city, shippingAddress.division, shippingAddress.country) &&
            validUserEmail(userEmail)
        ) {
            orderPlaced = true
            toastMessage = "Order placed successfully"
            return true
        } else {
            orderPlaced = false
            return false
        }
    }
}
