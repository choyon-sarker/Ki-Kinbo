package com.devdroid.kikinbo.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devdroid.kikinbo.R
import com.google.firebase.database.FirebaseDatabase

/**
 * The main activity for the Ki-Kinbo!! online shop application.
 *
 * This activity initializes the home screen, sets up edge-to-edge display,
 * manages padding based on window insets, and handles click events on product cards.
 * It connects to Firebase Realtime Database to retrieve or store product data.
 */
class MainActivity : AppCompatActivity() {

    /** Instance of the Firebase Realtime Database. */
    val database = FirebaseDatabase.getInstance()

    /** Reference to the "Products" node in the Firebase database. */
    val databaseRef = database.getReference("Products")

    /**
     * Called when the activity is created.
     *
     * This method initializes the UI components, applies window insets for edge-to-edge display,
     * and sets click listeners on product card views to navigate to the product detail screen.
     *
     * @param savedInstanceState If non-null, this bundle contains the saved state of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Enables edge-to-edge layout.
        setContentView(R.layout.activity_main)

        // Apply window insets to adjust padding for system bars.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // List of product cards and their associated product IDs.
        val products = listOf(
            Pair(R.id.homeProdcut1, "Headphoneid"),
            Pair(R.id.homeProdcut2, "Mouseid"),
            Pair(R.id.homeProdcut3, "Penid"),
            Pair(R.id.homeProdcut4, "Pencilid"),
            Pair(R.id.homeProdcut5, "Juiceid"),
            Pair(R.id.homeProdcut6, "Candyid")
        )

        // Set click listeners for product cards to open the product detail view.
        for ((cardViewId, productId) in products) {
            findViewById<CardView>(cardViewId).setOnClickListener {
                val intent = Intent(this, ViewProduct::class.java).apply {
                    putExtra("productId", productId)
                }
                startActivity(intent)
            }
        }

        // Uncomment the following line to push product data to Firebase.
        // pushProductData()
    }

    /**
     * Pushes sample product data to the Firebase database.
     *
     * This method adds product data to the database and logs success or failure messages.
     * Uncomment and modify this function as needed to upload product information.
     */
//    private fun pushProductData() {
//        databaseRef.push().setValue(product1)
//        databaseRef.push().setValue(product2)
//        databaseRef.push().setValue(product3)
//        databaseRef.push().setValue(product4)
//        databaseRef.push().setValue(product5)
//        databaseRef.push().setValue(product6)
//            .addOnSuccessListener {
//                println("Product data added successfully!")
//            }
//            .addOnFailureListener { e ->
//                println("Failed to add product data: ${e.message}")
//            }
//    }
}
