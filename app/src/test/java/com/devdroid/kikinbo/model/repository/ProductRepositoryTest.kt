package com.devdroid.kikinbo.repository

import com.devdroid.kikinbo.model.ProductDataModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductRepositoryTest {

    private lateinit var productRepository: ProductRepository

    @Before
    fun setUp() {
        // Initialize ProductRepository without actual Firebase
        productRepository = ProductRepository()

        // Add sample data to productList for testing
        productRepository.addProduct(ProductDataModel("1", "Headphones", 29, 4.5, "Great sound quality", "High bass", "Electronics"))
        productRepository.addProduct(ProductDataModel("2", "Mouse", 19, 4.3, "Ergonomic design", "Smooth operation", "Electronics"))
        productRepository.addProduct(ProductDataModel("3", "Juice", 1, 4.0, "Refreshing", "Tasty", "Beverages"))
        productRepository.addProduct(ProductDataModel("4", "Pen", 2, 4.1, "Smooth writing", "Writing", "Stationery"))
        productRepository.addProduct(ProductDataModel("5", "Pencil", 0, 4.5, "Good for sketching", "Drawing", "Stationery"))
    }

    @Test
    fun filterProductsByName_shouldReturnCorrectProducts() {
        val result = productRepository.filterProductsByName("head")
        assertEquals(1, result.size)
        assertEquals("Headphones", result[0].productName)
    }

    @Test
    fun filterProductsByCategory_shouldReturnCorrectProducts() {
        val result = productRepository.filterProductsByCategory("Electronics")
        assertEquals(2, result.size)
        assertEquals("Headphones", result[0].productName)
        assertEquals("Mouse", result[1].productName)
    }
}
