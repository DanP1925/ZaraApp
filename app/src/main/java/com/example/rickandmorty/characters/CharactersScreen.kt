package com.example.rickandmorty.characters

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

const val SEARCH_TAG = "searchTag"

@Composable
fun CharactersScreen(
    onCharacterSelected: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = hiltViewModel(),
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
                    successUiState.searchText,
                    successUiState.isLoading,
                    onCharacterSelected,
                    viewModel::updateSearchText
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
    searchText: String,
    isLoading: Boolean,
    onCharacterSelected: (id: Int) -> Unit,
    onSearchTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = searchText,
            onValueChange = { onSearchTextChanged(it) },
            label = {
                Text(stringResource(id = R.string.search))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .testTag(SEARCH_TAG)
        )
        if (!isLoading) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .weight(1f)
                    .padding(24.dp)
            ) {
                items(characters) { character ->
                    CharacterItem(character, onCharacterSelected)
                }
            }
        } else {
            Spacer(modifier = Modifier.height(24.dp))
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(24.dp)
                    .height(80.dp)
                    .width(80.dp)
            )
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
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .padding(12.dp)
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
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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
            "Rick Sanchez Sanchez Sanchez Sanchez",
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
        CharactersContent(fakeCharacters, "", false, {}, {})
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
        CharacterItem(fakeCharacter, {})
    }
}
