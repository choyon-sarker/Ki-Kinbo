package com.devdroid.kikinbo.viewmodel

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

/**
 * The SearchProductView activity enables users to search for products
 * by name or category, displaying the filtered results in a RecyclerView.
 * It interacts with [ProductRepository] to load product data from Firebase
 * and applies search criteria based on user input.
 */
class SearchProduct : AppCompatActivity() {

    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var ti_productSearch: TextInputEditText
    private lateinit var btnSearch: Button
    private lateinit var mAdapter: SearchProductAdapter
    private lateinit var searchTypeGroup: RadioGroup

    // Reference to ProductRepository
    private val productRepository = ProductRepository()

    // List of products filtered based on search criteria
    private val filteredList = arrayListOf<ProductDataModel>()

    /**
     * Initializes the activity, sets up the RecyclerView and search functionality,
     * and loads the product data from Firebase.
     *
     * @param savedInstanceState The previously saved state of the activity, if any.
     */
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

        // Load product data from ProductRepository
        productRepository.loadProductData(object : ProductRepository.ProductDataCallback {
            override fun onDataLoaded(products: List<ProductDataModel>) {
                filteredList.clear()
                filteredList.addAll(products)
                mAdapter.notifyDataSetChanged()
            }

            override fun onError(error: DatabaseError) {
                Toast.makeText(this@SearchProduct, "Failed to load products", Toast.LENGTH_SHORT).show()
            }
        })

        // Set click listener for the search button
        btnSearch.setOnClickListener {
            val query = ti_productSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                val normalizedQuery = query.trim()
                val filteredResults = when (searchTypeGroup.checkedRadioButtonId) {
                    R.id.radioSearchByName -> productRepository.filterProductsByName(normalizedQuery)
                    R.id.radioSearchByCategory -> productRepository.filterProductsByCategory(normalizedQuery)
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
