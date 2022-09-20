package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var wishlists : MutableList<Wishlist> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val wishlistRV = findViewById<RecyclerView>(R.id.wishlistRV)
        val adapter = wishlistAdapter(this,wishlists)

        val submit = findViewById<Button>(R.id.submit)
        val itemText = findViewById<EditText>(R.id.item)
        val priceText = findViewById<EditText>(R.id.price)
        val urlText = findViewById<EditText>(R.id.url)

        submit.setOnClickListener {
            wishlists.add(Wishlist(itemText.text.toString(),priceText.text.toString().toFloat(),urlText.text.toString().toUri()))
            adapter.notifyDataSetChanged()
        }

        wishlistRV.adapter = adapter
        wishlistRV.layoutManager = LinearLayoutManager(this)




    }
}


