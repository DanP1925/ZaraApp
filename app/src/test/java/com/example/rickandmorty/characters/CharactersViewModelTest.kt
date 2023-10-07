package com.example.rickandmorty.characters

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CharactersViewModelTest {

    private val viewModel = CharactersViewModel()

    @Test
    fun charactersViewModel_Initialization_FetchCharacters() {
        assertEquals(viewModel.uiState.value.characters.size,3)
        assertEquals(viewModel.uiState.value.characters[0].name, "Rick Sanchez")
    }

}