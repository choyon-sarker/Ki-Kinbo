package com.devdroid.kikinbo.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devdroid.kikinbo.R

/**
 * Activity that handles the bill payment process in the app.
 *
 * This activity sets up the user interface for the bill payment screen.
 * It applies edge-to-edge display and adjusts the layout to handle system window insets,
 * ensuring the content is properly displayed behind the system bars (e.g., status bar and navigation bar).
 */
class Pay_Bill : AppCompatActivity() {

    /**
     * Initializes the Pay Bill activity by setting up the user interface.
     * It enables edge-to-edge display and adjusts padding to ensure the content is displayed
     * correctly behind the system bars.
     *
     * @param savedInstanceState The saved state of the activity, if any.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Enables edge-to-edge display for the activity
        setContentView(R.layout.activity_pay_bill)  // Sets the layout for the activity

        // Listens for window insets and adjusts padding to accommodate system bars.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
