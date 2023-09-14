package com.example.rickandmorty20.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmorty20.ui.characterDetail.CharacterDetailsScreen
import com.example.rickandmorty20.ui.favorite.FavoriteScreen
import com.example.rickandmorty20.ui.mainList.MainListScreen

object Navigation {
    const val mainListScreenRoute = "MainListScreen"
    const val favoriteListScreenRoute = "FavoriteListScreen"
    const val characterDetailScreenRoute = "CharacterDetailsScreen"
    const val characterDetailArgument = "character"
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyNavigation20() {
    val navController: NavHostController = rememberNavController()
    Scaffold { innerPadding ->
        AppNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Navigation.mainListScreenRoute,
        modifier = modifier,
    ) {
        composable(route = Navigation.mainListScreenRoute) {
            MainListScreen(
                navigate = navController::navigate,
                intoFavorite = {
                    navController.navigate(Navigation.favoriteListScreenRoute) {
                        popUpTo(Navigation.favoriteListScreenRoute)
                        launchSingleTop = true
                    }
                },
            )
        }

        composable(route = Navigation.favoriteListScreenRoute) {
            FavoriteScreen(
                navigate = navController::navigate,
                intoMainList = {
                    navController.navigate(Navigation.mainListScreenRoute) {
                        popUpTo(Navigation.mainListScreenRoute)
                        launchSingleTop = true
                    }
                },
            )
        }

        composable(
            route = "${Navigation.characterDetailScreenRoute}/?${Navigation.characterDetailArgument}={${Navigation.characterDetailArgument}}",
            arguments = listOf(navArgument(Navigation.characterDetailArgument) {
                type = NavType.StringType
            })
        ) {
            CharacterDetailsScreen()
        }
    }


}


private fun NavHostController.isScreenInQueue(screenName: String): Boolean {
    return backQueue.any {
        it.destination.route == screenName
    } && backQueue[backQueue.size - 1].destination.route != screenName
}