package com.example.testkhajakhoj

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var database: DatabaseReference
    private var cuisineFilter: String? = null // Initialize with nullable type

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        restaurantAdapter = RestaurantAdapter(emptyList()) // Initialize with empty list
        recyclerView.adapter = restaurantAdapter

        // Retrieve intent extras if any
        cuisineFilter = intent.getStringExtra("cuisineFilter")

        database = FirebaseDatabase.getInstance().reference.child("restaurants")
        fetchRestaurantsFromFirebase()
    }

    private fun fetchRestaurantsFromFirebase() {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val restaurantList = mutableListOf<Restaurant>()
                    for (restaurantSnapshot in snapshot.children) {
                        val restaurant = restaurantSnapshot.getValue(Restaurant::class.java)
                        restaurant?.let {
                            restaurantList.add(it)
                        }
                    }
                    // Filter the restaurant list based on cuisineFilter if it's not null
                    val filteredList = if (cuisineFilter.isNullOrEmpty()) {
                        restaurantList // No filter applied
                    } else {
                        restaurantList.filter { it.cuisineType.equals(cuisineFilter, ignoreCase = true) }
                    }
                    restaurantAdapter.updateRestaurantList(filteredList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}
