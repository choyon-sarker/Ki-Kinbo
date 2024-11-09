package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.DummyDataStore
import com.devdroid.kikinbo.model.OrderItemDataModel
import com.devdroid.kikinbo.model.ShippingAddressDataModel
import com.devdroid.kikinbo.model.UserDataModel

class PlaceOrderViewModel {

    // Regular variables to store messages and order status
    var toastMessage: String? = null
    var orderPlaced: Boolean = false

    // Create an instance of DummyDataStore
    val dataStore = DummyDataStore()

    // Get user by id from the database
    fun getUserById(userId: String): UserDataModel? {
        return dataStore.dummyUserDatabase[userId] // Returns the UserDataModel or null if userId doesn't exist
    }

    // Get all cities in a given division and country
    fun getCitiesInDivisionAndCountry(division: String, country: String): List<String> {
        return dataStore.dummyShippingAddresses.filter {
            it.division == division && it.country == country
        }.map { it.city }
    }

    // Function to validate city, division, and country
    fun validCityDivisionCountry(city: String, division: String, country: String): Boolean {
        val validCountries = dataStore.dummyShippingAddresses.map { it.country }.distinct()
        if (country !in validCountries) {
            toastMessage = "Invalid country: $country"
            return false
        }

        val validDivisions = dataStore.dummyShippingAddresses.filter { it.country == country }.map { it.division }.distinct()
        if (division !in validDivisions) {
            toastMessage = "Invalid division: $division for country: $country"
            return false
        }

        val validCities = getCitiesInDivisionAndCountry(division, country)
        if (city !in validCities) {
            toastMessage = "Invalid city: $city under division: $division and country: $country"
            return false
        }

        toastMessage = "Valid city, division, and country!"
        return true
    }

    // Validate phone number and email
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

    // Validate User ID
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

    // Verify phone number
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

    // Function to place an order
    fun placeOrder(
        userId: String,
        userPhone: String,
        userEmail: String,
        items: ArrayList<OrderItemDataModel>,
        shippingAddress: ShippingAddressDataModel,
        totalAmount: Int
    ) {
        if (validUserId(userId) &&
            validUserPhone(userPhone) &&
            validCityDivisionCountry(shippingAddress.city, shippingAddress.division, shippingAddress.country)
        ) {
            orderPlaced = true
            toastMessage = "Order placed successfully"
        } else {
            orderPlaced = false
        }
    }
}

