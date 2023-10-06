package com.example.rickandmorty

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.filters.LargeTest
import com.example.rickandmorty.characters.CharactersScreen
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import org.junit.Rule
import org.junit.Test

@LargeTest
class CharactersTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun viewCharacterTest() {
        composeTestRule.setContent {
            RickAndMortyTheme {
                CharactersScreen()
            }
        }

        composeTestRule.onNodeWithText("Rick").assertIsDisplayed()
        composeTestRule.onNodeWithText("Rick").performClick()

        composeTestRule.onNodeWithText("Rick").assertIsDisplayed()
        composeTestRule.onNodeWithText("Description").assertIsDisplayed()
    }

}