package com.example.rickandmorty.data.service

import com.example.rickandmorty.data.model.CharactersDTO
import com.example.rickandmorty.data.utils.Constants
import retrofit2.http.GET

interface RickAndMortyService {

    @GET(Constants.CHARACTER_ENDPOINT)
    suspend fun fetchCharacters(): CharactersDTO

}