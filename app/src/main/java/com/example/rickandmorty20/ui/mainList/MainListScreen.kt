package com.example.rickandmorty20.ui.mainList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty20.common.mock.CharacterMock
import com.example.rickandmorty20.domain.entity.CharacterEntity
import com.example.rickandmorty20.ui.components.ui.BottomBar
import com.example.rickandmorty20.ui.components.ui.BottomBarSelected
import com.example.rickandmorty20.ui.components.ui.CharacterItem
import com.example.rickandmorty20.ui.components.ui.Loader
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainListScreen(
    modifier: Modifier = Modifier,
    navigate: (String) -> Unit = {},
    intoFavorite: () -> Unit = {},
) {

    val mainListViewModel: MainListViewModel = hiltViewModel()
    val charactersFlow: Flow<PagingData<CharacterEntity>> = mainListViewModel.charactersList
    val characters = charactersFlow.collectAsLazyPagingItems()
    val listState: LazyListState = rememberLazyListState()

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (characters.loadState.refresh is LoadState.Loading) {
                Loader()
            } else {
                LazyColumn(
                    state = listState,
                ) {
                    items(count = characters.itemCount, key = { it }) { index ->
                        val item = characters[index]
                        if (item != null) CharacterItem(
                            modifier = Modifier,
                            character = item,
                            navigate = navigate,
                            addIntoFavorite = mainListViewModel::addIntoFavorite,
                            removeFromFavorite = mainListViewModel::removeFromFavorite
                        )
                    }
                    items(count = 1) {
                        if (characters.loadState.append is LoadState.Loading) {
                            Loader()
                        }
                    }
                }
            }
        }
    }
    BottomBar(
        bottomBarSelected = BottomBarSelected.MainList,
        openFavoriteList = { intoFavorite() },
    )
}


@Preview()
@Composable
fun MainListPreview(modifier: Modifier = Modifier) {
    CharacterItem(modifier = modifier, CharacterMock.mockItem)
}

