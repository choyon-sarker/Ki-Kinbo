package com.devdroid.kikinbo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val databaseRef = database.getReference("Products")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        val homeProdcut1: CardView = findViewById(R.id.homeProdcut1)
//        val homeProduct2:CardView=findViewById(R.id.homeProdcut2)
//        val homeProduct3:CardView=findViewById(R.id.homeProdcut3)
//        homeProdcut1.setOnClickListener {
//            val intent = Intent(this, ViewProduct::class.java)
//            intent.putExtra("productId", "Headphoneid")
//            startActivity(intent)
//        }
//        homeProduct2.setOnClickListener {
//            val intent = Intent(this, ViewProduct::class.java)
//            intent.putExtra("productId", "Mouseid")
//            startActivity(intent)
//        }
//        homeProduct3.setOnClickListener {
//            val intent = Intent(this, ViewProduct::class.java)
//            intent.putExtra("productId", "Penid")
//            startActivity(intent)
//        }
        val products = listOf(
            Pair(R.id.homeProdcut1, "Headphoneid"),
            Pair(R.id.homeProdcut2, "Mouseid"),
            Pair(R.id.homeProdcut3, "Penid"),
            Pair(R.id.homeProdcut4,"Pencilid"),
            Pair(R.id.homeProdcut5,"Juiceid"),
            Pair(R.id.homeProdcut6,"Candyid")
        )
        for ((cardViewId, productId) in products) {
            findViewById<CardView>(cardViewId).setOnClickListener {
                val intent = Intent(this, ViewProduct::class.java)
                intent.putExtra("productId", productId)
                startActivity(intent)
            }
        }
        //pushProductData()
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