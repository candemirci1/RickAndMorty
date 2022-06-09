package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.data.model.CharactersDTO
import com.example.rickandmorty.data.service.RickAndMortyService
import com.example.rickandmorty.data.utils.Response
import retrofit2.HttpException
import java.io.IOException

class CharacterRepositoryImpl(private val service: RickAndMortyService) : CharactersRepository {

    override suspend fun getCharacters(): Response<CharactersDTO> {

        return try {
            val characters = service.fetchCharacters()
            Response.Success(characters)
        } catch (e: HttpException) {
            Response.Error(e.message.orEmpty(), e.code())
        } catch (e: IOException) {
            Response.Error("check your internet connection")
        }
    }

    override suspend fun getCharactersInfo(id: Int): Response<Character> {

        return try {
            val characters = service.fetchCharacterInfo(id)
            Response.Success(characters)
        } catch (e: HttpException) {
            Response.Error(e.message.orEmpty(), e.code())
        } catch (e: IOException) {
            Response.Error("check your internet connection")
        }

    }
}