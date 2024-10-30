package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.ProductDataModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchProductViewModel {

    private val dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Products")
    private val productList = arrayListOf<ProductDataModel>()
    val filteredList = arrayListOf<ProductDataModel>()

    fun loadProductData(onDataLoaded: (List<ProductDataModel>) -> Unit) {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                if (snapshot.exists()) {
                    for (productSnap in snapshot.children) {
                        val productData = productSnap.getValue(ProductDataModel::class.java)
                        productData?.let { productList.add(it) }
                    }
                    filteredList.clear()
                    filteredList.addAll(productList)
                    onDataLoaded(filteredList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error if needed
            }
        })
    }

    fun filterProductsByName(query: String): List<ProductDataModel> {
        return productList.filter {
            it.productName?.contains(query, ignoreCase = true) == true
        }
    }

    fun filterProductsByCategory(category: String): List<ProductDataModel> {
        return productList.filter {
            it.productCategory?.contains(category, ignoreCase = true) == true
        }
    }
}
