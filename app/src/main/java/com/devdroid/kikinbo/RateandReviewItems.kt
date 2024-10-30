package com.devdroid.kikinbo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class RateandReviewItems : AppCompatActivity() {

    // Hard-coded product ID for demonstration
    private val productId = "-OAGiY2X-wIjzSA6SEi2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rateand_review_items)

        val ratingBar = findViewById<RatingBar>(R.id.rating_bar)
        val reviewEditText = findViewById<EditText>(R.id.review_edit_text)
        val submitButton = findViewById<Button>(R.id.submit_button)

        // Submit button click listener
        submitButton.setOnClickListener {
            val newRating = ratingBar.rating
            val newReview = reviewEditText.text.toString()

            if (newReview.isNotEmpty()) {
                addReviewToProduct(productId, newRating, newReview)
            } else {
                Toast.makeText(this, "Please enter a review", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addReviewToProduct(productId: String, rating: Float, review: String) {
        // Reference to the specific product in Firebase
        val databaseRef = FirebaseDatabase.getInstance().getReference("Products").child(productId)

        // Fetch the current product data
        databaseRef.get().addOnSuccessListener { dataSnapshot ->
            val productData = dataSnapshot.getValue(ProductDataModel::class.java)

            // Prepare the new review entry
            val newReview = Review().apply {
                productRating = rating.toString()
                productIdReview = review
            }

            // Append the new review to the list of reviews
            val updatedReviews = productData?.reviews ?: mutableListOf()
            updatedReviews.add(newReview)

            // Update Firebase with the new review list
            databaseRef.child("reviews").setValue(updatedReviews).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Review submitted successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to submit review", Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to fetch product data", Toast.LENGTH_SHORT).show()
        }
    }
}
