package com.example.rickandmorty.characters

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.ui.theme.RickAndMortyTheme


@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    onCharacterSelected: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        when (uiState) {
            is CharactersUiState.Success -> {
                val successUiState = (uiState as CharactersUiState.Success)
                CharactersContent(
                    successUiState.characters,
                    onCharacterSelected
                )
            }
            is CharactersUiState.Error -> {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(id = R.string.error_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

@Composable
fun CharactersContent(
    characters: List<SeriesCharacter>,
    onCharacterSelected: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.padding(24.dp)
    ) {
        items(characters) { character ->
            CharacterItem(character, onCharacterSelected)
        }
    }
}

@Composable
fun CharacterItem(
    character: SeriesCharacter,
    onCharacterSelected: (id: Int) -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .border(1.5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(15.dp)),
        onClick = {
            onCharacterSelected(character.id)
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(24.dp)
                .aspectRatio(1f)
        ) {
            SubcomposeAsyncImage(
                model = character.image,
                contentDescription = character.image + " image",
                loading = {
                    CircularProgressIndicator()
                },
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
            )
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp),
                maxLines = 1
            )
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Composable
fun PreviewCharactersContent() {
    val fakeCharacters = listOf<SeriesCharacter>(
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

    RickAndMortyTheme {
        CharactersContent(fakeCharacters, {})
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark Item"
)
@Composable
fun PreviewCharacterItem() {
    val fakeCharacter = SeriesCharacter(
        1,
        "Rick Sanchez",
        "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
    )

    RickAndMortyTheme {
        CharacterItem(fakeCharacter,{})
    }
}
