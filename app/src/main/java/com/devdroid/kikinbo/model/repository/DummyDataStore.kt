package com.devdroid.kikinbo.model.repository

import com.devdroid.kikinbo.model.OrderItemDataModel
import com.devdroid.kikinbo.model.ShippingAddressDataModel
import com.devdroid.kikinbo.model.UserDataModel

class DummyDataStore {
    // Dummy user database
    val dummyUserDatabase = hashMapOf(
        "user1" to UserDataModel("user1", "Alice", "alice@example.com", "01345678901"),
        "user2" to UserDataModel("user2", "Bob", "bob@example.com", "01876543211"),
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

    // Dummy OrderItemDataModel objects
    val dummyOrderItems = listOf(
        OrderItemDataModel(
            productId = "P001",
            productName = "Wireless Mouse",
            availableStock = 120,
            quantity = 2,
            price = 500 // Price in cents, i.e., $5.00
        ),
        OrderItemDataModel(
            productId = "P002",
            productName = "Bluetooth Keyboard",
            availableStock = 80,
            quantity = 1,
            price = 1500 // Price in cents, i.e., $15.00
        ),
        OrderItemDataModel(
            productId = "P003",
            productName = "Gaming Headset",
            availableStock = 50,
            quantity = 1,
            price = 3000 // Price in cents, i.e., $30.00
        ),
        OrderItemDataModel(
            productId = "P004",
            productName = "USB-C Charging Cable",
            availableStock = 200,
            quantity = 3,
            price = 200 // Price in cents, i.e., $2.00
        ),
        OrderItemDataModel(
            productId = "P005",
            productName = "4K Monitor",
            availableStock = 30,
            quantity = 1,
            price = 20000 // Price in cents, i.e., $200.00
        )
    )

}
