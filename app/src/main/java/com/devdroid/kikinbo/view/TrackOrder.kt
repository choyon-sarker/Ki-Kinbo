package com.devdroid.kikinbo.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devdroid.kikinbo.R

/**
 * Activity for tracking the status of an order.
 * This activity displays order tracking details and adapts its UI to system insets for edge-to-edge layout.
 */
class TrackOrder : AppCompatActivity() {

    /**
     * Called when the activity is starting. Sets up the UI for tracking orders
     * and enables edge-to-edge layout adjustments.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the saved data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enables edge-to-edge layout for immersive experience.
        setContentView(R.layout.activity_track_order)

        // Adjusts padding to fit system bars (e.g., status bar, navigation bar) for edge-to-edge display.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
