package com.devdroid.kikinbo.view

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.kikinbo.R
import com.devdroid.kikinbo.model.ProductDataModel
import com.google.firebase.database.*

/**
 * Activity to display product feedback and ratings.
 *
 * This activity fetches product data from Firebase Realtime Database and displays
 * the product name, average rating, and individual reviews.
 */
class SeeFeedBackAndRating : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var productNameTextView: TextView
    private lateinit var productRatingBar: RatingBar
    private lateinit var reviewsContainer: LinearLayout

    /**
     * Called when the activity is created. Initializes views and fetches product data.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * this Bundle contains the data it most recently supplied in [onSaveInstanceState]. Otherwise, it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_feed_backand_rating)

        // Initialize Firebase Database reference
        database = FirebaseDatabase.getInstance().getReference("Products")

        // Bind views from XML
        productNameTextView = findViewById(R.id.productNameTextView)
        productRatingBar = findViewById(R.id.productRatingBar)
        reviewsContainer = findViewById(R.id.reviewsContainer)

        // Fetch product data from Firebase
        fetchProductData()
    }

    /**
     * Fetches product data from Firebase based on the product ID.
     *
     * This function retrieves product information, including reviews and ratings,
     * and updates the UI accordingly. If the product is not found, a toast message is displayed.
     */
    private fun fetchProductData() {
        val productId = "Mouseid"

        database.orderByChild("productId").equalTo(productId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val product = snapshot.children.first().getValue(ProductDataModel::class.java)
                        product?.let {
                            productNameTextView.text = it.productName ?: "N/A"

                            // Calculate and display the average rating
                            val averageRating = it.reviews?.mapNotNull { review -> review.productRating?.toFloat() }
                                ?.average()?.toFloat() ?: 0f
                            productRatingBar.rating = averageRating

                            // Clear previous reviews in the container
                            reviewsContainer.removeAllViews()

                            // Display each review dynamically
                            it.reviews?.forEach { review ->
                                // Create TextView for review text
                                val reviewTextView = TextView(this@SeeFeedBackAndRating).apply {
                                    text = review.productIdReview ?: "No review"
                                    textSize = 14f
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                    )
                                }

                                // Create RatingBar for review rating
                                val reviewRatingBar = RatingBar(this@SeeFeedBackAndRating, null, android.R.attr.ratingBarStyleSmall).apply {
                                    numStars = 5
                                    stepSize = 0.5f
                                    rating = review.productRating?.toFloat() ?: 0f
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                    )
                                    setIsIndicator(true)  // Make it read-only
                                }

                                // Add review elements to container
                                reviewsContainer.addView(reviewTextView)
                                reviewsContainer.addView(reviewRatingBar)
                            }
                        }
                    } else {
                        Toast.makeText(this@SeeFeedBackAndRating, "Product not found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@SeeFeedBackAndRating, "Failed to fetch data: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
