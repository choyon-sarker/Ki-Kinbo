package com.devdroid.kikinbo.model

/**
 * Data model representing a product in the Ki-Kinbo application.
 *
 * @property productId Unique identifier for the product.
 * @property productName Name of the product.
 * @property productPrice Price of the product, represented as an integer.
 * @property productDetail Detailed description of the product.
 * @property productCategory Category to which the product belongs.
 * @property reviews A mutable list of reviews associated with the product.
 * This allows for multiple reviews to be stored and managed.
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
 * @property productRating The rating given to the product, represented as a string.
 * @property productIdReview The unique identifier for the review, linking it to the respective product.
 */
data class Review(
    var productRating: String? = null,
    var productIdReview: String? = null
)
