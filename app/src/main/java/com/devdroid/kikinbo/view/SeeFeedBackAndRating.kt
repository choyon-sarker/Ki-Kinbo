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
import com.devdroid.kikinbo.viewmodel.ProductViewModel

/**
 * Activity to display feedback and ratings for a specific product.
 *
 * This activity fetches product data and displays the product name,
 * average rating, and individual reviews along with their ratings.
 */
class SeeFeedBackAndRating : AppCompatActivity() {

    private lateinit var productNameTextView: TextView
    private lateinit var productRatingBar: RatingBar
    private lateinit var reviewsContainer: LinearLayout
    private val productViewModel = ProductViewModel()  // Initialize ProductViewModel

    /**
     * Called when the activity is starting.
     * Sets the content view and initializes UI components.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down, this Bundle contains
     *                           the data it most recently supplied in
     *                           onSaveInstanceState(Bundle).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_feed_backand_rating)

        productNameTextView = findViewById(R.id.productNameTextView)
        productRatingBar = findViewById(R.id.productRatingBar)
        reviewsContainer = findViewById(R.id.reviewsContainer)

        fetchProductData("Mouseid")  // Replace "Mouseid" with your productId
    }

    /**
     * Fetches product data from the ViewModel using the provided product ID.
     *
     * @param productId The ID of the product for which data needs to be fetched.
     */
    private fun fetchProductData(productId: String) {
        productViewModel.fetchProductData(productId) { product ->
            if (product != null) {
                // Update UI with product data
                displayProductData(product)
            } else {
                Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Displays the product data in the UI.
     *
     * Updates the product name, average rating, and reviews based on the
     * provided product data model.
     *
     * @param product The product data model containing product details
     *                and reviews.
     */
    private fun displayProductData(product: ProductDataModel) {
        productNameTextView.text = product.productName ?: "N/A"
        val averageRating = product.reviews?.mapNotNull { it.productRating?.toFloat() }?.average()?.toFloat() ?: 0f
        productRatingBar.rating = averageRating

        reviewsContainer.removeAllViews()
        product.reviews?.forEach { review ->
            val reviewTextView = TextView(this).apply {
                text = review.productIdReview ?: "No review"
                textSize = 14f
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

            val reviewRatingBar = RatingBar(this, null, android.R.attr.ratingBarStyleSmall).apply {
                numStars = 5
                stepSize = 0.5f
                rating = review.productRating?.toFloat() ?: 0f
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                setIsIndicator(true) // Ensure that this line is not red in your IDE
            }

            reviewsContainer.addView(reviewTextView)
            reviewsContainer.addView(reviewRatingBar)
        }
    }
}
