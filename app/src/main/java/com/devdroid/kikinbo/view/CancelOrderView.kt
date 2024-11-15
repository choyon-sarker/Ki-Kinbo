package com.devdroid.kikinbo.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devdroid.kikinbo.R
import com.devdroid.kikinbo.viewmodel.CancelOrderViewModel
import com.google.android.material.textfield.TextInputEditText

class CancelOrderView : AppCompatActivity() {
    /** The input field for the order ID. */
    private lateinit var orderId: TextInputEditText

    /** The button used to submit the order ID for cancellation. */
    private lateinit var btnSubmitId: Button

    /**
     * Called when the activity is created. Initializes the UI components and sets up listeners.
     *
     * @param savedInstanceState the state of the activity before it was destroyed (if applicable)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cancel_order_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Initializes the views for the order ID input and submit button
        orderId = findViewById(R.id.tiOrderid)
        btnSubmitId = findViewById(R.id.btnSubminid)

        // Sets an onClickListener for the submit button
        btnSubmitId.setOnClickListener {
            val orderId1 = orderId.text.toString().trim() // Retrieves the order ID entered by the user

            // Attempts to cancel the order using the provided order ID
            val isOrderCanceled = CancelOrderViewModel().orderCanceled(orderId1)
            if (isOrderCanceled) {
                // Displays a success message if the order was canceled successfully
                Toast.makeText(this, "Order canceled successfully", Toast.LENGTH_SHORT).show()

                // Navigates to the MainActivity upon successful cancellation
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            } else {
                // Displays an error message if the order cancellation failed.
                Toast.makeText(this, "Order cancellation failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
