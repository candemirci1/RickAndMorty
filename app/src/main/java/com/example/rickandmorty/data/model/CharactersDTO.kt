package com.example.rickandmorty.data.model

import com.google.gson.annotations.SerializedName

data class CharactersDTO(
    val info: Info,
    @SerializedName("results")
    val characters: List<Character>

)




data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val created: String
)


