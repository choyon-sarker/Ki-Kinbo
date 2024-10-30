package com.devdroid.kikinbo

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchProduct : AppCompatActivity() {

    lateinit var recyclerViewSearch:RecyclerView
    lateinit var productList: ArrayList<ProductDataModel>
    lateinit var dbRef:DatabaseReference
    lateinit var ti_productSearch:TextInputEditText
    lateinit var btnSearch:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerViewSearch=findViewById(R.id.recyclerViewSearchid)
        recyclerViewSearch.layoutManager=LinearLayoutManager(this)
        recyclerViewSearch.setHasFixedSize(true)

        ti_productSearch=findViewById(R.id.ti_productSearchid)
        btnSearch=findViewById(R.id.btnSearchid)

        productList= arrayListOf<ProductDataModel>()
        getProductData()
    }

    private fun getProductData() {

        dbRef=FirebaseDatabase.getInstance().getReference( "Products")
        dbRef.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(procuctSnap in snapshot.children){
                        val productData=procuctSnap.getValue(ProductDataModel::class.java)
                        productList.add(productData!!)
                    }
                    val mAdapter=SearchProductAdapter(productList)
                    recyclerViewSearch.adapter=mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}