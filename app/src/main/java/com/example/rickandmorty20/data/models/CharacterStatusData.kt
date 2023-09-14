package com.example.rickandmorty20.data.models

import com.squareup.moshi.Json


enum class CharacterStatusData(val localization: String) {
    @Json(name = "Alive")
    Alive("Alive"),

    @Json(name = "Dead")
    Dead("Dead"),

    @Json(name = "unknown")
    Unknown("Unknown"),
}
