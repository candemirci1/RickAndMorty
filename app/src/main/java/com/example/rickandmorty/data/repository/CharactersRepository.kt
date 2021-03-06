package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.CharactersDTO
import com.example.rickandmorty.data.utils.Response

interface CharactersRepository {

    suspend fun getCharacters(): Response<CharactersDTO>
    suspend fun getCharactersInfo(id:Int): Response<Character>

}