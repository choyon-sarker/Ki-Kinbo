package com.devdroid.kikinbo.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.devdroid.kikinbo.PaymentMethod
import com.devdroid.kikinbo.R
import com.devdroid.kikinbo.model.OrderItemDataModel
import com.devdroid.kikinbo.model.ShippingAddressDataModel
import com.devdroid.kikinbo.viewmodel.PlaceOrderViewModel

class PlaceOrderView : AppCompatActivity() {

//    private val placeOrderViewModel: PlaceOrderViewModel by viewModels()

    private lateinit var editShippingCity: EditText
    private lateinit var userEditPhone: EditText
    private lateinit var userEditEmail: EditText
    private lateinit var editShippingDivision: EditText
    private lateinit var editShippingCountry: EditText
    private lateinit var totalAmountText: TextView
    private lateinit var btnPlaceOrder: Button
    private lateinit var userIdEditText: EditText
    private lateinit var textProducts: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_place_order_view)

        // Initialize UI elements
        editShippingCity = findViewById(R.id.editShippingCity)
        editShippingDivision = findViewById(R.id.editShippingDivision)
        editShippingCountry = findViewById(R.id.editShippingCountry)
        totalAmountText = findViewById(R.id.text_total_amount)
        btnPlaceOrder = findViewById(R.id.btn_place_order)
        userIdEditText = findViewById(R.id.editUserid)
        userEditEmail = findViewById(R.id.editEmailid)
        userEditPhone = findViewById(R.id.editPhoneid)
        textProducts = findViewById(R.id.text_products)

        // Retrieve the order items and total amount from the Intent
        val orderItems = intent.getSerializableExtra("orderItems") as? ArrayList<OrderItemDataModel>
        val totalAmount = intent.getIntExtra("totalAmount", 0)

        // Check if there are order items, then iterate and display them
        if (orderItems != null && orderItems.isNotEmpty()) {
            val productsString = orderItems.joinToString("\n") { item ->
                "${item.productName} (ID: ${item.productId}) - Qty: ${item.quantity} x ${item.price} = ${item.quantity * item.price}"
            }
            textProducts.text = productsString
            totalAmountText.text = "$totalAmount"
        } else {
            totalAmountText.text = "No items selected"
            textProducts.text = "Please add items to your order."
        }

        // Place Order button click listener
        btnPlaceOrder.setOnClickListener {
            val userId1 = userIdEditText.text.toString()
            val userPhone1 = userEditPhone.text.toString()
            val userEmail1 = userEditEmail.text.toString()

            // Validate inputs
//            if (!PlaceOrderViewModel().validateInputs(userPhone1, userEmail1)) return@setOnClickListener

            val shippingAddress = ShippingAddressDataModel(
                city = editShippingCity.text.toString(),
                division = editShippingDivision.text.toString(),
                country = editShippingCountry.text.toString()
            )

            if (orderItems != null) {
                // Call the ViewModel to place the order
                val isOrderValid: Unit =PlaceOrderViewModel().placeOrder(
                    userId = userId1,
                    userPhone = userPhone1,
                    userEmail = userEmail1,
                    items = orderItems,
                    shippingAddress = shippingAddress,
                    totalAmount = totalAmount
                )
                if (isOrderValid as Boolean){
                    // Navigate to payment screen after placing the order
                    Toast.makeText(this, "Order placed successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, PaymentMethod::class.java))
                } else {
                    Toast.makeText(this, "Order placed Failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No items selected for the order", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
