package com.example.rickandmorty.characters

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rickandmorty.ui.theme.RickAndMortyTheme


@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){

    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES ,
    name = "Dark"
)
@Composable
fun PreviewCharactersCreen(){
    RickAndMortyTheme {
        CharactersScreen()
    }
}