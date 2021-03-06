package com.example.rickandmorty.data.service

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.CharactersDTO
import com.example.rickandmorty.data.model.EpisodeDTO
import com.example.rickandmorty.data.model.LocationDTO
import com.example.rickandmorty.data.utils.Constants
import com.example.rickandmorty.data.utils.Constants.CHARACTER_INFO_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {

    @GET(Constants.CHARACTER_ENDPOINT)
    suspend fun fetchCharacters(): CharactersDTO

    @GET(CHARACTER_INFO_ENDPOINT)
    suspend fun fetchCharacterInfo(
        @Path("id") id: Int
    ): Character

    @GET(Constants.LOCATION_ENDPOINT)
    suspend fun  fetchLocations(): LocationDTO

    @GET(Constants.EPİSODE_ENDPOINT)
    suspend fun fetchEpisodes(): EpisodeDTO

}