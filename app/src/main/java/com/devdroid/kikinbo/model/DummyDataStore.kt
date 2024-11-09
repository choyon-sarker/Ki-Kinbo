package com.devdroid.kikinbo.model

class DummyDataStore {
    // Dummy user database
    val dummyUserDatabase = hashMapOf(
        "user1" to UserDataModel("user1", "Alice", "alice@example.com", "1234567890"),
        "user2" to UserDataModel("user2", "Bob", "bob@example.com", "0987654321"),
        "user3" to UserDataModel("user3", "Charlie", "charlie@example.com", null) // No phone number
    )

    // Dummy shipping addresses
    val dummyShippingAddresses = listOf(
        // Dhaka Division (6 Cities)
        ShippingAddressDataModel(city = "Dhaka", division = "Dhaka", country = "Bangladesh"),
        ShippingAddressDataModel(city = "Narayanganj", division = "Dhaka", country = "Bangladesh"),
        ShippingAddressDataModel(city = "Gazipur", division = "Dhaka", country = "Bangladesh"),
        ShippingAddressDataModel(city = "Madaripur", division = "Dhaka", country = "Bangladesh"),
        ShippingAddressDataModel(city = "Tangail", division = "Dhaka", country = "Bangladesh"),
        ShippingAddressDataModel(city = "Faridpur", division = "Dhaka", country = "Bangladesh"),

        // Rajshahi Division (6 Cities)
        ShippingAddressDataModel(city = "Rajshahi", division = "Rajshahi", country = "Bangladesh"),
        ShippingAddressDataModel(city = "Bogura", division = "Rajshahi", country = "Bangladesh"),
        ShippingAddressDataModel(city = "Naogaon", division = "Rajshahi", country = "Bangladesh"),
        ShippingAddressDataModel(city = "Chapainawabganj", division = "Rajshahi", country = "Bangladesh"),
        ShippingAddressDataModel(city = "Pabna", division = "Rajshahi", country = "Bangladesh"),
        ShippingAddressDataModel(city = "Sirajganj", division = "Rajshahi", country = "Bangladesh")
    )
}
