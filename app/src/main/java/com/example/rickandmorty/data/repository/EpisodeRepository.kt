package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.model.EpisodeDTO
import com.example.rickandmorty.data.utils.Response

interface EpisodeRepository {

    suspend fun getEpisode(): Response<EpisodeDTO>
}