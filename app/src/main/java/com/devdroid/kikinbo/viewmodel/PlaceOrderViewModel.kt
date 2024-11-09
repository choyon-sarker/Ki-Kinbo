//package com.devdroid.kikinbo.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.devdroid.kikinbo.model.OrderItemDataModel
//import com.devdroid.kikinbo.model.ShippingAddressDataModel
//import com.devdroid.kikinbo.model.UserDataModel
//
//class PlaceOrderViewModel : ViewModel(){
//    // LiveData to observe the message for Toast
//    private val _toastMessage = MutableLiveData<String>()
//    val toastMessage: LiveData<String> get() = _toastMessage
//
//    private val _orderPlaced = MutableLiveData<Boolean>()
//    val orderPlaced: LiveData<Boolean> get() = _orderPlaced
//
//
//    var dummyUserDatabase = hashMapOf(
//        "user1" to UserDataModel("user1", "Alice", "alice@example.com", "1234567890"),
//        "user2" to UserDataModel("user2", "Bob", "bob@example.com", "0987654321"),
//        "user3" to UserDataModel("user3", "Charlie", "charlie@example.com", null) // No phone number
//    )
//    val dummyShippingAddresses = listOf(
//        // Dhaka Division (6 Cities)
//        ShippingAddressDataModel(city = "Dhaka", division = "Dhaka", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Narayanganj", division = "Dhaka", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Gazipur", division = "Dhaka", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Madaripur", division = "Dhaka", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Tangail", division = "Dhaka", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Faridpur", division = "Dhaka", country = "Bangladesh"),
//
//        // Rajshahi Division (6 Cities)
//        ShippingAddressDataModel(city = "Rajshahi", division = "Rajshahi", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Bogura", division = "Rajshahi", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Naogaon", division = "Rajshahi", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Chapainawabganj", division = "Rajshahi", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Pabna", division = "Rajshahi", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Sirajganj", division = "Rajshahi", country = "Bangladesh")
//    )
//
//    //get user by id from the database
//    fun getUserById(userId: String): UserDataModel? {
//        return dummyUserDatabase[userId] // Returns the UserDataModel or null if userId doesn't exist
//    }
//
//    // 1. Helper function to get all cities in a given division and country
//    fun getCitiesInDivisionAndCountry(division: String, country: String): List<String> {
//        return dummyShippingAddresses.filter {
//            it.division == division && it.country == country
//        }.map { it.city }
//    }
//
//    // 2. Function to validate that the entered city, division, and country are correct
//    fun validCityDivisionCountry(city: String, division: String, country: String): Boolean {
//        // First, check if the country exists
//        val validCountries = dummyShippingAddresses.map { it.country }.distinct()
//        if (country !in validCountries) {
//            _toastMessage.value = "Invalid country: $country"
//            return false
//        }
//
//        // Then, check if the division exists within the country
//        val validDivisions = dummyShippingAddresses.filter { it.country == country }.map { it.division }.distinct()
//        if (division !in validDivisions) {
//            _toastMessage.value = "Invalid division: $division for country: $country"
//            return false
//        }
//
//        // Finally, check if the city exists under the division and country
//        val validCities = getCitiesInDivisionAndCountry(division, country)
//        if (city !in validCities) {
//            _toastMessage.value = "Invalid city: $city under division: $division and country: $country"
//            return false
//        }
//
//        // If all checks passed
//        _toastMessage.value = "Valid city, division, and country!"
//        return true
//    }
//
//    //not useable
//    fun validateInputs(userPhone: String, userEmail: String): Boolean {
//
//        if (userPhone==null) {
//            _toastMessage.value = "User Phone number is required"
//            return false
//        }
//        if (userEmail.isEmpty()) {
//            _toastMessage.value = "User Email is required"
//            return false
//        }
//        return true
//    }
//
//    //verify useId
//    fun validUserId(userId: String):Boolean{
//
//        val user = getUserById(userId)
//
//        if(userId.isEmpty()){
//            _toastMessage.value="User Id is empty"
//            return false
//        }else if(userId.isNotEmpty()){
//            if (user != null) {
//                _toastMessage.value="User Id is Valid"
//                return true
//            }
//            else{
//                _toastMessage.value="User Id is Not Valid"
//                return false
//            }
//        }else{
//            _toastMessage.value="User Id is Valid"
//            return true
//        }
//    }
//    // Verify the phone number
//    fun validUserPhone(userPhone: String?): Boolean {
//        if (userPhone.isNullOrEmpty()) {
//            _toastMessage.value = "User phone number is required"
//            return false
//        } else if (!userPhone.startsWith("01")) {
//            _toastMessage.value = "Phone number must start with '01'"
//            return false
//        } else if (!userPhone.all { it.isDigit() }) {
//            _toastMessage.value = "Phone number must contain only digits"
//            return false
//        }else if (userPhone.length != 11) {
//            _toastMessage.value = "Phone number must be exactly 11 digits"
//            return false
//        }  else {
//            return true
//        }
//    }
//
//
//    fun placeOrder(
//        userId: String,
//        userPhone: String,
//        userEmail: String,
//        items: ArrayList<OrderItemDataModel>,
//        shippingAddress: ShippingAddressDataModel,
//        totalAmount: Int
//    ) {
//        // Placeholder logic for placing the order
////        if (items.isNotEmpty()) {
////            _orderPlaced.value = true
////            _toastMessage.value = "Order placed successfully"
////        } else {
////            _toastMessage.value = "No items selected for the order"
////        }
//        if(validateInputs(userPhone,userEmail)){
//            _orderPlaced.value=true
//            _toastMessage.value="Order placed successfully"
//        }
//        if(validUserId(userId)){
//            _orderPlaced.value=true
//            _toastMessage.value="Order placed successfully"
//        }
//        if (validUserPhone(userPhone)){
//            _orderPlaced.value=true
//            _toastMessage.value="Order placed successfully"
//        }
//        if(validCityDivisionCountry(shippingAddress.city,shippingAddress.division,shippingAddress.country)){
//            _orderPlaced.value=true
//            _toastMessage.value="Order placed successfully"
//        }
//    }
//
//}

