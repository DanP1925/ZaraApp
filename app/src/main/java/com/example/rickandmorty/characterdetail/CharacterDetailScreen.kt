package com.example.rickandmorty.characterdetail

import android.content.res.Configuration
import android.widget.Toast
import androidx.annotation.StringRes
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.data.SeriesCharacterDetail
import com.example.rickandmorty.ui.theme.RickAndMortyTheme


@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        when (uiState) {
            is CharacterDetailUiState.Success -> {
                val successUiState = (uiState as CharacterDetailUiState.Success)
                CharacterDetailContent(
                    characterDetail = successUiState.character,
                    modifier = modifier
                )
            }

            is CharacterDetailUiState.Error -> {
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
fun CharacterDetailContent(
    characterDetail: SeriesCharacterDetail,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = characterDetail.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            SubcomposeAsyncImage(
                model = characterDetail.image,
                contentDescription = characterDetail.image + " image",
                loading = {
                    CircularProgressIndicator()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp)
                    .aspectRatio(1f)
                    .border(2.dp, MaterialTheme.colorScheme.primary),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CharacterDetailInfo(characterDetail = characterDetail)
        }
    }
}


@Composable
fun CharacterDetailInfo(
    characterDetail: SeriesCharacterDetail,
    modifier: Modifier = Modifier
) {
    Column {
        CharacterDetailInfoItem(
            label = R.string.character_detail_name,
            value = characterDetail.name
        )
        CharacterDetailInfoItem(
            label = R.string.character_detail_status,
            value = characterDetail.status
        )
        CharacterDetailInfoItem(
            label = R.string.character_detail_species,
            value = characterDetail.species
        )
        CharacterDetailInfoItem(
            label = R.string.character_detail_type,
            value = characterDetail.type
        )
        CharacterDetailInfoItem(
            label = R.string.character_detail_gender,
            value = characterDetail.gender
        )
        CharacterDetailInfoItem(
            label = R.string.character_detail_origin,
            value = characterDetail.origin
        )
        CharacterDetailInfoItem(
            label = R.string.character_detail_location,
            value = characterDetail.location
        )
    }
}

@Composable
fun CharacterDetailInfoItem(
    @StringRes label: Int,
    value: String,
    modifier: Modifier = Modifier
) {
    if (value.isNotEmpty()) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = formatLabel(stringResource(id = label)),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun formatLabel(label: String) = "$label: "

private fun getFakeCharacter() = SeriesCharacterDetail(
    1,
    "Rick Sanchez",
    "Alive",
    "Human",
    "",
    "Male",
    "Earth (C-137)",
    "Citadel of Ricks",
    "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
)

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Composable
fun PreviewCharacterDetailContent() {
    val fakeCharacter = getFakeCharacter()

    RickAndMortyTheme {
        CharacterDetailContent(characterDetail = fakeCharacter)
    }
}
