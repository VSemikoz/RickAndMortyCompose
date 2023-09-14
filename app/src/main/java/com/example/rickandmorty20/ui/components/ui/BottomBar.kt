package com.example.rickandmorty20.ui.components.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rickandmorty20.R

enum class BottomBarSelected {
    MainList, FavoriteList, None
}

@Preview
@Composable
fun BottomBar(
    openMainList: () -> Unit = {},
    openFavoriteList: () -> Unit = {},
    bottomBarSelected: BottomBarSelected = BottomBarSelected.None,
) {
    Box(
        contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier
                .weight(weight = 1f)
                .clickable { openMainList() }) {
                Image(
                    painter = painterResource(id = R.drawable.list),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(40.dp),
                    colorFilter = if (bottomBarSelected == BottomBarSelected.MainList) ColorFilter.tint(
                        Color.Black
                    )
                    else ColorFilter.tint(Color.LightGray),
                )
            }
            Box(modifier = Modifier
                .weight(weight = 1f)
                .clickable { openFavoriteList() }) {
                Image(
                    painter = painterResource(id = R.drawable.favorite),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(40.dp),
                    colorFilter = if (bottomBarSelected == BottomBarSelected.FavoriteList) ColorFilter.tint(
                        Color.Black
                    )
                    else ColorFilter.tint(Color.LightGray),
                )
            }
        }
    }
}