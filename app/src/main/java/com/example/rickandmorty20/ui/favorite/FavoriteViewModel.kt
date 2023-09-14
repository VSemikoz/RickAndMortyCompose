package com.example.rickandmorty20.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty20.data.repository.CharacterRepository
import com.example.rickandmorty20.domain.entity.CharacterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavoriteState(
    val charactersList: List<CharacterEntity>
)


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
) : ViewModel() {

    fun getFavorites(): Flow<List<CharacterEntity>> {
        return characterRepository.getFavoriteCharacterDB()
    }


    fun addIntoFavorite(character: CharacterEntity) {
        viewModelScope.launch {
            characterRepository.insertCharacterDB(character)
        }
    }

    fun removeFromFavorite(character: CharacterEntity) {
        viewModelScope.launch {
            characterRepository.deleteCharacterDB(character)
        }
    }

}