package com.example.rickandmorty20.domain.entity

import java.util.Date

data class CharacterEntity(
    val id: Int,
    val name: String,
    val status: CharacterStatusEntity,
    val species: String,
    val type: String,
    val gender: CharacterGenderEntity,
    val origin: CharacterOriginEntity,
    val location: CharacterLocationEntity,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: Date,
    val isFavorite: Boolean = false
)