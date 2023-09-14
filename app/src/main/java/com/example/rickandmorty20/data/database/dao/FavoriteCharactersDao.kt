package com.example.rickandmorty20.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.rickandmorty20.data.models.CharacterData
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCharactersDao {
    @Insert
    suspend fun insert(characterData: CharacterData)

    @Delete
    suspend fun delete(characterData: CharacterData)

    @Query("SELECT * from favorite_characters")
    fun getAll(): Flow<List<CharacterData>>
}