package com.devdroid.kikinbo.viewmodel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.kikinbo.R
import com.google.firebase.database.*

/**
 * The `AddToCart` activity provides an interface for users to add products to their cart.
 * It fetches product information from Firebase, displays the price, and enables the user
 * to adjust the quantity and view the total amount before proceeding to checkout.
 */
class AddToCart : AppCompatActivity() {

    // Firebase Database reference used for accessing product data
    private lateinit var databaseReference: DatabaseReference

    // UI elements for displaying product name, price, and quantity controls
    private var productNameTextView: TextView = TODO()
    private var productPriceTextView: TextView
    private var increaseButton: Button
    private var decreaseButton: Button
    private var proceedToCheckoutButton: Button

    // Tracks the selected quantity of the product
    var quantity: Int = 0

    // Stores the price of the product retrieved from Firebase
    var productPrice: Int = 0

    /**
     * Initializes the activity, sets up the UI elements, Firebase reference, and listeners
     * for the quantity control buttons and checkout process.
     *
     * @param savedInstanceState The saved instance state bundle.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_cart)

        // Set up Firebase Database reference pointing to "Products" node
        databaseReference = FirebaseDatabase.getInstance().getReference("Products")

        // Initialize UI elements for product name, price, and control buttons
        productNameTextView = findViewById(R.id.tvProductName)
        productPriceTextView = findViewById(R.id.tvProductPrice)
        increaseButton = findViewById(R.id.btnIncrease)
        decreaseButton = findViewById(R.id.btnDecrease)
        proceedToCheckoutButton = findViewById(R.id.btnProceedToCheckout)

        // Fetch and display product data from Firebase
        // fetchProductData()

        // Set click listeners for increasing and decreasing product quantity
        increaseButton.setOnClickListener {
            quantity++
            updateTotalAmount()
        }

        decreaseButton.setOnClickListener {
            if (quantity > 0) {
                quantity--
                updateTotalAmount()
            }
        }

        // Listener for proceeding to the checkout screen
        proceedToCheckoutButton.setOnClickListener {
            Toast.makeText(this, "Proceeding to Checkout", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Fetches product data from Firebase based on a specified product name.
     * Retrieves product price and displays it in the UI. If product data is not found,
     * an error message is shown.
     */
    /**private fun fetchProductData() {
        val productName = "Headphone" // Product name to search for in Firebase

        // Query Firebase database for a product matching the specified name
        databaseReference.orderByChild("productName").equalTo(productName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Check if product data exists
                    if (dataSnapshot.exists()) {
                        val product = dataSnapshot.children.first()
                            .getValue(ProductDataModel::class.java)

                        // Set the product price and display it, or show an error if null
                        if (product != null) {
                            productPrice = product.productPrice ?: 0
                            productPriceTextView.text = "Price: $$productPrice"
                            updateTotalAmount()
                        } else {
                            Toast.makeText(this@AddToCart, "Product data is null!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@AddToCart, "Product not found!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Show error message if Firebase database query is canceled
                    Toast.makeText(this@AddToCart, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                }
            })
    } */

    /**
     * Calculates and updates the total amount based on the current quantity and
     * product price. Displays the total amount in the UI.
     */
    fun updateTotalAmount() {
        val totalAmount = quantity * productPrice
        findViewById<TextView>(R.id.tvTotalAmount).text = "Total Amount: $$totalAmount"
    }
}
