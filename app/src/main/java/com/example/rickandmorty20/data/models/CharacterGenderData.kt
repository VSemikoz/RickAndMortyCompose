package com.example.rickandmorty20.data.models

import com.squareup.moshi.Json

enum class CharacterGenderData(val localization: String) {
    @Json(name = "Female")
    Female("Female"),

    @Json(name = "Male")
    Male("Male"),

    @Json(name = "Genderless")
    Genderless("Genderless"),

    @Json(name = "unknown")
    Unknown("Unknown"),
}
