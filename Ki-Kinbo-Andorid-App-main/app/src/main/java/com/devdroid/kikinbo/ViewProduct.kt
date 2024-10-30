package com.devdroid.kikinbo

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devdroid.kikinbo.model.ProductDataModel
import com.devdroid.kikinbo.viewmodel.ProductViewModel

class ViewProduct : AppCompatActivity() {
    private lateinit var productViewModel: ProductViewModel
    private lateinit var productPriceId: TextView
    private lateinit var productRatingId: TextView
    private lateinit var productNameId: TextView
    private lateinit var productCatagoryId: TextView
    private lateinit var productDetailsId: TextView
    private lateinit var productIdToFetch: String
    private lateinit var productDisplayHelper: ProductDisplayHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_product)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        productViewModel = ProductViewModel()
        productDisplayHelper = ProductDisplayHelper()  // Initialize ProductDisplayHelper

        productIdToFetch = intent.getStringExtra("productId") ?: ""

        productNameId = findViewById(R.id.productnameid)
        productPriceId = findViewById(R.id.productPriceid)
        productRatingId = findViewById(R.id.ProductRatingid)
        productCatagoryId = findViewById(R.id.tvProductCatagoryid)
        productDetailsId = findViewById(R.id.productDetailsid)

        if (productIdToFetch.isNotEmpty()) {
            productViewModel.fetchProductData(productIdToFetch) { product ->
                if (product != null) {
                    productDisplayHelper.displayProductDetails(product, productNameId, productPriceId, productRatingId, productCatagoryId, productDetailsId)
                } else {
                    Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Product ID not found", Toast.LENGTH_SHORT).show()
        }
    }
}

