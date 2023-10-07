package com.example.rickandmorty.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.CharactersRepository
import com.example.rickandmorty.data.SeriesCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CharactersUiState {
    data class Success(
        val characters: List<SeriesCharacter> = emptyList()
    ) : CharactersUiState()

    data class Error(
        val exception: Throwable
    ) : CharactersUiState()
}

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<CharactersUiState> =
        MutableStateFlow(CharactersUiState.Success(emptyList()))
    val uiState: StateFlow<CharactersUiState> = _uiState

    init {
        viewModelScope.launch {
            charactersRepository.getCharacters()
                .catch { exception ->
                    _uiState.value = CharactersUiState.Error(exception) }
                .collect { characters ->
                    _uiState.value = CharactersUiState.Success(characters)
                }
        }
    }
}