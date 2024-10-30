package com.devdroid.kikinbo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val databaseRef = database.getReference("Products")

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val hometi1:TextInputEditText=findViewById(R.id.hometi1)
//        hometi1.setOnClickListener {
//            val i=Intent(this,SearchProduct::class.java)
//        }
        hometi1.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val i = Intent(this, SearchProduct::class.java)
                startActivity(i)
                return@setOnTouchListener true // Consumed the event
            }
            false
        }

//        pushProductData()
    }
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