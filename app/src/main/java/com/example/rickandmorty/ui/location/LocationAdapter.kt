package com.example.rickandmorty.ui.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.data.model.Location
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.databinding.ItemLocationBinding
import com.example.rickandmorty.ui.home.HomeAdapter
import com.example.rickandmorty.util.DateHelper

class LocationAdapter(
    private val locations: List<Location>,
    private val itemWidth: Int
): RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder (val binding: ItemLocationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationAdapter.LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val currentLocation = locations[position]
        holder.binding.apply {
            rootLayout.updateLayoutParams {
                width = itemWidth
                height = itemWidth
            }
             tvLocation.text = currentLocation.name
            val dateHelper = DateHelper()
            tvCreated.text = dateHelper.simpleCreated(currentLocation.created)





        }
    }
    override fun getItemCount(): Int {
        return locations.size
    }


}