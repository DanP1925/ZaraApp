package com.example.rickandmorty

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.filters.LargeTest
import com.example.rickandmorty.characters.CharactersScreen
import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import org.junit.Rule
import org.junit.Test

@LargeTest
class CharactersTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun viewCharacterTest() {
        setContent()

        composeTestRule.onNodeWithText("Rick").assertIsDisplayed()
        composeTestRule.onNodeWithText("Rick").performClick()

        composeTestRule.onNodeWithText("Rick").assertIsDisplayed()
        composeTestRule.onNodeWithText("Description").assertIsDisplayed()
    }


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

    private fun setContent(){
        composeTestRule.setContent {
            RickAndMortyTheme {
                CharactersScreen(getFakeCharacters())
            }
        }
    }

}