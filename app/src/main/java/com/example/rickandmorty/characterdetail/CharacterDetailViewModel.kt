package com.example.rickandmorty.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.CharactersRepository
import com.example.rickandmorty.data.SeriesCharacterDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class CharacterDetailUiState {
    data class Success(
        val character: SeriesCharacterDetail = SeriesCharacterDetail()
    ) : CharacterDetailUiState()

    data class Error(
        val exception: Throwable
    ) : CharacterDetailUiState()
}

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {


    private val _uiState: MutableStateFlow<CharacterDetailUiState> = MutableStateFlow(
        CharacterDetailUiState.Success()
    )
    val uiState: StateFlow<CharacterDetailUiState> = _uiState

    init {
        viewModelScope.launch {
            charactersRepository.getCharacterDetail()
                .catch { exception ->
                    _uiState.value = CharacterDetailUiState.Error(exception)
                }
                .collect { characterDetail ->
                    _uiState.value = CharacterDetailUiState.Success(characterDetail)
                }
        }
    }

}