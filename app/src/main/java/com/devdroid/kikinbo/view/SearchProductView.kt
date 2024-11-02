
package com.devdroid.kikinbo.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devdroid.kikinbo.R
import com.devdroid.kikinbo.model.ProductDataModel
import com.devdroid.kikinbo.repository.ProductRepository
import com.devdroid.kikinbo.view.adapter.SearchProductAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseError

class SearchProductView : AppCompatActivity() {

    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var ti_productSearch: TextInputEditText
    private lateinit var btnSearch: Button
    private lateinit var mAdapter: SearchProductAdapter
    private lateinit var searchTypeGroup: RadioGroup

    // Reference to ProductRepository
    private val productRepository = ProductRepository()

    // List of products filtered based on search criteria
    private val filteredList = arrayListOf<ProductDataModel>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        // Initialize views
        recyclerViewSearch = findViewById(R.id.recyclerViewSearchid)
        ti_productSearch = findViewById(R.id.ti_productSearchid)
        btnSearch = findViewById(R.id.btnSearchid)
        searchTypeGroup = findViewById(R.id.searchTypeGroup)

        // Set up RecyclerView
        recyclerViewSearch.layoutManager = LinearLayoutManager(this)
        mAdapter = SearchProductAdapter(filteredList)
        recyclerViewSearch.adapter = mAdapter

        // Load product data
        productRepository.loadProductData(object : ProductRepository.ProductDataCallback {
            override fun onDataLoaded(products: List<ProductDataModel>) {
                filteredList.clear()
                filteredList.addAll(products)
                mAdapter.notifyDataSetChanged()
            }

            override fun onError(error: DatabaseError) {
                Toast.makeText(this@SearchProductView, "Failed to load products", Toast.LENGTH_SHORT).show()
            }
        })

        // Set click listener for the search button
        btnSearch.setOnClickListener {
            val query = ti_productSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                val filteredResults = when (searchTypeGroup.checkedRadioButtonId) {
                    R.id.radioSearchByName -> productRepository.filterProductsByName(query)
                    R.id.radioSearchByCategory -> productRepository.filterProductsByCategory(query)
                    else -> emptyList()
                }

                filteredList.clear()
                filteredList.addAll(filteredResults)

                if (filteredResults.isEmpty()) {
                    Toast.makeText(this, "No matching products found", Toast.LENGTH_SHORT).show()
                }
                mAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Please enter a search query", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


//package com.devdroid.kikinbo.viewmodel
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.widget.Button
//import android.widget.RadioGroup
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.devdroid.kikinbo.R
//import com.devdroid.kikinbo.model.ProductDataModel
//import com.devdroid.kikinbo.view.adapter.SearchProductAdapter
//import com.google.android.material.textfield.TextInputEditText
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//class SearchProductViewModel : AppCompatActivity() {
//
//    private lateinit var recyclerViewSearch: RecyclerView
//    private lateinit var ti_productSearch: TextInputEditText
//    private lateinit var btnSearch: Button
//    private lateinit var mAdapter: SearchProductAdapter
//    private lateinit var searchTypeGroup: RadioGroup
//
//    // Firebase Database reference
//    private val dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Products")
//
//    // List of all retrieved products from the database
//    val productList = arrayListOf<ProductDataModel>()
//
//    // List of products filtered based on search criteria
//    val filteredList = arrayListOf<ProductDataModel>()
//
//    @SuppressLint("ClickableViewAccessibility")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_search_product)
//
//        // Set up window insets for edge-to-edge display
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        // Initialize views
//        recyclerViewSearch = findViewById(R.id.recyclerViewSearchid)
//        ti_productSearch = findViewById(R.id.ti_productSearchid)
//        btnSearch = findViewById(R.id.btnSearchid)
//        searchTypeGroup = findViewById(R.id.searchTypeGroup)
//
//        // Set up RecyclerView
//        recyclerViewSearch.layoutManager = LinearLayoutManager(this)
//        recyclerViewSearch.setHasFixedSize(true)
//
//        // Initialize adapter with the empty filtered list
//        mAdapter = SearchProductAdapter(filteredList)
//        recyclerViewSearch.adapter = mAdapter
//
//        // Load product data from Firebase
//        loadProductData()
//
//        // Set click listener for the search button
//        btnSearch.setOnClickListener {
//            val query = ti_productSearch.text.toString().trim()
//            if (query.isNotEmpty()) {
//                val filteredResults = when (searchTypeGroup.checkedRadioButtonId) {
//                    R.id.radioSearchByName -> filterProductsByName(query)
//                    R.id.radioSearchByCategory -> filterProductsByCategory(query)
//                    else -> emptyList()
//                }
//
//                filteredList.clear()
//                filteredList.addAll(filteredResults)
//
//                if (filteredResults.isEmpty()) {
//                    Toast.makeText(this, "No matching products found", Toast.LENGTH_SHORT).show()
//                }
//                mAdapter.notifyDataSetChanged() // Notify adapter of data changes
//            } else {
//                Toast.makeText(this, "Please enter a search query", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    /**
//     * Loads product data from Firebase and populates the product list.
//     */
//    private fun loadProductData() {
//        dbRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                productList.clear()
//                if (snapshot.exists()) {
//                    for (productSnap in snapshot.children) {
//                        val productData = productSnap.getValue(ProductDataModel::class.java)
//                        productData?.let { productList.add(it) }
//                    }
//                    filteredList.clear()
//                    filteredList.addAll(productList) // Initially load all products
//                    mAdapter.notifyDataSetChanged() // Notify adapter that data has changed
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Handle error if needed
//                Toast.makeText(this@SearchProductViewModel, "Failed to load products", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//
//    /**
//     * Filters the product list based on a provided name query.
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
//     * @param category The category to filter products by.
//     * @return List of products matching the specified category.
//     */
//    private fun filterProductsByCategory(category: String): List<ProductDataModel> {
//        return productList.filter {
//            it.productCategory?.contains(category, ignoreCase = true) == true
//        }
//    }
//}
