package com.example.rickandmorty20.data.models

import com.squareup.moshi.Json

data class CharacterResultData(
    @Json(name = "results")
    val characterList: List<CharacterData>,
    @Json(name = "info")
    val characterInfo: CharacterInfoData
)
