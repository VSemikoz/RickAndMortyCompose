package com.example.rickandmorty20.ui.components.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.rickandmorty20.R
import com.example.rickandmorty20.domain.entity.CharacterEntity
import com.example.rickandmorty20.ui.components.Navigation
import com.google.gson.Gson
import java.text.SimpleDateFormat


@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: CharacterEntity,
    navigate: (String) -> Unit = {},
    addIntoFavorite: (CharacterEntity) -> Unit = {},
    removeFromFavorite: (CharacterEntity) -> Unit = {},
) {
    Card(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable {
            val characterJson = Gson().toJson(character)
            navigate("${Navigation.characterDetailScreenRoute}/?${Navigation.characterDetailArgument}=$characterJson")
        }

    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(4.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SubcomposeAsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                loading = { CircularProgressIndicator() },
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(3f),
            ) {

                Text(
                    stringResource(id = R.string.character_card_name, character.name),
                    textAlign = TextAlign.Start,
                )
                Text(
                    stringResource(
                        id = R.string.character_card_status,
                        stringResource(character.status.resourceId)
                    ),
                    textAlign = TextAlign.Start,
                )
                Text(
                    stringResource(
                        id = R.string.character_card_gender,
                        stringResource(character.gender.resourceId)
                    ),
                    textAlign = TextAlign.Start,

                    )
                Text(
                    stringResource(id = R.string.character_card_origin, character.origin.name),
                    textAlign = TextAlign.Start,
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.favorite),
                    contentDescription = null,
                    colorFilter = if (character.isFavorite) ColorFilter.tint(Color.Yellow) else ColorFilter.tint(
                        Color.White
                    ),
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            if (character.isFavorite) {
                                removeFromFavorite(character)
                            } else {
                                addIntoFavorite(character)

                            }
                        },
                )
                Text(
                    SimpleDateFormat.getDateInstance().format(character.created),
                    textAlign = TextAlign.End,
                )
            }

        }
    }
}