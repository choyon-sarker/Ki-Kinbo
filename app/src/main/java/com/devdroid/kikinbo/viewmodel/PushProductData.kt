package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.product1
import com.devdroid.kikinbo.model.product2
import com.devdroid.kikinbo.model.product3
import com.devdroid.kikinbo.model.product4
import com.devdroid.kikinbo.model.product5
import com.devdroid.kikinbo.model.product6
import com.google.firebase.database.FirebaseDatabase

class PushProductData {

    val database = FirebaseDatabase.getInstance()
    val databaseRef = database.getReference("Products")
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