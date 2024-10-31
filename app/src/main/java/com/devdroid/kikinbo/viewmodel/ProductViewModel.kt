package com.devdroid.kikinbo.viewmodel

import androidx.lifecycle.ViewModel
import com.devdroid.kikinbo.model.ProductDataModel
import com.devdroid.kikinbo.model.Review
import com.google.firebase.database.*

/**
 * ViewModel responsible for interacting with the Firebase Realtime Database to manage product data and reviews.
 */
class ProductViewModel : ViewModel() {

    // Firebase Database reference pointing to the "Products" node
    private val databaseRef: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("Products")

    /**
     * Fetches product data from Firebase based on the given product ID.
     *
     * @param productId The ID of the product to fetch.
     * @param callback A callback that returns the fetched product or null if not found.
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

    /**
     * Adds a review to the specified product.
     *
     * @param productId The ID of the product to which the review will be added.
     * @param rating The rating given to the product.
     * @param reviewText The text of the review.
     * @param callback A callback that returns true if the review was added successfully, false otherwise.
     */
    fun addReviewToProduct(productId: String, rating: Float, reviewText: String, callback: (Boolean) -> Unit) {
        val newReview = Review().apply {
            productRating = rating.toString()
            productIdReview = reviewText
        }

        // Reference to the specific product in Firebase
        val productRef = databaseRef.child(productId)

        // Fetch the current product data
        productRef.get().addOnSuccessListener { dataSnapshot ->
            val productData = dataSnapshot.getValue(ProductDataModel::class.java)

            // Append the new review to the list of reviews
            val updatedReviews = productData?.reviews ?: mutableListOf()
            updatedReviews.add(newReview)

            // Update Firebase with the new review list
            productRef.child("reviews").setValue(updatedReviews).addOnCompleteListener { task ->
                callback(task.isSuccessful) // Return success status
            }
        }.addOnFailureListener {
            callback(false) // Return false on failure to fetch product data
        }
    }
}
