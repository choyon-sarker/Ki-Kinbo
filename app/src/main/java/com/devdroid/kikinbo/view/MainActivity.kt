package com.devdroid.kikinbo.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devdroid.kikinbo.R
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

        hometi1.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val i = Intent(this, SearchProductView::class.java)
                startActivity(i)
                return@setOnTouchListener true // Consumed the event
            }
            false
        }

        //when we want to push product data on firebase database
        // Create an instance of PushProductData
        //val pushProductData = PushProductData()

        // Call the function directly
        //pushProductData.pushProductData()

    }
}