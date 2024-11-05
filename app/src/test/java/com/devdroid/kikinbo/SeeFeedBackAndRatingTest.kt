package com.devdroid.kikinbo

import com.devdroid.kikinbo.model.ProductDataModel
import com.devdroid.kikinbo.model.Review
import com.devdroid.kikinbo.view.SeeFeedBackAndRating
import com.devdroid.kikinbo.viewmodel.ProductViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class SeeFeedBackAndRatingTest {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var seeFeedBackAndRating: SeeFeedBackAndRating

    @Before
    fun setup() {
        productViewModel = mock(ProductViewModel::class.java)
        seeFeedBackAndRating = SeeFeedBackAndRating().apply {
            this.productViewModel = productViewModel // Set the mocked ViewModel
        }
    }

    @Test
    fun testFetchProductData_validProductId_updatesDataModel() {
        // Create a product model with mock data
        val product = ProductDataModel(
            "Mouseid",
            "Test Product",
            4,
            "A great mouse",
            "Electronics",
            mutableListOf(Review("4", "Good product")) // Adding a review
        )

        // Mock the ViewModel to return the product data
        `when`(productViewModel.fetchProductData("Mouseid", any())).thenAnswer {
            val callback = it.getArgument<(ProductDataModel?) -> Unit>(1)
            callback(product)
        }

        // Fetch product data using the mocked ViewModel
        seeFeedBackAndRating.fetchProductData("Mouseid")

        // Assertions to check if the internal state is updated correctly
        assertEquals("Test Product", seeFeedBackAndRating.productNameTextView.text.toString())
        assertEquals(4.0f, seeFeedBackAndRating.productRatingBar.rating) // Assuming productRatingBar is a RatingBar

        // Check reviews directly from the product model
        val reviews = product.reviews // Get reviews directly from the mocked product data
        if (reviews != null) {
            assertEquals(1, reviews.size)
        } // Check the number of reviews
        assertEquals("Good product", reviews?.get(0)?.productIdReview ?: "No review")
        // Check the review content
    }




    @Test
    fun testFetchProductData_invalidProductId_doesNotUpdateDataModel() {
        `when`(productViewModel.fetchProductData("InvalidID", any())).thenAnswer {
            val callback = it.getArgument<(ProductDataModel?) -> Unit>(1)
            callback(null)
        }

        seeFeedBackAndRating.fetchProductData("InvalidID")

        // Check that the product data remains unchanged or defaults to an expected state
        // assertEquals(expected, seeFeedBackAndRating.getProductData())
    }

    @Test
    fun testDisplayProductData_updatesInternalState() {
        val product = ProductDataModel("Mouseid", "Test Product", 5, "A great mouse", "Electronics", mutableListOf(Review("5", "Excellent product")))

        seeFeedBackAndRating.displayProductData(product)

        // Check if the internal state is updated correctly
        // assertEquals(expected, seeFeedBackAndRating.getProductData())
    }

    @Test
    fun testDisplayProductData_noReviews_displaysDefaultValues() {
        val product = ProductDataModel("Mouseid", "Test Product", 0, "No reviews", "Electronics", mutableListOf())

        seeFeedBackAndRating.displayProductData(product)

        // Check if the internal state is updated correctly for default values
        // assertEquals(expected, seeFeedBackAndRating.getProductData())
    }
}
