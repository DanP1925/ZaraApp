package com.example.rickandmorty.characters

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextInput
import androidx.test.filters.MediumTest
import com.example.rickandmorty.FakeCharactersRepository
import com.example.rickandmorty.HiltTestActivity
import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import com.example.rickandmorty.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@MediumTest
class CharactersScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

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
    fun setupCharacterScreen(){
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

    @Test
    fun searchCharacter(){
        charactersViewModel = CharactersViewModel(fakeCharactersRepository)
        setContent()

        composeTestRule.onNodeWithTag("searchTag").performTextInput("rick")

        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()
        assertEquals(fakeCharactersRepository.getNumberOfCharacters(),1)
    }

    private fun setContent(){
        composeTestRule.setContent {
            RickAndMortyTheme {
                CharactersScreen({}, viewModel =  charactersViewModel)
            }
        }
    }

}