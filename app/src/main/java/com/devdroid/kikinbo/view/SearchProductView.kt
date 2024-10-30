

package com.devdroid.kikinbo.view

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devdroid.kikinbo.R
import com.devdroid.kikinbo.view.adapter.SearchProductAdapter
import com.devdroid.kikinbo.viewmodel.SearchProductViewModel
import com.google.android.material.textfield.TextInputEditText

class SearchProductView : AppCompatActivity() {

    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var ti_productSearch: TextInputEditText
    private lateinit var btnSearch: Button
    private lateinit var mAdapter: SearchProductAdapter
    private lateinit var searchTypeGroup: RadioGroup
    private val viewModel = SearchProductViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_product)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        recyclerViewSearch = findViewById(R.id.recyclerViewSearchid)
        ti_productSearch = findViewById(R.id.ti_productSearchid)
        btnSearch = findViewById(R.id.btnSearchid)
        searchTypeGroup = findViewById(R.id.searchTypeGroup)

        // Set up RecyclerView
        recyclerViewSearch.layoutManager = LinearLayoutManager(this)
        recyclerViewSearch.setHasFixedSize(true)

        // Initialize adapter with the filtered list from ViewModel
        mAdapter = SearchProductAdapter(viewModel.filteredList)
        recyclerViewSearch.adapter = mAdapter

        // Load product data
        viewModel.loadProductData {
            mAdapter.notifyDataSetChanged() // Notify adapter that data has changed
        }

        // Set click listener for the search button
        btnSearch.setOnClickListener {
            val query = ti_productSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                val filteredResults = when (searchTypeGroup.checkedRadioButtonId) {
                    R.id.radioSearchByName -> viewModel.filterProductsByName(query)
                    R.id.radioSearchByCategory -> viewModel.filterProductsByCategory(query)
                    else -> emptyList()
                }

                viewModel.filteredList.clear()
                viewModel.filteredList.addAll(filteredResults)

                if (filteredResults.isEmpty()) {
                    Toast.makeText(this, "No matching products found", Toast.LENGTH_SHORT).show()
                }
                mAdapter.notifyDataSetChanged() // Notify adapter of data changes
            } else {
                Toast.makeText(this, "Please enter a search query", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

