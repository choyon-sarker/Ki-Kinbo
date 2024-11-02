package ViewModel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import Model.ProductDataModel
import com.devdroid.kikinbo.R
import com.google.firebase.database.*

/**
 * The `AddToCart` activity provides an interface for users to add products to their cart.
 * It fetches product information from Firebase, displays the price, and enables the user
 * to adjust the quantity and view the total amount before proceeding to checkout.
 */
class AddToCart : AppCompatActivity() {

    // Firebase Database reference used for accessing product data
    private lateinit var database: DatabaseReference

    // UI elements for displaying product name, price, and quantity controls
    private lateinit var tvProductName: TextView
    private lateinit var tvProductPrice: TextView
    private lateinit var btnIncrease: Button
    private lateinit var btnDecrease: Button
    private lateinit var btnProceedToCheckout: Button

    // Tracks the selected quantity of the product
    private var quantity: Int = 0

    // Stores the price of the product retrieved from Firebase
    private var productPrice: Int = 0

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
        database = FirebaseDatabase.getInstance().getReference("Products")

        // Initialize UI elements for product name, price, and control buttons
        tvProductName = findViewById(R.id.tvProductName)
        tvProductPrice = findViewById(R.id.tvProductPrice)
        btnIncrease = findViewById(R.id.btnIncrease)
        btnDecrease = findViewById(R.id.btnDecrease)
        btnProceedToCheckout = findViewById(R.id.btnProceedToCheckout)

        // Fetch and display product data from Firebase
        fetchProductData()

        // Set click listeners for increasing and decreasing product quantity
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

        // Listener for proceeding to the checkout screen
        btnProceedToCheckout.setOnClickListener {
            Toast.makeText(this, "Proceeding to Checkout", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Fetches product data from Firebase based on a specified product name.
     * Retrieves product price and displays it in the UI. If product data is not found,
     * an error message is shown.
     */
    private fun fetchProductData() {
        val productName = "Headphone" // Product name to search for in Firebase

        // Query Firebase database for a product matching the specified name
        database.orderByChild("productName").equalTo(productName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Check if product data exists
                    if (dataSnapshot.exists()) {
                        val product = dataSnapshot.children.first()
                            .getValue(ProductDataModel::class.java)

                        // Set the product price and display it, or show an error if null
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
                    // Show error message if Firebase database query is canceled
                    Toast.makeText(this@AddToCart, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    /**
     * Calculates and updates the total amount based on the current quantity and
     * product price. Displays the total amount in the UI.
     */
    private fun updateTotalAmount() {
        val totalAmount = quantity * productPrice
        findViewById<TextView>(R.id.tvTotalAmount).text = "Total Amount: $$totalAmount"
    }
}
