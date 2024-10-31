package com.devdroid.kikinbo.view // Make sure this matches your project structure

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.kikinbo.R
import com.devdroid.kikinbo.viewmodel.ProductViewModel

class RateAndReviewItems : AppCompatActivity() { // Make sure class name is consistent with usage

    private val productViewModel: ProductViewModel by viewModels()
    private val productId = "-OAGiY2X-wIjzSA6SEi2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rateand_review_items)

        val ratingBar = findViewById<RatingBar>(R.id.rating_bar)
        val reviewEditText = findViewById<EditText>(R.id.review_edit_text)
        val submitButton = findViewById<Button>(R.id.submit_button)

        // Fetch product data on creation
        productViewModel.fetchProductData(productId) { product ->
            if (product != null) {
                // Use product data as needed (e.g., display product name)
            } else {
                Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show()
            }
        }

        // Submit button click listener
        submitButton.setOnClickListener {
            val newRating = ratingBar.rating
            val newReview = reviewEditText.text.toString()

            if (newReview.isNotEmpty()) {
                productViewModel.addReviewToProduct(productId, newRating, newReview) { success ->
                    if (success) {
                        Toast.makeText(this, "Review submitted successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to submit review", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please enter a review", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
