package com.example.rickandmorty.characters

import com.example.rickandmorty.FakeCharactersRepository
import com.example.rickandmorty.MainDispatcherRule
import com.example.rickandmorty.data.SeriesCharacter
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
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
    }

    @Test
    fun charactersViewModel_Initialization_FetchCharacters() = runTest {
        charactersViewModel = CharactersViewModel(fakeCharactersRepository)

        assertTrue(charactersViewModel.uiState.value is CharactersUiState.Success)
        val characters = (charactersViewModel.uiState.value as CharactersUiState.Success).characters
        assertEquals(characters.size, 3)
        assertEquals(characters[0].name, "Rick Sanchez")
    }

    @Test
    fun charactersViewModel_Initialization_Error() = runTest {
        fakeCharactersRepository.makeItFail()
        charactersViewModel = CharactersViewModel(fakeCharactersRepository)

        assertTrue(charactersViewModel.uiState.value is CharactersUiState.Error)
        val error = (charactersViewModel.uiState.value as CharactersUiState.Error).exception
        assertEquals(error.message,"Test exception")

        fakeCharactersRepository.cleanFailFlag()
    }

}