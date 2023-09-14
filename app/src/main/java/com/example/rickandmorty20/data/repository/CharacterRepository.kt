package com.example.rickandmorty20.data.repository

import com.example.rickandmorty20.domain.entity.CharacterEntity
import com.example.rickandmorty20.domain.entity.CharacterResultEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharactersFromNetwork(page: Int): CharacterResultEntity?
    suspend fun insertCharacterDB(characterEntity: CharacterEntity)
    suspend fun deleteCharacterDB(characterEntity: CharacterEntity)
    fun getFavoriteCharacterDB(): Flow<List<CharacterEntity>>
}