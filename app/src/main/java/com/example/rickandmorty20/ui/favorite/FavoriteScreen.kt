package com.example.rickandmorty20.ui.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty20.ui.components.ui.BottomBar
import com.example.rickandmorty20.ui.components.ui.BottomBarSelected
import com.example.rickandmorty20.ui.components.ui.CharacterItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun FavoriteScreen(
    navigate: (String) -> Unit = {},
    intoMainList: () -> Unit = {},
) {

    val favoriteViewModel: FavoriteViewModel = hiltViewModel()
    val characters = favoriteViewModel.getFavorites().collectAsState(initial = listOf()).value

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(content = {
                items(count = characters.size) {
                    CharacterItem(
                        character = characters[it],
                        navigate = navigate,
                        addIntoFavorite = favoriteViewModel::addIntoFavorite,
                        removeFromFavorite = favoriteViewModel::removeFromFavorite,
                    )
                }
            })
        }
        BottomBar(
            bottomBarSelected = BottomBarSelected.FavoriteList,
            openMainList = { intoMainList() },
        )
    }
}


