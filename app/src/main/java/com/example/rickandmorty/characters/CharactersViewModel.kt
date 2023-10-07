package com.example.rickandmorty.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.SeriesCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

data class CharactersUiState(
    val characters: List<SeriesCharacter> = emptyList()
)

class CharactersViewModel(
) : ViewModel() {

    private val _uiState: MutableStateFlow<CharactersUiState> =
        MutableStateFlow(CharactersUiState())
    val uiState: StateFlow<CharactersUiState> = _uiState

    init {
        viewModelScope.launch {
            fetchCharacters().collect { characters ->
                _uiState.value = CharactersUiState(characters)
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

    private fun fetchCharacters(): Flow<List<SeriesCharacter>> = flow {
        emit(getFakeCharacters())
    }


}