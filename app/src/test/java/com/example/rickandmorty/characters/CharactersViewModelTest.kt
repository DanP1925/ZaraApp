package com.example.rickandmorty.characters

import com.example.rickandmorty.FakeCharactersRepository
import com.example.rickandmorty.MainDispatcherRule
import com.example.rickandmorty.characters.CharactersViewModel
import com.example.rickandmorty.data.SeriesCharacter
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CharactersViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var charactersViewModel: CharactersViewModel

    private lateinit var fakeCharactersRepository: FakeCharactersRepository

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

    @Before
    fun setupViewModel(){
        fakeCharactersRepository = FakeCharactersRepository()
        fakeCharactersRepository.addCharacters(getFakeCharacters())
        charactersViewModel = CharactersViewModel(fakeCharactersRepository)
    }

    @Test
    fun charactersViewModel_Initialization_FetchCharacters() = runTest {
        assertEquals(charactersViewModel.uiState.value.characters.size, 3)
        assertEquals(charactersViewModel.uiState.value.characters[0].name, "Rick Sanchez")
    }

}