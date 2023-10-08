package com.example.rickandmorty.characterdetail

import com.example.rickandmorty.FakeCharactersRepository
import com.example.rickandmorty.MainDispatcherRule
import com.example.rickandmorty.data.SeriesCharacterDetail
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    private lateinit var fakeCharactersRepository: FakeCharactersRepository

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

    @Before
    fun setupViewModel(){
        fakeCharactersRepository = FakeCharactersRepository()
        fakeCharactersRepository.addCharacterDetail(getFakeCharacter())
    }

    @Test
    fun characterDetailViewModel_Initialization_FetchCharacter() = runTest {
        val fakeCharacter = getFakeCharacter()

        characterDetailViewModel = CharacterDetailViewModel(fakeCharactersRepository)

        assertTrue(characterDetailViewModel.uiState.value is CharacterDetailUiState.Success)
        val characterDetail =
            (characterDetailViewModel.uiState.value as CharacterDetailUiState.Success).character
        assertEquals(characterDetail.name, fakeCharacter.name)
        assertEquals(characterDetail.status, fakeCharacter.status)
        assertEquals(characterDetail.species, fakeCharacter.species)
    }

}