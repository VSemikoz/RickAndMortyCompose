package com.example.rickandmorty20.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@JsonClass(generateAdapter = true)
@Entity(tableName = "favorite_characters")
data class CharacterData(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: CharacterStatusData,
    val species: String,
    val type: String,
    val gender: CharacterGenderData,
    @Embedded(prefix = "origin")
    val origin: CharacterOriginData,
    @Embedded(prefix = "location")
    val location: CharacterLocationData,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: Date,
)


