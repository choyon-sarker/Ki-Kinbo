package com.devdroid.kikinbo.viewmodel

import com.devdroid.kikinbo.model.ProductDataModel
import com.google.firebase.database.*

class ProductViewModel {
    private val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Products")

    fun fetchProductData(productId: String, callback: (ProductDataModel?) -> Unit) {
        databaseRef.orderByChild("productId").equalTo(productId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (child in snapshot.children) {
                            val product = child.getValue(ProductDataModel::class.java)
                            callback(product)
                            return // Exit after finding the product
                        }
                    }
                    callback(null) // No product found
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(null) // Handle error by returning null
                }
            })
    }
}
