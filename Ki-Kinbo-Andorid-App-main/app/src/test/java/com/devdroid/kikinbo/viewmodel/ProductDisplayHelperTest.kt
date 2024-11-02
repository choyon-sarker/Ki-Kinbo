package com.devdroid.kikinbo.viewmodel

import org.junit.jupiter.api.Assertions.*
import android.widget.TextView
import com.devdroid.kikinbo.ProductDisplayHelper
import com.devdroid.kikinbo.model.ProductDataModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

class ProductDisplayHelperTest{

    private lateinit var productDisplayHelper: ProductDisplayHelper
    private lateinit var productNameTextView: TextView
    private lateinit var productPriceTextView: TextView
    private lateinit var productRatingTextView: TextView
    private lateinit var productCategoryTextView: TextView
    private lateinit var productDetailsTextView: TextView

    @BeforeEach
    fun setUp() {
        productDisplayHelper = ProductDisplayHelper()
        productNameTextView = mock(TextView::class.java)
        productPriceTextView = mock(TextView::class.java)
        productRatingTextView = mock(TextView::class.java)
        productCategoryTextView = mock(TextView::class.java)
        productDetailsTextView = mock(TextView::class.java)
    }

    @Test
    fun testDisplayProductDetails() {
        val product = ProductDataModel(
            productId = "1",
            productName = "Test Product",
            productPrice = 150,
            productRating = 4.7,
            productDetail = "This is a great product.",
            productReview = "Excellent.",
            productCategory = "Electronics"
        )

        productDisplayHelper.displayProductDetails(
            product,
            productNameTextView,
            productPriceTextView,
            productRatingTextView,
            productCategoryTextView,
            productDetailsTextView
        )

        assertEquals("Test Product", productNameTextView.text)
        assertEquals("150.0 BDT", productPriceTextView.text)
        assertEquals("4.7", productRatingTextView.text)
        assertEquals("Electronics", productCategoryTextView.text)
        assertEquals("This is a great product.", productDetailsTextView.text)
    }
    @Test
    fun testDisplayProductDetailsWithNormalValues() {
        val product = ProductDataModel(
            productId = "1",
            productName = "Test Product",
            productPrice = 150,
            productRating = 4.7,
            productDetail = "This is a great product.",
            productReview = "Excellent.",
            productCategory = "Electronics"
        )

        productDisplayHelper.displayProductDetails(
            product,
            productNameTextView,
            productPriceTextView,
            productRatingTextView,
            productCategoryTextView,
            productDetailsTextView
        )

        assertEquals("Test Product", productNameTextView.text)
        assertEquals("150.0 BDT", productPriceTextView.text)
        assertEquals("4.7", productRatingTextView.text)
        assertEquals("Electronics", productCategoryTextView.text)
        assertEquals("This is a great product.", productDetailsTextView.text)
    }

    @Test
    fun testDisplayProductDetailsWithZeroPrice() {
        val product = ProductDataModel(
            productId = "2",
            productName = "Free Product",
            productPrice = 0,
            productRating = 4.0,
            productDetail = "This product is free!",
            productReview = "Awesome!",
            productCategory = "Promotions"
        )

        productDisplayHelper.displayProductDetails(
            product,
            productNameTextView,
            productPriceTextView,
            productRatingTextView,
            productCategoryTextView,
            productDetailsTextView
        )

        assertEquals("Free Product", productNameTextView.text)
        assertEquals("0.0 BDT", productPriceTextView.text)
        assertEquals("4.0", productRatingTextView.text)
        assertEquals("Promotions", productCategoryTextView.text)
        assertEquals("This product is free!", productDetailsTextView.text)
    }

    @Test
    fun testDisplayProductDetailsWithMaximumRating() {
        val product = ProductDataModel(
            productId = "3",
            productName = "Top Rated Product",
            productPrice = 200,
            productRating = 5.0,
            productDetail = "Best product ever!",
            productReview = "Highly recommend!",
            productCategory = "Best Sellers"
        )

        productDisplayHelper.displayProductDetails(
            product,
            productNameTextView,
            productPriceTextView,
            productRatingTextView,
            productCategoryTextView,
            productDetailsTextView
        )

        assertEquals("Top Rated Product", productNameTextView.text)
        assertEquals("200.0 BDT", productPriceTextView.text)
        assertEquals("5.0", productRatingTextView.text)
        assertEquals("Best Sellers", productCategoryTextView.text)
        assertEquals("Best product ever!", productDetailsTextView.text)
    }

    @Test
    fun testDisplayProductDetailsWithMinimumRating() {
        val product = ProductDataModel(
            productId = "4",
            productName = "Low Rated Product",
            productPrice = 100,
            productRating = 0.0,
            productDetail = "This product has not been rated well.",
            productReview = "Needs improvement.",
            productCategory = "New Arrivals"
        )

        productDisplayHelper.displayProductDetails(
            product,
            productNameTextView,
            productPriceTextView,
            productRatingTextView,
            productCategoryTextView,
            productDetailsTextView
        )

        assertEquals("Low Rated Product", productNameTextView.text)
        assertEquals("100.0 BDT", productPriceTextView.text)
        assertEquals("0.0", productRatingTextView.text)
        assertEquals("New Arrivals", productCategoryTextView.text)
        assertEquals("This product has not been rated well.", productDetailsTextView.text)
    }

