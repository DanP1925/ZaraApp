package com.example.rickandmorty

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.characters.CharactersScreen
import com.example.rickandmorty.characters.CharactersViewModel

@Composable
fun RickAndMortyNavGraph(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = "characters",
        modifier = Modifier
    ){
        composable("characters"){
            CharactersScreen(onCharacterSelected = {

            })
        }
    }
}