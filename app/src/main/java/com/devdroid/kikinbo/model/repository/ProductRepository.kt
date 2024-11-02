// ProductRepository.kt
package com.devdroid.kikinbo.repository

import com.devdroid.kikinbo.model.ProductDataModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Repository class for managing product data interactions with Firebase.
 * Handles data loading, adding products, and filtering products by name or category.
 *
 * @property dbRef Firebase database reference for the "Products" node.
 */
class ProductRepository(private val dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Products")) {

    // Holds all products loaded from Firebase
    private val productList = mutableListOf<ProductDataModel>()

    /**
     * Adds a product to the in-memory product list.
     *
     * @param product The product to be added.
     */
    fun addProduct(product: ProductDataModel) {
        productList.add(product)
    }

    /**
     * Callback interface to handle product data loading events.
     */
    interface ProductDataCallback {
        /**
         * Called when product data is successfully loaded from Firebase.
         *
         * @param products The list of loaded products.
         */
        fun onDataLoaded(products: List<ProductDataModel>)

        /**
         * Called when an error occurs while loading product data.
         *
         * @param error The database error that occurred.
         */
        fun onError(error: DatabaseError)
    }

    /**
     * Loads product data from Firebase and triggers callback with the result.
     *
     * @param callback The callback to handle data loading events.
     */
    fun loadProductData(callback: ProductDataCallback) {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                for (productSnap in snapshot.children) {
                    val productData = productSnap.getValue(ProductDataModel::class.java)
                    productData?.let { productList.add(it) }
                }
                callback.onDataLoaded(productList)
            }

            override fun onCancelled(error: DatabaseError) {
                callback.onError(error)
            }
        })
    }

    /**
     * Filters the product list based on the provided name query.
     *
     * @param query The search term to match product names against.
     * @return A list of products with names that contain the query string, ignoring case.
     */
    fun filterProductsByName(query: String): List<ProductDataModel> {
        return productList.filter { it.productName?.contains(query, ignoreCase = true) == true }
    }

    /**
     * Filters the product list based on the provided category.
     *
     * @param category The category term to match products against.
     * @return A list of products within the specified category, ignoring case.
     */
    fun filterProductsByCategory(category: String): List<ProductDataModel> {
        return productList.filter { it.productCategory?.contains(category, ignoreCase = true) == true }
    }
}
