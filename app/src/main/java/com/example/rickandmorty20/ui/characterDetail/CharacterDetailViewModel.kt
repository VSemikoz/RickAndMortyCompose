package com.example.rickandmorty20.ui.characterDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty20.data.repository.CharacterRepository
import com.example.rickandmorty20.domain.entity.CharacterEntity
import com.example.rickandmorty20.ui.components.Navigation
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CharacterDetailState(
    val character: CharacterEntity
)

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val state = MutableStateFlow(
        CharacterDetailState(
            Gson().fromJson(
                savedStateHandle.get<String>(Navigation.characterDetailArgument),
                CharacterEntity::class.java
            )
        )
    )


    fun addIntoFavorite(character: CharacterEntity) {
        viewModelScope.launch {
            characterRepository.insertCharacterDB(character)
        }
        state.update {
            it.copy(
                character = character.copy(isFavorite = true)
            )
        }
    }

    fun removeFromFavorite(character: CharacterEntity) {
        viewModelScope.launch {
            characterRepository.deleteCharacterDB(character)
        }
        state.update {
            it.copy(
                character = character.copy(isFavorite = false)
            )
        }
    }
}