package com.example.rickandmorty20.data.repository

import android.util.Log
import com.example.rickandmorty20.data.database.DataBase
import com.example.rickandmorty20.data.api.CharacterApi
import com.example.rickandmorty20.data.mapper.intoData
import com.example.rickandmorty20.data.mapper.intoDomain
import com.example.rickandmorty20.domain.entity.CharacterEntity
import com.example.rickandmorty20.domain.entity.CharacterResultEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val characterApi: CharacterApi, private val dataBase: DataBase
) : CharacterRepository {

    override suspend fun getCharactersFromNetwork(page: Int): CharacterResultEntity? {
        return try {
            delay(2000) //TODO
            characterApi.loadCharactersPage(page).body()?.intoDomain()
        } catch (e: Error) {
            Log.e("CharacterRepository", "$e")
            return null
        }
    }

    override suspend fun insertCharacterDB(characterEntity: CharacterEntity) {
        dataBase.favoriteCharactersDao().insert(characterEntity.intoData())
    }

    override suspend fun deleteCharacterDB(characterEntity: CharacterEntity) {
        dataBase.favoriteCharactersDao().delete(characterEntity.intoData())
    }

    override fun getFavoriteCharacterDB(): Flow<List<CharacterEntity>> {
        return dataBase.favoriteCharactersDao().getAll()
            .map { list -> list.map { e -> e.intoDomain().copy(isFavorite = true) } }
    }
}