package com.devdroid.kikinbo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AddToCart : AppCompatActivity() {
    private lateinit var tvProductPrice: TextView
    private lateinit var btnIncrease: Button
    private lateinit var btnDecrease: Button
    private lateinit var btnProceedToCheckout: Button

    private var quantity: Int = 0
    private var productPrice: Int = 0 // Will be fetched from the database
    private val productName: String = "Headphone" // Product ID from Firebase

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_cart)

        tvProductPrice = findViewById(R.id.tvProductPrice)
        btnIncrease = findViewById(R.id.btnIncrease)
        btnDecrease = findViewById(R.id.btnDecrease)
        btnProceedToCheckout = findViewById(R.id.btnProceedToCheckout)

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance().getReference("products")

        // Fetch only the product price from the database
        fetchProductPrice(productName)

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

    private fun fetchProductPrice(productName: String) {
        // Query the database for the product with the given productId and only retrieve the price
        database.child(productName).child("productPrice").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Fetch the product price
                    productPrice = dataSnapshot.getValue(Int::class.java) ?: 0
                    tvProductPrice.text = "Price: $$productPrice"
                    updateTotalAmount()
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
