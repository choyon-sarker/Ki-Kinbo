//package com.devdroid.kikinbo.view
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.devdroid.kikinbo.PaymentMethod
//import com.devdroid.kikinbo.R
//import com.devdroid.kikinbo.model.OrderItemDataModel
//import com.devdroid.kikinbo.model.ShippingAddressDataModel
//import com.devdroid.kikinbo.viewmodel.PlaceOrderViewModel
//
//class PlaceOrderView : AppCompatActivity() {
//
//    private val placeOrderViewModel: PlaceOrderViewModel by viewModels()
//
//    private lateinit var editShippingCity: EditText
//    private lateinit var userEditPhone: EditText
//    private lateinit var userEditEmail: EditText
//    private lateinit var editShippingDivision: EditText
//    private lateinit var editShippingCountry: EditText
//    //private lateinit var recyclerViewItems: RecyclerView
//    private lateinit var totalAmountText: TextView
//    private lateinit var btnPlaceOrder: Button
//    private lateinit var userIdEditText:EditText
//    //private lateinit var btnSelectItems:Button
//    private lateinit var textProducts:TextView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_place_order_view)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        // Initialize UI elements
//        editShippingCity = findViewById(R.id.editShippingCity)
//        editShippingDivision = findViewById(R.id.editShippingDivision)
//        editShippingCountry = findViewById(R.id.editShippingCountry)
//       // recyclerViewItems = findViewById(R.id.recyclerView_items)
//        totalAmountText = findViewById(R.id.text_total_amount)
//        btnPlaceOrder = findViewById(R.id.btn_place_order)
//        userIdEditText=findViewById(R.id.editUserid)
//        userEditEmail=findViewById(R.id.editEmailid)
//        userEditPhone=findViewById(R.id.editPhoneid)
//        textProducts=findViewById(R.id.text_products)
//
//        // Retrieve the order items and total amount from the Intent
//        val orderItems = intent.getSerializableExtra("orderItems") as? ArrayList<OrderItemDataModel>
//        //val totalAmount = intent.getDoubleExtra("totalAmount", 0.0)
//        val totalAmount=intent.getIntExtra("totalAmount",0)
//
//        // Check if there are order items, then iterate and display them
//        if (orderItems != null && orderItems.isNotEmpty()) {
//            // Calculate total amount by iterating through the order items
//            val totalAmount = orderItems.sumOf { item -> item.price * item.quantity }
//            // Create a string to display all product details
//            val productsString = orderItems.joinToString("\n") { item ->
//                // For each item, display the product ID, name, quantity, and total price for that item
//                "${item.productName} (ID: ${item.productId}) - Qty: ${item.quantity} x ${item.price} = ${item.quantity * item.price}"
//            }
//            // Display the product details string
//            textProducts.text = productsString
//            // Display the total amount
//            totalAmountText.text = "$totalAmount"
//        } else {
//            // If no items were selected, show a message
//            totalAmountText.text = "No items selected"
//            textProducts.text = "Please add items to your order."
//        }
////        // Observe LiveData for Toast messages
////        placeOrderViewModel.toastMessage.observe(this) { message ->
////            // Show Toast when the message in LiveData changes
////            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
////        }
//        // Observing ViewModel LiveData
//        placeOrderViewModel.toastMessage.observe(this) { message ->
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//        }
//        placeOrderViewModel.orderPlaced.observe(this) { isOrderPlaced ->
//            if (isOrderPlaced) {
//                Toast.makeText(this, "Placed order successfully", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(this, PaymentMethod::class.java))
//            }
//        }
//
//        // Place Order button click listener
//        btnPlaceOrder.setOnClickListener {
//
//            // Get the text entered by the user
//            val userId1 = userIdEditText.text.toString()
////            if (userId1.isEmpty()){
////                userIdEditText.error="User ID is required"
////                return@setOnClickListener
////            }
//            val userPhone1=userEditPhone.text.toString()
////            if (userPhone1.isEmpty()){
////                userEditPhone.error="User Phone number is required"
////                return@setOnClickListener
////            }
//            val userEmail1=userEditEmail.text.toString()
////            if (userEmail1.isEmpty()){
////                userEditEmail.error="User Email is required"
////                return@setOnClickListener
////            }
//            // Validate if the user ID is empty
////            if (userId.isEmpty()) {
////                Toast.makeText(this, "User ID cannot be empty", Toast.LENGTH_SHORT).show()
////                return@setOnClickListener
////            }
//            // Validate inputs using ViewModel
//            if (!placeOrderViewModel.validateInputs(userPhone1, userEmail1)) return@setOnClickListener
//
//
//            // Retrieve the values from the UI
//            val shippingAddress = ShippingAddressDataModel(
//                city = editShippingCity.text.toString(),
//                division=editShippingDivision.text.toString(),
//                country = editShippingCountry.text.toString()
//            )
////            if(shippingAddress.city.isEmpty() || shippingAddress.country.isEmpty()){
////                when()
////            }
//
////            PlaceOrderViewModel.toastMessage.observe(this) { message ->
////                // When the LiveData changes, show the Toast with the message
////                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
////            }
//                // Place the order using your view model (you need to implement placeOrder in the ViewModel)
//            //if (orderItems != null) {
//
//                PlaceOrderViewModel().placeOrder(
//                    userId = userId1,
//                    userPhone = userPhone1,
//                    userEmail = userEmail1,
//                    items = orderItems!!,  // The list of order items
//                    shippingAddress = shippingAddress,
//                    totalAmount = totalAmount
//                )
////                if(isOrderPlaced) {
////                    val i= Intent(this, PaymentMethod::class.java)
////                    Toast.makeText(this,"Placed order successfully", Toast.LENGTH_SHORT).show()
////                    startActivity(i)
////                }
////            else{
////                    // Observe LiveData for Toast messages
////                    placeOrderViewModel.toastMessage.observe(this) { message ->
////                        // Show Toast when the message in LiveData changes
////                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
////                    }
////                }
//           // }
////                // Optionally, show a success message or navigate to a confirmation screen
////                Toast.makeText(this, "Order placed successfully", Toast.LENGTH_SHORT).show()
//            }
////            else {
////                // If no items are selected or passed, handle the case
////                Toast.makeText(this, "No items selected for the order", Toast.LENGTH_SHORT).show()
////            }
//    }
//}


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
