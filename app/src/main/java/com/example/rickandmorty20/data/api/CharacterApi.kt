package com.example.rickandmorty20.data.api

import com.example.rickandmorty20.data.models.CharacterResultData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface CharacterApi {
    @GET("character")
    suspend fun loadCharactersPage(@Query("page") a: Int): Response<CharacterResultData>
}

