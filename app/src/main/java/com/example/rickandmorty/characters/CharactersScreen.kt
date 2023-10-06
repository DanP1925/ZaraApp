package com.example.rickandmorty.characters

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.ui.theme.RickAndMortyTheme


@Composable
fun CharactersScreen(
    characters: List<SeriesCharacter>,
    modifier: Modifier = Modifier
) {
    val characters by remember { mutableStateOf(getFakeCharacters()) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        CharactersContent(characters, modifier)
    }
}

@Composable
fun CharactersContent(
    characters: List<SeriesCharacter>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.padding(24.dp)
    ) {
        items(characters) { character ->
            CharacterItem(character)
        }
    }
}

@Composable
fun CharacterItem(
    character: SeriesCharacter
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .border(1.5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(15.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.aspectRatio(1f)
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .paddingFromBaseline(bottom = 8.dp)
            )
        }
    }
}

private fun getFakeCharacters() = listOf<SeriesCharacter>(
    SeriesCharacter(
        1,
        "Rick Sanchez",
        "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
    ),
    SeriesCharacter(
        2,
        "Morty Smith",
        "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
    ),
    SeriesCharacter(
        3,
        "Summer Smith",
        "https://rickandmortyapi.com/api/character/avatar/3.jpeg"
    )
)

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Composable
fun PreviewCharactersCreen() {
    RickAndMortyTheme {
        CharactersScreen(getFakeCharacters())
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Composable
fun PreviewCharacterItem() {
    RickAndMortyTheme {
        CharacterItem(getFakeCharacters()[0])
    }
}
