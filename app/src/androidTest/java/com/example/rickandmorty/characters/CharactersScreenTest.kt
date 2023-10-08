package com.example.rickandmorty.characters

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.filters.MediumTest
import com.example.rickandmorty.FakeCharactersRepository
import com.example.rickandmorty.TestActivity
import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import com.example.rickandmorty.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@MediumTest
class CharactersScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestActivity>()

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
    fun showAllCharacters() {
        charactersViewModel = CharactersViewModel(fakeCharactersRepository)
        setContent()

        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()
        composeTestRule.onNodeWithText("Summer Smith").assertIsDisplayed()
    }

    @Test
    fun showAllCharacters_error(){
        fakeCharactersRepository.makeItFail()
        charactersViewModel = CharactersViewModel(fakeCharactersRepository)
        setContent()

        val errorText = composeTestRule.activity.getString(R.string.error_message)
        composeTestRule.onNodeWithText(errorText)

        fakeCharactersRepository.cleanFailFlag()
    }

    private fun setContent(){
        composeTestRule.setContent {
            RickAndMortyTheme {
                CharactersScreen(charactersViewModel, {})
            }
        }
    }

}