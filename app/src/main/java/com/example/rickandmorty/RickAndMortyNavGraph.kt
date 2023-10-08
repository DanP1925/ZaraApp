package com.example.rickandmorty

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmorty.RickAndMortyDestinations.CHARACTERS_ROUTE
import com.example.rickandmorty.RickAndMortyDestinations.CHARACTER_DETAIL_ROUTE
import com.example.rickandmorty.characterdetail.CharacterDetailScreen
import com.example.rickandmorty.characters.CharactersScreen
import com.example.rickandmorty.characters.CharactersViewModel

@Composable
fun RickAndMortyNavGraph(
    navController: NavHostController = rememberNavController(),
    navActions : RickAndMortyNavigation = remember(navController){
        RickAndMortyNavigation(navController)
    },
    startDestination : String = CHARACTERS_ROUTE
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier,
    ){
        composable(CHARACTERS_ROUTE){
            CharactersScreen(onCharacterSelected = navActions::navigateToCharacterDetail)
        }
        composable(
            CHARACTER_DETAIL_ROUTE,
            arguments = listOf(
                navArgument(RickAndMortyArgs.CHARACTER_ID_ARG) { type = NavType.IntType}
            )
        ){
            CharacterDetailScreen()
        }
    }
}