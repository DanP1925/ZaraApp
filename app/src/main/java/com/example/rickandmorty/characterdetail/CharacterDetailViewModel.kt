package com.example.rickandmorty.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.SeriesCharacterDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
) : ViewModel() {

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


    private val _uiState: MutableStateFlow<CharacterDetailUiState> = MutableStateFlow(
        CharacterDetailUiState.Success()
    )
    val uiState: StateFlow<CharacterDetailUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = CharacterDetailUiState.Success(getFakeCharacter())
        }
    }

}