package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.model.LocationDTO
import com.example.rickandmorty.data.utils.Response

interface LocationRepository {
    suspend fun getLocation(): Response<LocationDTO>
}