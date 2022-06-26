package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.model.EpisodeDTO
import com.example.rickandmorty.data.service.RickAndMortyService
import com.example.rickandmorty.data.utils.Response
import retrofit2.HttpException
import java.io.IOException

class EpisodeRepositoryImpl(private val service: RickAndMortyService): EpisodeRepository {
    override suspend fun getEpisode(): Response<EpisodeDTO> {
        return try {
            val episodes = service.fetchEpisodes()
            Response.Success(episodes)
        } catch (e: HttpException) {
            Response.Error(e.message.orEmpty(), e.code())
        } catch (e: IOException) {
            Response.Error("check your internet connection")
        }
    }
}