package com.example.rickandmorty.characters

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.SeriesCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

data class CharactersUiState(
    val characters: List<SeriesCharacter>
)

class CharactersViewModel : ViewModel() {

    val uiState: StateFlow<CharactersUiState> = fetchCharacters()

    private fun fetchCharacters(): StateFlow<CharactersUiState> =
        MutableStateFlow(CharactersUiState(emptyList()))


}