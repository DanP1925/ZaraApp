package com.example.rickandmorty

import androidx.navigation.NavHostController
import com.example.rickandmorty.RickAndMortyArgs.CHARACTER_ID_ARG
import com.example.rickandmorty.RickAndMortyScreens.CHARACTERS_SCREEN

private object RickAndMortyScreens {
    const val CHARACTERS_SCREEN = "characters"
}

object RickAndMortyArgs{
    const val CHARACTER_ID_ARG = "character_id"
}
object RickAndMortyDestinations{
    const val CHARACTERS_ROUTE = "$CHARACTERS_SCREEN"
    const val CHARACTER_DETAIL_ROUTE = "$CHARACTERS_SCREEN/{$CHARACTER_ID_ARG}"
}


class RickAndMortyNavigation(
    private val navController : NavHostController
) {

    fun navigateToCharacterDetail(characterId: Int){
        navController.navigate("$CHARACTERS_SCREEN/$characterId")
    }
}