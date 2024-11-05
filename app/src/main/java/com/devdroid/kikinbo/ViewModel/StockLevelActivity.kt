package com.devdroid.kikinbo.ViewModel
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.kikinbo.R

class StockLevelActivity : AppCompatActivity() {

    private lateinit var productNameEditText: EditText
    private lateinit var stockLevelEditText: EditText
    private lateinit var thresholdEditText: EditText
    private lateinit var alertMessageTextView: TextView
    private lateinit var setStockButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_stock_level) // Replace with your XML file name

        // Initialize views
        productNameEditText = findViewById(R.id.product_Name)
        stockLevelEditText = findViewById(R.id.stock_Level)
        thresholdEditText = findViewById(R.id.restock_Threshold)
        alertMessageTextView = findViewById(R.id.alert_Message)
        setStockButton = findViewById(R.id.setStock_Button)

        // Set button click listener
        setStockButton.setOnClickListener {
            checkStockLevels()
        }
    }

    private fun checkStockLevels() {
        // Get input values
        val stockLevel = stockLevelEditText.text.toString().toIntOrNull()
        val threshold = thresholdEditText.text.toString().toIntOrNull()

        // Validate input
        if (stockLevel != null && threshold != null) {
            // Check if stock level is below or equal to threshold
            if (stockLevel <= threshold) {
                alertMessageTextView.text = "Alert: Stock is below the threshold! Time to restock."
                alertMessageTextView.visibility = View.VISIBLE
            } else {
                alertMessageTextView.visibility = View.GONE
            }
        } else {
            // Handle invalid input
            alertMessageTextView.text = "Please enter valid numbers for stock level and threshold."
            alertMessageTextView.visibility = View.VISIBLE
        }
    }
}
