package com.devdroid.kikinbo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.kikinbo.model.OrderItemDataModel
import com.devdroid.kikinbo.view.PlaceOrderView

class SelectItems : AppCompatActivity() {

    private lateinit var btnSelectHeadphone: RadioButton
    private lateinit var btnSelectMouse: RadioButton
    private lateinit var btnSelectPen: RadioButton
    private lateinit var productQuantityid1: EditText
    private lateinit var productQuantityid2: EditText
    private lateinit var productQuantityid3: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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

            val itemsToSend = mutableListOf<OrderItemDataModel>()

            // Collect selected items based on the quantity entered
            // Headphone
            if (btnSelectHeadphone.isChecked) {
                val headphoneQty = productQuantityid1.text.toString().toIntOrNull() ?: 0
                if (headphoneQty > 0) {
                    itemsToSend.add(OrderItemDataModel("Headphoneid", "Headphone", headphoneQty, 20, 300))
                }
            }

            // Mouse
            if (btnSelectMouse.isChecked) {
                val mouseQty = productQuantityid2.text.toString().toIntOrNull() ?: 0
                if (mouseQty > 0) {
                    itemsToSend.add(OrderItemDataModel("Mouseid", "Mouse", mouseQty, 15,230))
                }
            }

            // Pen
            if (btnSelectPen.isChecked) {
                val penQty = productQuantityid3.text.toString().toIntOrNull() ?: 0
                if (penQty > 0) {
                    itemsToSend.add(OrderItemDataModel("Penid", "Pen", penQty, 15,35))
                }
            }

            // If there are selected items, send them to the next activity
            if (itemsToSend.isNotEmpty()) {
                val intent = Intent(this, PlaceOrderView::class.java)
                intent.putExtra("orderItems", ArrayList(itemsToSend)) // Pass list as ArrayList, which is Serializable
                Toast.makeText(this, "Saved the data", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            } else {
                // Handle case where no items were selected
                Toast.makeText(this, "Please select at least one item", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
