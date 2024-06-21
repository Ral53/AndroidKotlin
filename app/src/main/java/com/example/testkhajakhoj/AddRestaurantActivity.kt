package com.example.testkhajakhoj

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddRestaurantActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var addButton: AppCompatImageButton
    private lateinit var mainButton: Button
    private lateinit var filterButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_restaurant)

        database = FirebaseDatabase.getInstance().reference.child("restaurants")
        addButton = findViewById(R.id.addButton)
        mainButton = findViewById(R.id.mainButton)
        filterButton = findViewById(R.id.filterButton)

        addButton.setOnClickListener {
            val restaurantList = getRestaurantData()
            for (restaurant in restaurantList) {
                addRestaurant(restaurant)
            }
        }

        mainButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        var cuisineFilterType = "American"

        filterButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("cuisineFilter", cuisineFilterType)
            startActivity(intent)
        }
    }

    private fun addRestaurant(restaurant: Restaurant) {
        val restaurantId = database.push().key
        if (restaurantId != null) {
            restaurant.id = restaurantId
            database.child(restaurantId).setValue(restaurant).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Restaurant added successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to add restaurant", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Failed to get restaurant ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getRestaurantData(): ArrayList<Restaurant> {
        return arrayListOf(
            Restaurant(
                id = "",
                name = "Little Italy Pizzeria",
                address = "123 Main St, Anytown, CA 12345",
                cuisineType = "Italian",
                openTime = "11:00 AM",
                closeTime = "10:00 PM",
                contactNumber = "(555) 555-1234",
                bikeParking = true,
                carParking = true,
                wifi = true,
                rating = 4.8,
                location = "123 Main St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "Spicy Szechuan",
                address = "456 Elm St, Anytown, CA 12345",
                cuisineType = "Chinese",
                openTime = "11:30 AM",
                closeTime = "11:00 PM",
                contactNumber = "(555) 555-5678",
                bikeParking = true,
                carParking = false,
                wifi = true,
                rating = 4.2,
                location = "456 Elm St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "The Crepe Escape",
                address = "789 Oak St, Anytown, CA 12345",
                cuisineType = "French",
                openTime = "8:00 AM",
                closeTime = "8:00 PM",
                contactNumber = "(555) 555-9012",
                bikeParking = false,
                carParking = true,
                wifi = true,
                rating = 4.5,
                location = "789 Oak St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "Burger Barn",
                address = "1011 Pine St, Anytown, CA 12345",
                cuisineType = "American",
                openTime = "10:00 AM",
                closeTime = "12:00 AM",
                contactNumber = "(555) 555-2345",
                bikeParking = true,
                carParking = true,
                wifi = false,
                rating = 4.0,
                location = "1011 Pine St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "Tandoori Palace",
                address = "1314 Maple St, Anytown, CA 12345",
                cuisineType = "Indian",
                openTime = "5:00 PM",
                closeTime = "10:00 PM",
                contactNumber = "(555) 555-6789",
                bikeParking = false,
                carParking = true,
                wifi = true,
                rating = 4.7,
                location = "1314 Maple St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "Sunshine Sushi",
                address = "1617 Birch St, Anytown, CA 12345",
                cuisineType = "Japanese",
                openTime = "12:00 PM",
                closeTime = "2:00 PM, 5:00 PM - 9:00 PM",
                contactNumber = "(555) 555-0123",
                bikeParking = true,
                carParking = true,
                rating = 4.9,
                location = "1617 Birch St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "Coffee Corner",
                address = "1920 Spruce St, Anytown, CA 12345",
                cuisineType = "Cafe",
                openTime = "7:00 AM",
                closeTime = "7:00 PM",
                contactNumber = "(555) 555-4567",
                bikeParking = true,
                carParking = true,
                wifi = true,
                rating = 4.3,
                location = "1920 Spruce St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "Taco Fiesta",
                address = "2223 Elm St, Anytown, CA 12345",
                cuisineType = "Mexican",
                openTime = "10:00 AM",
                closeTime = "10:00 PM",
                contactNumber = "(555) 555-3456",
                bikeParking = true,
                carParking = true,
                wifi = true,
                rating = 4.1,
                location = "2223 Elm St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "Falafel Frenzy",
                address = "2526 Oak St, Anytown, CA 12345",
                cuisineType = "Mediterranean",
                openTime = "11:00 AM",
                closeTime = "8:00 PM",
                contactNumber = "(555) 555-7890",
                bikeParking = false,
                carParking = true,
                wifi = false,
                rating = 4.6,
                location = "2526 Oak St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "The Wandering Wok",
                address = "2829 Pine St, Anytown, CA 12345",
                cuisineType = "Asian Fusion",
                openTime = "5:00 PM",
                closeTime = "10:00 PM",
                contactNumber = "(555) 555-1593",
                bikeParking = true,
                carParking = true,
                wifi = true,
                rating = 4.8,
                location = "2829 Pine St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "Simply Salads",
                address = "3132 Maple St, Anytown, CA 12345",
                cuisineType = "Healthy",
                openTime = "10:00 AM",
                closeTime = "8:00 PM",
                contactNumber = "(555) 555-8524",
                bikeParking = true,
                carParking = true,
                wifi = true,
                rating = 4.4,
                location = "3132 Maple St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "The Salty Siren",
                address = "3435 Birch St, Anytown, CA 12345",
                cuisineType = "Seafood",
                openTime = "5:00 PM",
                closeTime = "10:00 PM",
                contactNumber = "(555) 555-6257",
                bikeParking = false,
                carParking = true,
                wifi = true,
                rating = 4.9,
                location = "3435 Birch St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "The Happy Plow",
                address = "3738 Spruce St, Anytown, CA 12345",
                cuisineType = "Vegan",
                openTime = "8:00 AM",
                closeTime = "5:00 PM",
                contactNumber = "(555) 555-9870",
                bikeParking = true,
                carParking = true,
                wifi = true,
                rating = 4.7,
                location = "3738 Spruce St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "The Curryosity Shop",
                address = "4041 Elm St, Anytown, CA 12345",
                cuisineType = "Thai",
                openTime = "11:30 AM",
                closeTime = "9:00 PM",
                contactNumber = "(555) 555-3214",
                bikeParking = false,
                carParking = true,
                wifi = true,
                rating = 4.2,
                location = "4041 Elm St, Anytown, CA 12345"
            ),
            // New restaurants
            Restaurant(
                id = "",
                name = "Nonna Sophia's Trattoria",
                address = "23 Elm St, Anytown, CA 12345",
                cuisineType = "Italian",
                openTime = "10:00 AM",
                closeTime = "11:00 PM",
                contactNumber = "(555) 555-7832",
                bikeParking = true,
                carParking = true,
                wifi = true,
                rating = 4.6,
                location = "23 Elm St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "Lucky Dragon",
                address = "521 Elm St, Anytown, CA 12345",
                cuisineType = "Chinese",
                openTime = "11:00 AM",
                closeTime = "10:00 PM",
                contactNumber = "(555) 555-4918",
                bikeParking = false,
                carParking = true, // Assuming this means a parking lot
                wifi = true,
                rating = 4.3,
                location = "521 Elm St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "All-Star Grill",
                address = "1022 Pine St, Anytown, CA 12345",
                cuisineType = "American",
                openTime = "11:00 AM",
                closeTime = "1:00 AM",
                contactNumber = "(555) 555-5874",
                bikeParking = true,
                carParking = true, // Assuming this means street parking
                wifi = true,
                rating = 4.2,
                location = "1022 Pine St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "Namaste India",
                address = "1323 Maple St, Anytown, CA 12345",
                cuisineType = "Indian",
                openTime = "11:30 AM",
                closeTime = "2:30 PM, 5:00 PM - 10:00 PM",
                contactNumber = "(555) 555-9631",
                bikeParking = false,
                carParking = true, // Assuming this means valet parking
                wifi = true,
                rating = 4.8,
                location = "1323 Maple St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "Sakura Ramen",
                address = "1626 Birch St, Anytown, CA 12345",
                cuisineType = "Japanese",
                openTime = "11:00 AM",
                closeTime = "10:00 PM",
                contactNumber = "(555) 555-2587",
                bikeParking = true,
                carParking = true, // Assuming this means street parking
                wifi = true,
                rating = 4.7,
                location = "1626 Birch St, Anytown, CA 12345"
            ),
            Restaurant(
                id = "",
                name = "El Sombrero",
                address = "2232 Elm St, Anytown, CA 12345",
                cuisineType = "Mexican",
                openTime = "8:00 AM",
                closeTime = "9:00 PM",
                contactNumber = "(555) 555-0974",
                bikeParking = false,
                carParking = true, // Assuming this means a parking lot
                wifi = true,
                rating = 4.5,
                location = "2232 Elm St, Anytown, CA 12345"
            )
        )
    }

}
