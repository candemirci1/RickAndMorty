package com.example.rickandmorty.data.model

import java.net.IDN

data class LocationDTO (
    val info: Info,
    val results: List<Location>
        )


data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val url: String,
    val created: String
)