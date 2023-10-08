package com.example.rickandmorty.characterdetail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.rickandmorty.FakeCharactersRepository
import com.example.rickandmorty.R
import com.example.rickandmorty.TestActivity
import com.example.rickandmorty.data.SeriesCharacterDetail
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestActivity>()

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
    fun setupCharacterDetailScreen(){
        fakeCharactersRepository = FakeCharactersRepository()
        fakeCharactersRepository.addCharacterDetail(getFakeCharacter())
    }

    @Test
    fun showCharacterDetail() {
        val fakeCharacter = getFakeCharacter()
        characterDetailViewModel = CharacterDetailViewModel(fakeCharactersRepository)
        setContent()

        val nodes = composeTestRule.onAllNodes(hasText(fakeCharacter.name))
        nodes[0].assertIsDisplayed()
        nodes[1].assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeCharacter.species).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeCharacter.origin).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.character_detail_type)
        ).assertDoesNotExist()
        composeTestRule.onNodeWithText(fakeCharacter.location).assertIsDisplayed()
    }

    @Test
    fun showCharacterDetail_error(){
        fakeCharactersRepository.makeItFail()
        characterDetailViewModel = CharacterDetailViewModel(fakeCharactersRepository)
        setContent()

        val errorText = composeTestRule.activity.getString(R.string.error_message)
        composeTestRule.onNodeWithText(errorText)

        fakeCharactersRepository.cleanFailFlag()
    }

    private fun setContent() {
        composeTestRule.setContent {
            RickAndMortyTheme {
                CharacterDetailScreen(characterDetailViewModel)
            }
        }
    }

}