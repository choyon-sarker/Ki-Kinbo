package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.product1
import com.devdroid.kikinbo.model.product2
import com.devdroid.kikinbo.model.product3
import com.devdroid.kikinbo.model.product4
import com.devdroid.kikinbo.model.product5
import com.devdroid.kikinbo.model.product6
import com.google.firebase.database.FirebaseDatabase

/**
 * ViewModel class responsible for pushing product data to Firebase Realtime Database.
 *
 * This class is used to add predefined product entries (product1 through product6)
 * to the "Products" node in the Firebase Realtime Database. Each product is pushed
 * individually as a unique entry.
 */
class PushProductData {

    // Firebase Realtime Database instance
    private val database = FirebaseDatabase.getInstance()

    // Reference to the "Products" node in the database
    private val databaseRef = database.getReference("Products")

    /**
     * Pushes predefined product data (product1 through product6) to the "Products" node in Firebase.
     *
     * Each product is pushed as a unique entry using the `push` method, which generates a unique ID.
     * If the operation succeeds, a success message is printed; otherwise, an error message is displayed.
     */
    fun pushProductData() {
        databaseRef.push().setValue(product1)
        databaseRef.push().setValue(product2)
        databaseRef.push().setValue(product3)
        databaseRef.push().setValue(product4)
        databaseRef.push().setValue(product5)
        databaseRef.push().setValue(product6)
            .addOnSuccessListener {
                println("Product data added successfully!")
            }
            .addOnFailureListener { e ->
                println("Failed to add product data: ${e.message}")
            }
    }
}
