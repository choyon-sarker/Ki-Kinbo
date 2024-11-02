package com.devdroid.kikinbo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devdroid.kikinbo.model.repository.IProductRepository
import com.devdroid.kikinbo.model.ProductDataModel
import org.junit.Rule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ViewProductViewModelTest {

    private lateinit var repository: IProductRepository
    private lateinit var viewModel: ViewProductViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @BeforeEach
    fun setUp() {
        repository = mock()
        viewModel = ViewProductViewModel(repository)
    }

    @Test
    fun `getProductData returns formatted ProductViewData when product exists`() {
        val productId = "123"
        val product = ProductDataModel(
            productId = productId,
            productName = "Sample Product",
            productPrice = 500,
            productRating = 4.5,
            productDetail = "Product details",
            productCategory = "Electronics",
            productReview = "Great product!"
        )

        whenever(repository.fetchProductById(any(), any())).thenAnswer {
            val callback = it.getArgument<(ProductDataModel?) -> Unit>(1)
            callback(product) // Simulate fetching the product
        }

        viewModel.getProductData(productId) { productViewData ->
            assertEquals("Sample Product", productViewData?.productName)
            assertEquals(500, productViewData?.productPrice)
            assertEquals(4.5, productViewData?.productRating)
            assertEquals("Electronics", productViewData?.productCategory)
            assertEquals("Product details", productViewData?.productDetail)
        }
    }

    //Product Not Found
    @Test
    fun `getProductData returns null when product does not exist`() {
        val productId = "non_existing_id"

        whenever(repository.fetchProductById(any(), any())).thenAnswer {
            val callback = it.getArgument<(ProductDataModel?) -> Unit>(1)
            callback(null) // Simulate not finding the product
        }

        viewModel.getProductData(productId) { productViewData ->
            assertNull(productViewData) // Expecting null
        }
    }

    //Fetching Product With Error
    @Test
    fun `getProductData returns null when there is an error fetching the product`() {
        val productId = "123"

        whenever(repository.fetchProductById(any(), any())).thenAnswer {
            val callback = it.getArgument<(ProductDataModel?) -> Unit>(1)
            callback(null) // Simulating an error by returning null
        }

        viewModel.getProductData(productId) { productViewData ->
            assertNull(productViewData) // Expecting null
        }
    }

    //Product Data Conversion
    @Test
    fun `getProductData returns properly formatted ProductViewData`() {
        val productId = "123"
        val product = ProductDataModel(
            productId = productId,
            productName = "Sample Product",
            productPrice = 500,
            productRating = 4.5,
            productDetail = "Product details",
            productCategory = "Electronics",
            productReview = "Great product!"
        )

        whenever(repository.fetchProductById(any(), any())).thenAnswer {
            val callback = it.getArgument<(ProductDataModel?) -> Unit>(1)
            callback(product) // Simulate fetching the product
        }

        viewModel.getProductData(productId) { productViewData ->
            assertNotNull(productViewData)
            assertEquals("Sample Product", productViewData?.productName)
            assertEquals("500", productViewData?.productPrice?.toString()) // Assuming you format price
            assertEquals("4.5", productViewData?.productRating?.toString()) // Assuming you format rating
            assertEquals("Electronics", productViewData?.productCategory)
            assertEquals("Product details", productViewData?.productDetail)
        }
    }

    //Null Product Data
    @Test
    fun `getProductData does not crash when product data is null`() {
        val productId = "123"

        whenever(repository.fetchProductById(any(), any())).thenAnswer {
            val callback = it.getArgument<(ProductDataModel?) -> Unit>(1)
            callback(null) // Simulate fetching null product
        }

        viewModel.getProductData(productId) { productViewData ->
            assertNull(productViewData) // Expecting null, should not crash
        }
    }

    //verify product id
    @Test
    fun `getProductData calls repository with correct product ID`() {
        val productId = "123"
        val product = ProductDataModel(
            productId = productId,
            productName = "Sample Product",
            productPrice = 500,
            productRating = 4.5,
            productDetail = "Product details",
            productCategory = "Electronics",
            productReview = "Great product!"
        )

        whenever(repository.fetchProductById(eq(productId), any())).thenAnswer {
            val callback = it.getArgument<(ProductDataModel?) -> Unit>(1)
            callback(product) // Simulate fetching the product
        }

        viewModel.getProductData(productId) { productViewData ->
            // No assertions needed, just verifying that the repository was called with the correct ID
            verify(repository).fetchProductById(eq(productId), any())
        }
    }

    //Empty product id
    @Test
    fun `getProductData returns null when product ID is empty`() {
        val productId = ""

        viewModel.getProductData(productId) { productViewData ->
            assertNull(productViewData) // Expecting null since the ID is empty
        }
    }
    //Valid Product with No Rating
    @Test
    fun `getProductData handles product with no rating`() {
        val productId = "123"
        val product = ProductDataModel(
            productId = productId,
            productName = "Sample Product",
            productPrice = 500,
            productRating = null, // No rating
            productDetail = "Product details",
            productCategory = "Electronics",
            productReview = "Great product!"
        )

        whenever(repository.fetchProductById(any(), any())).thenAnswer {
            val callback = it.getArgument<(ProductDataModel?) -> Unit>(1)
            callback(product) // Simulate fetching the product
        }

        viewModel.getProductData(productId) { productViewData ->
            assertNotNull(productViewData)
            assertNull(productViewData?.productRating) // Expecting null rating
        }
    }

    //Long Product Name
    @Test
    fun `getProductData handles product with long name`() {
        val productId = "123"
        val longName = "This is a very long product name that exceeds typical lengths"
        val product = ProductDataModel(
            productId = productId,
            productName = longName,
            productPrice = 500,
            productRating = 4.5,
            productDetail = "Product details",
            productCategory = "Electronics",
            productReview = "Great product!"
        )

        whenever(repository.fetchProductById(any(), any())).thenAnswer {
            val callback = it.getArgument<(ProductDataModel?) -> Unit>(1)
            callback(product) // Simulate fetching the product
        }

        viewModel.getProductData(productId) { productViewData ->
            assertNotNull(productViewData)
            assertEquals(longName, productViewData?.productName) // Expecting the long name
        }
    }

    //Handling Special Characters
    @Test
    fun `getProductData handles product with special characters`() {
        val productId = "123"
        val product = ProductDataModel(
            productId = productId,
            productName = "Sample Product & Co.",
            productPrice = 500,
            productRating = 4.5,
            productDetail = "Product details with special characters !@#",
            productCategory = "Electronics",
            productReview = "Great product!"
        )

        whenever(repository.fetchProductById(any(), any())).thenAnswer {
            val callback = it.getArgument<(ProductDataModel?) -> Unit>(1)
            callback(product) // Simulate fetching the product
        }

        viewModel.getProductData(productId) { productViewData ->
            assertNotNull(productViewData)
            assertEquals("Sample Product & Co.", productViewData?.productName) // Expecting the product name with special characters
            assertEquals("Product details with special characters !@#", productViewData?.productDetail) // Expecting details with special characters
        }
    }

    //performance test
    @Test
    fun `getProductData performs efficiently`() {
        val productId = "123"
        val product = ProductDataModel(
            productId = productId,
            productName = "Sample Product",
            productPrice = 500,
            productRating = 4.5,
            productDetail = "Product details",
            productCategory = "Electronics",
            productReview = "Great product!"
        )

        whenever(repository.fetchProductById(any(), any())).thenAnswer {
            val callback = it.getArgument<(ProductDataModel?) -> Unit>(1)
            callback(product) // Simulate fetching the product
        }

        val startTime = System.currentTimeMillis()
        viewModel.getProductData(productId) { productViewData ->
            val duration = System.currentTimeMillis() - startTime
            assertNotNull(productViewData)
            assertTrue(duration < 2000) // Expecting the fetch to complete within 2 seconds
        }
    }




}
