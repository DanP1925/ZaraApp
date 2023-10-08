package com.example.rickandmorty.characters

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.rickandmorty.R
import com.example.rickandmorty.TestActivity
import com.example.rickandmorty.characterdetail.CharacterDetailScreen
import com.example.rickandmorty.data.SeriesCharacterDetail
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import org.junit.Rule
import org.junit.Test

class CharacterDetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestActivity>()

    fun getFakeCharacter() = SeriesCharacterDetail(
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

    @Test
    fun showCharacterDetail(){
        val fakeCharacter = getFakeCharacter()
        setContent()

        composeTestRule.onNodeWithText(fakeCharacter.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeCharacter.species).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeCharacter.origin).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.character_detail_type)
        ).assertDoesNotExist()
        composeTestRule.onNodeWithText(fakeCharacter.location).assertIsDisplayed()
    }

    private fun setContent(){
        composeTestRule.setContent {
            RickAndMortyTheme {
                CharacterDetailScreen()
            }
        }
    }

}