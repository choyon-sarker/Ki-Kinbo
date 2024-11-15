package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.repository.DummyUserInfoDataStore
import com.google.firebase.database.FirebaseDatabase

class PushUserData {
    // Firebase Realtime Database instance
    private val database = FirebaseDatabase.getInstance()

    // Reference to the "Products" node in the database
    private val databaseRef = database.getReference("UserInfo")

    private val dummyuserdb = DummyUserInfoDataStore()

    /**
     * Pushes predefined product data (product1 through product6) to the "Products" node in Firebase.
     *
     * Each product is pushed as a unique entry using the `push` method, which generates a unique ID.
     * If the operation succeeds, a success message is printed; otherwise, an error message is displayed.
     */
    fun fPushUserData() {
        databaseRef.push().setValue(dummyuserdb.demoUser1)
        databaseRef.push().setValue(dummyuserdb.demoUser2)
        databaseRef.push().setValue(dummyuserdb.demoUser3)
        databaseRef.push().setValue(dummyuserdb.demoUser4)
        databaseRef.push().setValue(dummyuserdb.demoUser5)
            .addOnSuccessListener {
                println("Product data added successfully!")
            }
            .addOnFailureListener { e ->
                println("Failed to add product data: ${e.message}")
            }
    }
}