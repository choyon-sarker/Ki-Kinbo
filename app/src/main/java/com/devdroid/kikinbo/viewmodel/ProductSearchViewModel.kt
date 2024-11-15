// ProductSearchViewModel.kt
package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.ProductDataModel

/**
 * ViewModel class that handles product search operations based on user input.
 * Provides methods to filter a list of products by name or category.
 *
 * @property productList The list of products available for search and filtering.
 */
class ProductSearchViewModel(private val productList: List<ProductDataModel>) {

    /**
     * Filters the product list based on the provided name query.
     *
     * @param query The search term to match product names against.
     * @return A list of products with names that contain the query string, ignoring case.
     */

    fun filterProductsByName(query: String): List<ProductDataModel> {
        val trimmedQuery = query.trim()
        return productList.filter {
            it.productName?.contains(trimmedQuery, ignoreCase = true) == true
        }
    }

    /**
     * Filters the product list based on the provided category.
     *
     * @param category The category term to match products against.
     * @return A list of products within the specified category, ignoring case.
     */
    fun filterProductsByCategory(category: String): List<ProductDataModel> {
        val trimmedCategory = category.trim()
        return productList.filter {
            it.productCategory?.contains(trimmedCategory, ignoreCase = true) == true
        }
    }
}
