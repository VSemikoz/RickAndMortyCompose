package com.example.rickandmorty20.ui.characterDetail

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.rickandmorty20.R
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen() {
    val viewModel: CharacterDetailViewModel = hiltViewModel()
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val character = viewModel.state.collectAsState().value.character

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(id = R.string.details_app_bar, character.name)) },
            navigationIcon = {
                IconButton(onClick = {
                    onBackPressedDispatcher?.onBackPressed()
                }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Gray)
        )

    }) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            SubcomposeAsyncImage(
                model = character.image,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
                loading = { CircularProgressIndicator() },
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier.weight(2f),
                    verticalArrangement = Arrangement.spacedBy(2.dp),

                    ) {
                    Text(stringResource(R.string.character_details_name, character.name))
                    Text(stringResource(R.string.character_details_origin, character.origin.name))
                    Text(
                        stringResource(
                            id = R.string.character_details_location, character.location.name
                        )
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        stringResource(
                            R.string.character_details_gender,
                            stringResource(character.gender.resourceId)
                        )
                    )
                    Text(
                        stringResource(
                            R.string.character_details_status,
                            stringResource(character.status.resourceId)
                        )
                    )
                    Text(stringResource(R.string.character_details_type, character.type))
                    Text(stringResource(R.string.character_details_species, character.species))
                    Text(
                        stringResource(
                            R.string.character_details_created,
                            SimpleDateFormat.getDateInstance().format(character.created)
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (character.isFavorite) viewModel.removeFromFavorite(character)
                    else viewModel.addIntoFavorite(character)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
                    .fillMaxWidth(),
            ) {
                if (character.isFavorite) Text(stringResource(id = R.string.character_details_remove_favorite_btn))
                else Text(stringResource(id = R.string.character_details_add_favorite_btn))
            }
        }

    }
}
