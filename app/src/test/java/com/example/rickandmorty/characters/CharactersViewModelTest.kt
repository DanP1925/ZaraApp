package com.example.rickandmorty.characters

import com.example.rickandmorty.MainDispatcherRule
import com.example.rickandmorty.characters.CharactersViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test


class CharactersViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun charactersViewModel_Initialization_FetchCharacters() = runTest {
        val viewModel = CharactersViewModel()

        assertEquals(viewModel.uiState.value.characters.size, 3)
        assertEquals(viewModel.uiState.value.characters[0].name, "Rick Sanchez")
    }

}