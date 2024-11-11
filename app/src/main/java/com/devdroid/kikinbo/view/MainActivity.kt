package com.devdroid.kikinbo.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devdroid.kikinbo.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

/**
 * Main activity that serves as the entry point for the app.
 * Sets up UI elements and interactions, including Firebase database reference.
 * @see [SearchProduct] for the activity triggered by searching products.
 * @see [PlaceOrderView] for the activity triggered by the place order button.
 */
class MainActivity : AppCompatActivity() {

    /** Instance of Firebase Database. */
    val database = FirebaseDatabase.getInstance()

    /** Reference to the "Products" node in the Firebase Database. */
    val databaseRef = database.getReference("Products")

    /**
     * Called when the activity is starting. Sets up edge-to-edge display, initializes
     * UI components, and handles navigation to the search product view.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down, this Bundle contains the data it most recently
     * supplied in [onSaveInstanceState].
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set padding for system bars (like status and navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Set up the Place Order button to navigate to PlaceOrderView on click
        val cancelOrder: Button =findViewById(R.id.btnSelectItems)
        cancelOrder.setOnClickListener {
            val i= Intent(this@MainActivity, CancelOrderView::class.java)
            startActivity(i)
        }
    }
}
