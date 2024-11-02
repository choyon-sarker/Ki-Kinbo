package com.devdroid.kikinbo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class AddToCart : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var tvProductName: TextView
    private lateinit var tvProductPrice: TextView
    private lateinit var btnIncrease: Button
    private lateinit var btnDecrease: Button
    private lateinit var btnProceedToCheckout: Button




    private var quantity: Int = 0
    private var productPrice: Int = 0 // Will be fetched from the database
    // Product ID from Firebase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_cart)

        database = FirebaseDatabase.getInstance().getReference("Products")
        tvProductName = findViewById(R.id.tvProductName)
        tvProductPrice = findViewById(R.id.tvProductPrice)
        btnIncrease = findViewById(R.id.btnIncrease)
        btnDecrease = findViewById(R.id.btnDecrease)
        btnProceedToCheckout = findViewById(R.id.btnProceedToCheckout)

        // Initialize Firebase Database
        // Fetch product data from Firebase

        // Fetch only the product price from the database
        fetchProductData()

        btnIncrease.setOnClickListener {
            quantity++
            updateTotalAmount()
        }

        btnDecrease.setOnClickListener {
            if (quantity > 0) {
                quantity--
                updateTotalAmount()
            }
        }

        btnProceedToCheckout.setOnClickListener {
            Toast.makeText(this, "Proceeding to Checkout", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchProductData() {
        val productName = "Headphone"
        // Query the database for the product with the given productName
        database.orderByChild("productName").equalTo(productName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (dataSnapshot.exists()) {
                        // Retrieve the product data as a ProductDataModel object
                        val product = dataSnapshot.children.first()
                            .getValue(ProductDataModel::class.java)

                        // Ensure the product is not null
                        if (product != null) {
                            productPrice = product.productPrice ?: 0
                            tvProductPrice.text = "Price: $$productPrice"
                            updateTotalAmount()
                        } else {
                            Toast.makeText(this@AddToCart, "Product data is null!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@AddToCart, "Product not found!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@AddToCart, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }


    private fun updateTotalAmount() {
        // Updates the total amount based on quantity and product price
        val totalAmount = quantity * productPrice
        findViewById<TextView>(R.id.tvTotalAmount).text = "Total Amount: $$totalAmount"
    }
}
