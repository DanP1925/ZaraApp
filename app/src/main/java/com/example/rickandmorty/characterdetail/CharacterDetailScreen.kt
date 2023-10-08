package com.example.rickandmorty.characterdetail

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.data.SeriesCharacterDetail
import com.example.rickandmorty.ui.theme.RickAndMortyTheme


@Composable
fun CharacterDetailScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        CharacterDetailContent(
            characterDetail = getFakeCharacter(),
            modifier = modifier
        )
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
            verticalArrangement = Arrangement.Center
        ) {
            SubcomposeAsyncImage(
                model = characterDetail.image,
                contentDescription = characterDetail.image + " image",
                loading = {
                    CircularProgressIndicator()
                },
            )
            CharacterDetailInfo(
                label = R.string.character_detail_name,
                value = characterDetail.name
            )
            CharacterDetailInfo(
                label = R.string.character_detail_status,
                value = characterDetail.status
            )
            CharacterDetailInfo(
                label = R.string.character_detail_species,
                value = characterDetail.species
            )
            CharacterDetailInfo(
                label = R.string.character_detail_type,
                value = characterDetail.type
            )
            CharacterDetailInfo(
                label = R.string.character_detail_gender,
                value = characterDetail.gender
            )
            CharacterDetailInfo(
                label = R.string.character_detail_origin,
                value = characterDetail.origin
            )
            CharacterDetailInfo(
                label = R.string.character_detail_location,
                value = characterDetail.location
            )
        }
    }
}

@Composable
fun CharacterDetailInfo(
    @StringRes label: Int,
    value: String,
    modifier: Modifier = Modifier
) {
    if (value.isNotEmpty()) {
        Row {
            Text(formatLabel(stringResource(id = label)))
            Text(value)
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
