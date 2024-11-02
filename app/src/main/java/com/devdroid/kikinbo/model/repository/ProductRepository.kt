package com.devdroid.kikinbo.repository

import com.devdroid.kikinbo.model.ProductDataModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductRepository(private val dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Products")) {

    // Holds all products loaded from Firebase
    private val productList = mutableListOf<ProductDataModel>()

    fun addProduct(product: ProductDataModel) {
        productList.add(product)
    }
    // Callback to handle data loading
    interface ProductDataCallback {
        fun onDataLoaded(products: List<ProductDataModel>)
        fun onError(error: DatabaseError)
    }

    // Loads data from Firebase
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

    // Filters products by name
    fun filterProductsByName(query: String): List<ProductDataModel> {
        return productList.filter { it.productName?.contains(query, ignoreCase = true) == true }
    }

    // Filters products by category
    fun filterProductsByCategory(category: String): List<ProductDataModel> {
        return productList.filter { it.productCategory?.contains(category, ignoreCase = true) == true }
    }
}
