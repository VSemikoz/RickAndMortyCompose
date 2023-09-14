package com.example.rickandmorty20.ui.mainList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty20.data.repository.CharacterRepository
import com.example.rickandmorty20.data.repository.pagers.CharacterPager
import com.example.rickandmorty20.domain.entity.CharacterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class MainState(
    val favorite: List<CharacterEntity>
)

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val characterPager: CharacterPager,
    private val characterRepository: CharacterRepository,
) : ViewModel() {
    init {
        loadFavorite()
    }


    private val state: MutableStateFlow<MainState> = MutableStateFlow(MainState(listOf()))


    val charactersList: Flow<PagingData<CharacterEntity>> =
        characterPager.pager.flow.cachedIn(viewModelScope)
            //Update favorite state
            .combine(state.asStateFlow()) { pager, state ->
                pager.map { pagerCharacter ->
                    val favoriteIds = state.favorite.map { it.id }
                    if (favoriteIds.contains(pagerCharacter.id)) {
                        return@map pagerCharacter.copy(isFavorite = true)
                    }
                    return@map pagerCharacter
                }
            }

    private fun loadFavorite() {
        viewModelScope.launch {
            characterRepository.getFavoriteCharacterDB().collect { list ->
                state.update {
                    it.copy(favorite = list)
                }
            }
        }
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