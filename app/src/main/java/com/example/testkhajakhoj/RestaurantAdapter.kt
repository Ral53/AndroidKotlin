package com.example.testkhajakhoj

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantAdapter(private var restaurantList: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private var filteredRestaurantList: List<Restaurant> = restaurantList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = filteredRestaurantList[position]
        holder.bind(restaurant)
    }

    override fun getItemCount(): Int {
        return filteredRestaurantList.size
    }

    fun updateRestaurantList(newList: List<Restaurant>) {
        restaurantList = newList
        filterByCuisineType("")
    }

    fun filterByCuisineType(cuisineFilter: String) {
        filteredRestaurantList = if (cuisineFilter.isBlank()) {
            restaurantList // No filter applied, show all restaurants
        } else {
            restaurantList.filter { it.cuisineType.equals(cuisineFilter, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val restaurantName: TextView = itemView.findViewById(R.id.RestaurantName)
        private val restaurantCuisine: TextView = itemView.findViewById(R.id.ResturantCuisine)
        private val restaurantDistance: TextView = itemView.findViewById(R.id.ResturantDistance)
        private val restaurantTime: TextView = itemView.findViewById(R.id.ResturantTime)
        private val restaurantLocation: TextView = itemView.findViewById(R.id.RestaurantLocation)
        private val restaurantRating: TextView = itemView.findViewById(R.id.restaurantRating)

        fun bind(restaurant: Restaurant) {
            restaurantName.text = restaurant.name
            restaurantCuisine.text = restaurant.cuisineType
            restaurantDistance.text = "0.5 km"
            restaurantTime.text = "${restaurant.openTime} - ${restaurant.closeTime}"
            restaurantLocation.text = restaurant.location
            restaurantRating.text = restaurant.rating.toString()
        }
    }
}
