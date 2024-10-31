package com.devdroid.kikinbo.model

data class ProductDataModel(
    val productId: String? = null,
    val productName: String? = null,
    val productPrice: Int? = null,
    val productDetail: String? = null,
    val productCategory: String? = null,
    val reviews: MutableList<Review>? = arrayListOf()  // To store multiple reviews
)

data class Review(
    var productRating: String? = null,
    var productIdReview: String? = null
)
