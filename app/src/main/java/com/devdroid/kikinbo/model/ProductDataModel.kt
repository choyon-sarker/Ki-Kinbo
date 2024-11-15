package com.devdroid.kikinbo.model

/**
 * Data model representing a product with various attributes such as name, price, rating, and category.
 *
 * @property productId Unique identifier for the product.
 * @property productName Name of the product.
 * @property productPrice Price of the product in integer format.
 * @property productRating Average user rating of the product, represented as a double.
 * @property productDetail Detailed description of the product's features and benefits.
 * @property productReview User review or feedback for the product.
 * @property productCategory Category to which the product belongs, e.g., Electronics, Stationary, etc.
 */
data class ProductDataModel(
    val productId: String? = null,
    val productName: String? = null,
    val productPrice: Int? = null,
    val productRating: Double? = null,
    val productDetail: String? = null,
    val productReview: String? = null,
    val productCategory: String? = null
)
