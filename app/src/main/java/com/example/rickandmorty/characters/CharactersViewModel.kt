package com.example.rickandmorty.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.CharactersRepository
import com.example.rickandmorty.data.SeriesCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CharactersUiState {
    data class Success(
        val searchText: String = "",
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
        MutableStateFlow(CharactersUiState.Success())
    val uiState: StateFlow<CharactersUiState> = _uiState

    private var fetchJob: Job? = null

    init {
        fetchCharacters()
    }

    private fun fetchCharacters(): Job {
        return viewModelScope.launch {
            charactersRepository.getCharacters()
                .catch { exception ->
                    _uiState.value = CharactersUiState.Error(exception)
                }
                .collect { characters ->
                    _uiState.value = CharactersUiState.Success("", characters)
                }
        }
    }

    fun updateSearchText(newText: String) {
        if (_uiState.value is CharactersUiState.Success) {
            _uiState.update {
                (it as CharactersUiState.Success).copy(
                    searchText = newText
                )
            }
        }
        fetchJob?.cancel()
        fetchJob = if (newText == "") {
            fetchCharacters()
        } else {
            filterCharacters(newText)
        }
    }

    private fun filterCharacters(newText: String): Job {
        return viewModelScope.launch {
            charactersRepository.getFilteredCharacters(newText)
                .catch { exception ->
                    _uiState.value = CharactersUiState.Error(exception)
                }.collect { seriesCharacters ->
                    if (_uiState.value is CharactersUiState.Success) {
                        _uiState.update {
                            (it as CharactersUiState.Success).copy(
                                characters = seriesCharacters
                            )
                        }
                    }
                }
        }
    }

}