    @Test
    fun testDisplayProductDetailsWithEmptyProductName() {
        val product = ProductDataModel(
            productId = "5",
            productName = "",
            productPrice = 50,
            productRating = 4.0,
            productDetail = "An anonymous product.",
            productReview = "Interesting.",
            productCategory = "Miscellaneous"
        )

        productDisplayHelper.displayProductDetails(
            product,
            productNameTextView,
            productPriceTextView,
            productRatingTextView,
            productCategoryTextView,
            productDetailsTextView
        )

        assertEquals("", productNameTextView.text)
        assertEquals("50.0 BDT", productPriceTextView.text)
        assertEquals("4.0", productRatingTextView.text)
        assertEquals("Miscellaneous", productCategoryTextView.text)
        assertEquals("An anonymous product.", productDetailsTextView.text)
    }

    @Test
    fun testDisplayProductDetailsWithNullCategory() {
        val product = ProductDataModel(
            productId = "6",
            productName = "Product Without Category",
            productPrice = 75,
            productRating = 3.5,
            productDetail = "This product belongs to no category.",
            productReview = "Okay.",
            productCategory = null // Simulating null category
        )

        productDisplayHelper.displayProductDetails(
            product,
            productNameTextView,
            productPriceTextView,
            productRatingTextView,
            productCategoryTextView,
            productDetailsTextView
        )

        assertEquals("Product Without Category", productNameTextView.text)
        assertEquals("75.0 BDT", productPriceTextView.text)
        assertEquals("3.5", productRatingTextView.text)
        assertEquals("null", productCategoryTextView.text) // Depending on how null is handled
        assertEquals("This product belongs to no category.", productDetailsTextView.text)
    }

    @Test
    fun testDisplayProductDetailsWithLongProductDetail() {
        val product = ProductDataModel(
            productId = "7",
            productName = "Long Description Product",
            productPrice = 120,
            productRating = 4.8,
            productDetail = "This product has a very long description. " +
                    "It goes on to explain many features and benefits, " +
                    "which are extensive and detailed, providing an in-depth overview.",
            productReview = "Excellent!",
            productCategory = "Gadgets"
        )

        productDisplayHelper.displayProductDetails(
            product,
            productNameTextView,
            productPriceTextView,
            productRatingTextView,
            productCategoryTextView,
            productDetailsTextView
        )

        assertEquals("Long Description Product", productNameTextView.text)
        assertEquals("120.0 BDT", productPriceTextView.text)
        assertEquals("4.8", productRatingTextView.text)
        assertEquals("Gadgets", productCategoryTextView.text)
        assertEquals("This product has a very long description. It goes on to explain many features and benefits, which are extensive and detailed, providing an in-depth overview.", productDetailsTextView.text)
    }


    @Test
    fun testDisplayProductDetailsWithWhitespaceProductName() {
        val product = ProductDataModel(
            productId = "9",
            productName = "   ", // Name with only whitespace
            productPrice = 99,
            productRating = 4.0,
            productDetail = "A product with an empty name.",
            productReview = "Okay.",
            productCategory = "Miscellaneous"
        )

        productDisplayHelper.displayProductDetails(
            product,
            productNameTextView,
            productPriceTextView,
            productRatingTextView,
            productCategoryTextView,
            productDetailsTextView
        )

        assertEquals("   ", productNameTextView.text) // Assuming it retains whitespace
        assertEquals("99.0 BDT", productPriceTextView.text)
        assertEquals("4.0", productRatingTextView.text)
        assertEquals("Miscellaneous", productCategoryTextView.text)
        assertEquals("A product with an empty name.", productDetailsTextView.text)
    }

    @Test
    fun testDisplayProductDetailsWithNonStandardCurrency() {
        val product = ProductDataModel(
            productId = "10",
            productName = "Special Currency Product",
            productPrice = 200,
            productRating = 5.0,
            productDetail = "This product price is in special currency.",
            productReview = "Amazing.",
            productCategory = "Special Items"
        )

        productDisplayHelper.displayProductDetails(
            product,
            productNameTextView,
            productPriceTextView,
            productRatingTextView,
            productCategoryTextView,
            productDetailsTextView
        )

        assertEquals("Special Currency Product", productNameTextView.text)
        assertEquals("200.0 BDT", productPriceTextView.text) // Assuming the format remains the same
        assertEquals("5.0", productRatingTextView.text)
        assertEquals("Special Items", productCategoryTextView.text)
        assertEquals("This product price is in special currency.", productDetailsTextView.text)
    }

    @Test
    fun testDisplayProductDetailsWithEmptyFields() {
        val product = ProductDataModel(
            productId = "1",
            productName = "",
            productPrice = 0,
            productRating = 0.0,
            productDetail = "",
            productReview = "",
            productCategory = ""
        )

        productDisplayHelper.displayProductDetails(
            product,
            productNameTextView,
            productPriceTextView,
            productRatingTextView,
            productCategoryTextView,
            productDetailsTextView
        )

        assertEquals("", productNameTextView.text)
        assertEquals("0.0 BDT", productPriceTextView.text) // Check for zero price
        assertEquals("0.0", productRatingTextView.text) // Check for zero rating
        assertEquals("", productCategoryTextView.text)
        assertEquals("", productDetailsTextView.text)
    }
}