//package com.devdroid.kikinbo
//
//import android.os.Bundle
//import android.widget.TextView
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.devdroid.kikinbo.model.ProductDataModel
//import com.devdroid.kikinbo.viewmodel.ProductViewModel
//
//class ViewProduct : AppCompatActivity() {
//    private lateinit var productViewModel: ProductViewModel
//    private lateinit var productPriceId: TextView
//    private lateinit var productRatingId: TextView
//    private lateinit var productNameId: TextView
//    private lateinit var productCatagoryId: TextView
//    private lateinit var productDetailsId: TextView
//    private lateinit var productIdToFetch: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_view_product)
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        productViewModel = ProductViewModel()
//
//        productIdToFetch = intent.getStringExtra("productId") ?: ""
//
//        productNameId = findViewById(R.id.productnameid)
//        productPriceId = findViewById(R.id.productPriceid)
//        productRatingId = findViewById(R.id.ProductRatingid)
//        productCatagoryId = findViewById(R.id.tvProductCatagoryid)
//        productDetailsId = findViewById(R.id.productDetailsid)
//
//        if (productIdToFetch.isNotEmpty()) {
//            productViewModel.fetchProductData(productIdToFetch) { product ->
//                if (product != null) {
//                    displayProductDetails(product)
//                } else {
//                    Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show()
//                }
//            }
//        } else {
//            Toast.makeText(this, "Product ID not found", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun displayProductDetails(product: ProductDataModel) {
//        productNameId.text = product.productName
//        productPriceId.text = "${product.productPrice} BDT"
//        productRatingId.text = "${product.productRating}"
//        productCatagoryId.text = "${product.productCategory}"
//        productDetailsId.text = "${product.productDetail}"
//    }
//}
//
//
////package com.devdroid.kikinbo
////
////import android.os.Bundle
////import android.widget.TextView
////import android.widget.Toast
////import androidx.activity.enableEdgeToEdge
////import androidx.appcompat.app.AppCompatActivity
////import androidx.core.view.ViewCompat
////import androidx.core.view.WindowInsetsCompat
////import com.devdroid.kikinbo.model.ProductDataModel
////import com.google.firebase.database.*
////
////class ViewProduct : AppCompatActivity() {
////    private lateinit var databaseRef: DatabaseReference
////    private lateinit var productIdToFetch: String
////    private lateinit var productPriceId: TextView
////    private lateinit var productRatingId: TextView
////    private lateinit var productNameId: TextView
////    private lateinit var productCatagoryId:TextView
////    private lateinit var productDetailsId:TextView
////
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        enableEdgeToEdge()
////        setContentView(R.layout.activity_view_product)
////
////        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
////            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
////            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
////            insets
////        }
////        databaseRef = FirebaseDatabase.getInstance().getReference("Products")
////        productIdToFetch = intent.getStringExtra("productId") ?: ""
////
////        productNameId = findViewById(R.id.productnameid)
////        productPriceId = findViewById(R.id.productPriceid)
////        productRatingId = findViewById(R.id.ProductRatingid)
////        productCatagoryId=findViewById(R.id.tvProductCatagoryid)
////        productDetailsId=findViewById(R.id.productDetailsid)
////
////
////
////        if (productIdToFetch.isNotEmpty()) {
////            fetchProductData(productIdToFetch)
////        } else {
////            Toast.makeText(this, "Product ID not found", Toast.LENGTH_SHORT).show()
////        }
////    }
////
////    private fun fetchProductData(productId: String) {
////        databaseRef.orderByChild("productId").equalTo(productId)
////            .addListenerForSingleValueEvent(object : ValueEventListener {
////                override fun onDataChange(snapshot: DataSnapshot) {
////                    if (snapshot.exists()) {
////                        for (child in snapshot.children) {
////                            val product = child.getValue(ProductDataModel::class.java)
////                            if (product != null) {
////                                displayProductDetails(product)
////                            } else {
////                                Toast.makeText(this@ViewProduct, "Product not found", Toast.LENGTH_SHORT).show()
////                            }
////                        }
////                    } else {
////                        Toast.makeText(this@ViewProduct, "Product not found", Toast.LENGTH_SHORT).show()
////                    }
////                }
////
////                override fun onCancelled(error: DatabaseError) {
////                    Toast.makeText(this@ViewProduct, "Failed to load data: ${error.message}", Toast.LENGTH_SHORT).show()
////                }
////            })
////    }
////
////    private fun displayProductDetails(product: ProductDataModel) {
////        productNameId.text = product.productName
////        productPriceId.text = "${product.productPrice} BDT"
////        productRatingId.text = "${product.productRating}"
////        productCatagoryId.text="${product.productCategory}"
////        productDetailsId.text="${product.productDetail}"
////
////    }
////}
//
//
////package com.devdroid.kikinbo
////
////import android.os.Bundle
////import android.widget.TextView
////import android.widget.Toast
////import androidx.activity.enableEdgeToEdge
////import androidx.appcompat.app.AppCompatActivity
////import androidx.core.view.ViewCompat
////import androidx.core.view.WindowInsetsCompat
////import com.google.firebase.database.DataSnapshot
////import com.google.firebase.database.DatabaseError
////import com.google.firebase.database.DatabaseReference
////import com.google.firebase.database.FirebaseDatabase
////import com.google.firebase.database.ValueEventListener
////
////class ViewProduct : AppCompatActivity() {
////    private lateinit var databaseRef: DatabaseReference
////    private lateinit var productIdToFetch:String
////    lateinit var productPriceId: TextView
////    lateinit var productRatingId: TextView
////    lateinit var productNameId: TextView
////    lateinit var VproductId:String
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        enableEdgeToEdge()
////        setContentView(R.layout.activity_view_product)
////        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
////            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
////            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
////            insets
////        }
////        databaseRef = FirebaseDatabase.getInstance().getReference("Products")
////        //databaseRef= FirebaseDatabase.getInstance().getReference("Product")
////        //specify the data id you want to fetch
////        productIdToFetch = intent.getStringExtra("Headphone") ?: ""
////        productNameId=findViewById(R.id.productnameid)
////        productPriceId=findViewById(R.id.productPriceid)
////        productRatingId=findViewById(R.id.ProductRatingid)
////
////        // Fetch product data from Firebase
////        if (productIdToFetch.isNotEmpty()) {
////            fetchProductData(productIdToFetch)
////        } else {
////            Toast.makeText(this, "Product ID not found", Toast.LENGTH_SHORT).show()
////        }
////    }
//////    private fun fetchProductData(productId: String) {
//////        // Ensure 'databaseRef' points to the correct 'Products' table in your database.
//////        databaseRef.child("Products").child(productId)
//////            .addListenerForSingleValueEvent(object : ValueEventListener {
//////                override fun onDataChange(snapshot: DataSnapshot) {
//////                    val product = snapshot.getValue(ProductDataModel::class.java)
//////                    if (product != null) {
//////                        productNameId.text = product.productName
//////                        productPriceId.text = "Price: ${product.productPrice} BDT"
//////                        productRatingId.text = "Rating: ${product.productRating}"
//////                    } else {
//////                        Toast.makeText(this@ViewProduct, "Product not found", Toast.LENGTH_SHORT).show()
//////                    }
//////                }
//////
//////                override fun onCancelled(error: DatabaseError) {
//////                    Toast.makeText(this@ViewProduct, "Failed to load data: ${error.message}", Toast.LENGTH_SHORT).show()
//////                }
//////            })
//////    }
////private fun fetchProductData(productId: String) {
////    // Query to find the product with the given productId
////    databaseRef.orderByChild("productId").equalTo(productId)
////        .addListenerForSingleValueEvent(object : ValueEventListener {
////            override fun onDataChange(snapshot: DataSnapshot) {
////                if (snapshot.exists()) {
////                    for (child in snapshot.children) {
////                        val product = child.getValue(ProductDataModel::class.java)
////                        if (product != null) {
////                            displayProductDetails(product)
////                        } else {
////                            Toast.makeText(this@ViewProduct, "Product not found", Toast.LENGTH_SHORT).show()
////                        }
////                    }
////                } else {
////                    Toast.makeText(this@ViewProduct, "Product not found", Toast.LENGTH_SHORT).show()
////                }
////            }
////
////            override fun onCancelled(error: DatabaseError) {
////                Toast.makeText(this@ViewProduct, "Failed to load data: ${error.message}", Toast.LENGTH_SHORT).show()
////            }
////        })
////}
////    private fun displayProductDetails(product: ProductDataModel) {
////        productNameId.text = product.productName
////        productPriceId.text = "Price: ${product.productPrice} BDT"
////        productRatingId.text = "Rating: ${product.productRating}"
////        // Optionally show more details, e.g., productDetail, productReview
////    }
////
////}
