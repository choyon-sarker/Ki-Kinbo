// ProductSearchViewModel.kt
package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.ProductDataModel

class ProductSearchViewModel(private val productList: List<ProductDataModel>) {

    // Filters the product list based on the provided name query
    fun filterProductsByName(query: String): List<ProductDataModel> {
        return productList.filter {
            it.productName?.contains(query, ignoreCase = true) == true
        }
    }

    // Filters the product list based on the provided category
    fun filterProductsByCategory(category: String): List<ProductDataModel> {
        return productList.filter {
            it.productCategory?.contains(category, ignoreCase = true) == true
        }
    }
    // Filters the product list based on a provided price range
    fun filterProductsByPrice(minPrice: Int? = null, maxPrice: Int? = null): List<ProductDataModel> {
        return productList.filter { product ->
            val price = product.productPrice
            (minPrice == null || (price != null && price >= minPrice)) &&
                    (maxPrice == null || (price != null && price <= maxPrice))
        }
    }

    // Filters the product list based on a minimum rating
    fun filterProductsByRating(minRating: Double? = null): List<ProductDataModel> {
        return productList.filter { product ->
            val rating = product.productRating
            minRating == null || (rating != null && rating >= minRating)
        }
    }
}
