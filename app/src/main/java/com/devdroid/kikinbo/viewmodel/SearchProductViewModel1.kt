//package com.devdroid.kikinbo.viewmodel
//
//import com.devdroid.kikinbo.model.ProductDataModel
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
///**
// * ViewModel class responsible for managing and filtering product data.
// *
// * This class retrieves product data from the Firebase Realtime Database, maintains a
// * list of products, and provides filtering functions to search by name or category.
// */
//class SearchProductViewModel {
//
//    // Reference to the "Products" node in Firebase Realtime Database
//    private val dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Products")
//
//    // List of all retrieved products from the database
//    val productList = arrayListOf<ProductDataModel>()
//
//    // List of products filtered based on search criteria
//    val filteredList = arrayListOf<ProductDataModel>()
//
//    /**
//     * Loads product data from Firebase and populates the product list.
//     *
//     * This function listens for changes to the "Products" node in the Firebase Realtime Database.
//     * Upon data change, it updates the productList and filteredList, and then triggers the callback
//     * [onDataLoaded] to return the loaded data.
//     *
//     * @param onDataLoaded Callback function triggered after loading data, providing the list of products.
//     */
//    fun loadProductData(onDataLoaded: (List<ProductDataModel>) -> Unit) {
//        dbRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                productList.clear()
//                if (snapshot.exists()) {
//                    for (productSnap in snapshot.children) {
//                        val productData = productSnap.getValue(ProductDataModel::class.java)
//                        productData?.let { productList.add(it) }
//                    }
//                    filteredList.clear()
//                    filteredList.addAll(productList)
//                    onDataLoaded(filteredList)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Handle error if needed
//            }
//        })
//    }
//
//    /**
//     * Filters the product list based on a provided name query.
//     *
//     * This function searches the product list for products containing the [query] string
//     * in their name, ignoring case.
//     *
//     * @param query The search query to filter products by name.
//     * @return List of products matching the name query.
//     */
//    fun filterProductsByName(query: String): List<ProductDataModel> {
//        return productList.filter {
//            it.productName?.contains(query, ignoreCase = true) == true
//        }
//    }
//
//    /**
//     * Filters the product list based on a provided category.
//     *
//     * This function searches the product list for products belonging to the specified [category],
//     * ignoring case.
//     *
//     * @param category The category to filter products by.
//     * @return List of products matching the specified category.
//     */
//    fun filterProductsByCategory(category: String): List<ProductDataModel> {
//        return productList.filter {
//            it.productCategory?.contains(category, ignoreCase = true) == true
//        }
//    }
//}

