package com.devdroid.kikinbo.view

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devdroid.kikinbo.ProductDisplayHelper
import com.devdroid.kikinbo.R
import com.devdroid.kikinbo.viewmodel.ProductViewModel

/**
 * Activity to display detailed information about a selected product.
 * Retrieves product data from the `ProductViewModel` and displays it using `ProductDisplayHelper`.
 * Handles insets to ensure proper padding for system UI elements.
 */
class ViewProduct : AppCompatActivity() {

    // ViewModel to handle product data retrieval
    private lateinit var productViewModel: ProductViewModel

    // TextView references for product details
    private lateinit var productNameId: TextView
    private lateinit var productPriceId: TextView
    private lateinit var productRatingId: TextView
    private lateinit var productCategoryId: TextView
    private lateinit var productDetailsId: TextView

    // Helper class to display product information
    private lateinit var productDisplayHelper: ProductDisplayHelper

    // Product ID passed through intent to fetch data
    private lateinit var productIdToFetch: String

    /**
     * Initializes the activity, sets up UI elements, and handles intent data.
     * Applies system UI padding and retrieves product data based on the product ID.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Enable immersive mode for the activity
        setContentView(R.layout.activity_view_product)

        applyWindowInsets()  // Handle system insets for proper padding

        // Initialize ViewModel and Helper
        productViewModel = ProductViewModel()
        productDisplayHelper = ProductDisplayHelper()

        // Retrieve the product ID from intent extras
        productIdToFetch = intent.getStringExtra("productId") ?: ""

        // Bind UI elements to their corresponding views
        bindViews()

        // Fetch and display product data
        if (productIdToFetch.isNotEmpty()) {
            fetchAndDisplayProduct()
        } else {
            showToast("Product ID not found")
        }
    }

    /**
     * Applies system window insets to adjust padding dynamically for UI elements.
     */
    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * Binds TextView elements from the layout to the corresponding variables.
     */
    private fun bindViews() {
        productNameId = findViewById(R.id.productnameid)
        productPriceId = findViewById(R.id.productPriceid)
        productRatingId = findViewById(R.id.ProductRatingid)
        productCategoryId = findViewById(R.id.tvProductCatagoryid)
        productDetailsId = findViewById(R.id.productDetailsid)
    }

    /**
     * Fetches product data from the ViewModel and displays it using the helper class.
     */
    private fun fetchAndDisplayProduct() {
        productViewModel.fetchProductData(productIdToFetch) { product ->
            if (product != null) {
                productDisplayHelper.displayProductDetails(
                    product,
                    productNameId,
                    productPriceId,
                    productRatingId,
                    productCategoryId,
                    productDetailsId
                )
            } else {
                showToast("Product not found")
            }
        }
    }

    /**
     * Displays a short Toast message.
     * @param message The message to be shown in the Toast.
     */
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
