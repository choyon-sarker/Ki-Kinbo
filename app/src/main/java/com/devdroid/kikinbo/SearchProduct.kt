package com.devdroid.kikinbo

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
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchProduct : AppCompatActivity() {

    lateinit var recyclerViewSearch: RecyclerView
    lateinit var productList: ArrayList<ProductDataModel>
    lateinit var filteredList: ArrayList<ProductDataModel>
    lateinit var dbRef: DatabaseReference
    lateinit var ti_productSearch: TextInputEditText
    lateinit var btnSearch: Button
    lateinit var mAdapter: SearchProductAdapter
    lateinit var searchTypeGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerViewSearch = findViewById(R.id.recyclerViewSearchid)
        recyclerViewSearch.layoutManager = LinearLayoutManager(this)
        recyclerViewSearch.setHasFixedSize(true)

        ti_productSearch = findViewById(R.id.ti_productSearchid)
        btnSearch = findViewById(R.id.btnSearchid)
        searchTypeGroup = findViewById(R.id.searchTypeGroup)

        productList = arrayListOf()
        filteredList = arrayListOf()
        mAdapter = SearchProductAdapter(filteredList)
        recyclerViewSearch.adapter = mAdapter

        getProductData()

        // Search operation based on selected option
        btnSearch.setOnClickListener {
            val query = ti_productSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                when (searchTypeGroup.checkedRadioButtonId) {
                    R.id.radioSearchByName -> filterProductsByName(query)
                    R.id.radioSearchByCategory -> filterProductsByCategory(query)
                }
            } else {
                Toast.makeText(this, "Please enter a search query", Toast.LENGTH_SHORT).show()
            }
        }
//        // Search operation by name
//        btnSearch.setOnClickListener {
//            val query = ti_productSearch.text.toString().trim()
//            if (query.isNotEmpty()) {
//                filterProducts(query)
//            } else {
//                Toast.makeText(this, "Please enter a product name", Toast.LENGTH_SHORT).show()
//            }
//        }
//        //search operation by catagory
//        btnSearch.setOnClickListener {
//            val category = ti_productSearch.text.toString().trim()
//            if (category.isNotEmpty()) {
//                filterProductsByCategory(category)
//            } else {
//                Toast.makeText(this, "Please enter a product category", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    private fun getProductData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Products")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear() // Clear previous data
                if (snapshot.exists()) {
                    for (productSnap in snapshot.children) {
                        val productData = productSnap.getValue(ProductDataModel::class.java)
                        productData?.let { productList.add(it) } // Only add non-null products
                    }
                    // Initially, show all products
                    filteredList.clear()
                    filteredList.addAll(productList)
                    mAdapter.notifyDataSetChanged() // Notify adapter of the change
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SearchProduct, "Failed to load products", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //filter produce by Product name
    private fun filterProductsByName(query: String) {
        // Filter the product list based on the query
        filteredList.clear()
        filteredList.addAll(productList.filter {
            it.productName?.contains(query, ignoreCase = true) == true
        })

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No matching products found", Toast.LENGTH_SHORT).show()
        }

        // Notify adapter of data changes
        mAdapter.notifyDataSetChanged()
    }
    //filter produce by product catagory
    private fun filterProductsByCategory(category: String) {
        filteredList.clear()
        filteredList.addAll(productList.filter {
            it.productCategory?.contains(category, ignoreCase = true) == true
        })

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No matching categories found", Toast.LENGTH_SHORT).show()
        }

        mAdapter.notifyDataSetChanged()
    }
}

//import android.os.Bundle
//import android.widget.Button
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.android.material.textfield.TextInputEditText
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener

//class SearchProduct : AppCompatActivity() {
//
//    lateinit var recyclerViewSearch:RecyclerView
//    lateinit var productList: ArrayList<ProductDataModel>
//    lateinit var filteredList: ArrayList<ProductDataModel>
//    lateinit var dbRef:DatabaseReference
//    lateinit var ti_productSearch:TextInputEditText
//    lateinit var btnSearch:Button
//    lateinit var mAdapter: SearchProductAdapter
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_search_product)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//        recyclerViewSearch=findViewById(R.id.recyclerViewSearchid)
//        recyclerViewSearch.layoutManager=LinearLayoutManager(this)
//        recyclerViewSearch.setHasFixedSize(true)
//
//        ti_productSearch=findViewById(R.id.ti_productSearchid)
//        btnSearch=findViewById(R.id.btnSearchid)
//
//        productList= arrayListOf<ProductDataModel>()
//        filteredList = arrayListOf<ProductDataModel>()
//        mAdapter = SearchProductAdapter(filteredList)
//        recyclerViewSearch.adapter = mAdapter
//        getProductData()
//
//        //search operation
//        btnSearch.setOnClickListener {
//            val query = ti_productSearch.text.toString().trim()
//            if (query.isNotEmpty()) {
//                filterProducts(query)
//            } else {
//                Toast.makeText(this, "Please enter a product name", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun getProductData() {
//
//        dbRef=FirebaseDatabase.getInstance().getReference( "Products")
//        dbRef.addValueEventListener(object :ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()){
//                    for(procuctSnap in snapshot.children){
//                        val productData=procuctSnap.getValue(ProductDataModel::class.java)
//                        productList.add(productData!!)
//                    }
//                    val mAdapter=SearchProductAdapter(productList)
//                    recyclerViewSearch.adapter=mAdapter
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//
//    }
//    private fun filterProducts(query: String) {
//        // Filter the product list based on the query
//        filteredList.clear()
//        filteredList.addAll(productList.filter {
//            it.productName?.contains(query, ignoreCase = true) == true
//        })
//
//        if (filteredList.isEmpty()) {
//            Toast.makeText(this, "No matching products found", Toast.LENGTH_SHORT).show()
//        }
//
//        // Notify adapter of data changes
//        mAdapter.notifyDataSetChanged()
//    }
//}