package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.DummyDataStore
import com.devdroid.kikinbo.model.OrderItemDataModel
import com.devdroid.kikinbo.model.ShippingAddressDataModel
import com.devdroid.kikinbo.model.UserDataModel

class PlaceOrderViewModel {

    // Regular variables to store messages and order status
    var toastMessage: String? = null
    var orderPlaced: Boolean = false

//    // Dummy data for users and shipping addresses
//    var dummyUserDatabase = hashMapOf(
//        "user1" to UserDataModel("user1", "Alice", "alice@example.com", "1234567890"),
//        "user2" to UserDataModel("user2", "Bob", "bob@example.com", "0987654321"),
//        "user3" to UserDataModel("user3", "Charlie", "charlie@example.com", null) // No phone number
//    )
//
//    // Dummy data for shipping addresses
//    val dummyShippingAddresses = listOf(
//        // Dhaka Division (6 Cities)
//        ShippingAddressDataModel(city = "Dhaka", division = "Dhaka", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Narayanganj", division = "Dhaka", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Gazipur", division = "Dhaka", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Madaripur", division = "Dhaka", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Tangail", division = "Dhaka", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Faridpur", division = "Dhaka", country = "Bangladesh"),
//
//        // Rajshahi Division (6 Cities)
//        ShippingAddressDataModel(city = "Rajshahi", division = "Rajshahi", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Bogura", division = "Rajshahi", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Naogaon", division = "Rajshahi", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Chapainawabganj", division = "Rajshahi", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Pabna", division = "Rajshahi", country = "Bangladesh"),
//        ShippingAddressDataModel(city = "Sirajganj", division = "Rajshahi", country = "Bangladesh")
//    )

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

//    // Validate User ID
//    fun validUserId(userId: String): Boolean {
//        val user = getUserById(userId)
//
//        if (userId.isEmpty()) {
//            toastMessage = "User Id is empty"
//            return false
//        } else if (user != null && user==dummyUserDatabase[userId]) {
//            toastMessage = "User Id is Valid"
//            return true
//        } else {
//            toastMessage = "User Id is Not Valid"
//            return false
//        }
//    }
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

