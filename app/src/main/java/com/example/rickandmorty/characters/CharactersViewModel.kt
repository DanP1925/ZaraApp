package com.example.rickandmorty.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.CharactersRepository
import com.example.rickandmorty.data.SeriesCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CharactersUiState(
    val characters: List<SeriesCharacter> = emptyList()
)

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<CharactersUiState> =
        MutableStateFlow(CharactersUiState())
    val uiState: StateFlow<CharactersUiState> = _uiState

    init {
        viewModelScope.launch {
            charactersRepository.getCharacters().collect { characters ->
                _uiState.value = CharactersUiState(characters)
            }
        }
    }

}