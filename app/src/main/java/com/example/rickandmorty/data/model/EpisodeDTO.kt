package com.example.rickandmorty.data.model

data class EpisodeDTO(
    val info: Info,
    val results: List<Episode>
)


data class Episode (
    val id: Int,
    val name: String,
    val url: String,
    val created: String
)