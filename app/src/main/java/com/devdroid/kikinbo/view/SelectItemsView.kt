package com.devdroid.kikinbo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.kikinbo.view.PlaceOrderView
import com.devdroid.kikinbo.viewmodel.SelectItemsViewModel

/**
 * Activity for selecting items and placing an order.
 * Users can select quantities for specific products, which will be processed by the ViewModel.
 * If the order is valid, users are navigated to the next screen to place the order.
 */
class SelectItemsView : AppCompatActivity() {

    // UI elements representing selectable items and quantity input fields
    private lateinit var btnSelectHeadphone: RadioButton
    private lateinit var btnSelectMouse: RadioButton
    private lateinit var btnSelectPen: RadioButton
    private lateinit var productQuantityid1: EditText
    private lateinit var productQuantityid2: EditText
    private lateinit var productQuantityid3: EditText
    private lateinit var btnSave: Button

    // Instance of the ViewModel to manage order processing
    private val selectItemsViewModel: SelectItemsViewModel by viewModels()

    /**
     * Called when the activity is first created.
     * Sets up the view, initializes UI components, and configures the save button action.
     *
     * @param savedInstanceState If the activity is being re-initialized, this bundle contains the most recent data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_items)

        // Initialize UI elements
        btnSelectHeadphone = findViewById(R.id.btnSelectHeadphone)
        btnSelectMouse = findViewById(R.id.btnSelectMouse)
        btnSelectPen = findViewById(R.id.btnSelectPen)
        productQuantityid1 = findViewById(R.id.productQuantityid1)
        productQuantityid2 = findViewById(R.id.productQuantityid2)
        productQuantityid3 = findViewById(R.id.productQuantityid3)
        btnSave = findViewById(R.id.btnSaveid)

        // Set up the save button action
        btnSave.setOnClickListener {
            val selectedHeadphoneQty = productQuantityid1.text.toString().toIntOrNull() ?: 0
            val selectedMouseQty = productQuantityid2.text.toString().toIntOrNull() ?: 0
            val selectedPenQty = productQuantityid3.text.toString().toIntOrNull() ?: 0

            // Process the order using the ViewModel
            val (itemsToSend, message) = selectItemsViewModel.processOrder(selectedHeadphoneQty, selectedMouseQty, selectedPenQty)

            // Display the message to the user
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            // If the order items are valid, navigate to the next screen
            itemsToSend?.let {
                val intent = Intent(this, PlaceOrderView::class.java)
                intent.putExtra("orderItems", ArrayList(it)) // Pass list as ArrayList
                startActivity(intent)
            }
        }
    }
}
