package com.example.rickandmorty.characters

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.filters.MediumTest
import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import org.junit.Rule
import org.junit.Test

@MediumTest
class CharactersScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showAllCharacters() {
        setContent()

        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()

        composeTestRule.onNodeWithText("Summer Smith").assertIsDisplayed()
    }

    private fun setContent(){
        composeTestRule.setContent {
            RickAndMortyTheme {
                CharactersScreen(CharactersViewModel())
            }
        }
    }

}