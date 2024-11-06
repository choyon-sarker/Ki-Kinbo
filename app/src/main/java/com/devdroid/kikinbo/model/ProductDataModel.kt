package com.devdroid.kikinbo.model

/**
 * Data model representing a product.
 *
 * @property productId The unique identifier for the product.
 * @property productName The name of the product.
 * @property productPrice The price of the product.
 * @property productDetail Detailed description of the product.
 * @property productCategory The category to which the product belongs.
 * @property reviews A mutable list of reviews associated with the product.
 */
data class ProductDataModel(
    val productId: String? = null,
    val productName: String? = null,
    val productPrice: Int? = null,
    val productDetail: String? = null,
    val productCategory: String? = null,
    val reviews: MutableList<Review>? = arrayListOf()  // To store multiple reviews
)

/**
 * Data model representing a review for a product.
 *
 * @property productRating The rating given to the product.
 * @property productIdReview The text of the review.
 */
data class Review(
    var productRating: String? = null,
    var productIdReview: String? = null
)
