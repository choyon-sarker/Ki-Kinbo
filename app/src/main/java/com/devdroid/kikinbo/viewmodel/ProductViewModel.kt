package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.ProductDataModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * ViewModel responsible for interacting with the Firebase Realtime Database
 * to fetch product details based on a provided product ID.
 */
class ProductViewModel {

    // Firebase Database reference pointing to the "Products" node
    private val databaseRef: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("Products")

    /**
     * Fetches product data from Firebase based on the given product ID.
     * The product data is returned via a callback function.
     *
     * @param productId The unique identifier of the product to be fetched.
     * @param callback A function that takes a nullable [ProductDataModel] object.
     *                 Returns the product if found; otherwise, returns null.
     */
    fun fetchProductData(productId: String, callback: (ProductDataModel?) -> Unit) {
        databaseRef.orderByChild("productId").equalTo(productId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (child in snapshot.children) {
                            val product = child.getValue(ProductDataModel::class.java)
                            callback(product) // Return the product via callback
                            return // Exit after finding the product
                        }
                    } else {
                        callback(null) // No product found
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(null) // Return null if an error occurs
                }
            })
    }
}