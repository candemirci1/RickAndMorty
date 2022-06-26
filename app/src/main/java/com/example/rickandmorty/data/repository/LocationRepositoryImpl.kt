package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.model.LocationDTO
import com.example.rickandmorty.data.service.RickAndMortyService
import com.example.rickandmorty.data.utils.Response
import retrofit2.HttpException
import java.io.IOException

class LocationRepositoryImpl(private val service: RickAndMortyService): LocationRepository {
    override suspend fun getLocation(): Response<LocationDTO> {
        return try {
            val locations = service.fetchLocations()
            Response.Success(locations)
        } catch (e: HttpException) {
            Response.Error(e.message.orEmpty(), e.code())
        } catch (e: IOException) {
            Response.Error("check your internet connection")
        }
    }


}