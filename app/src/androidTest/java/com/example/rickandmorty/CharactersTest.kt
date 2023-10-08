package com.example.rickandmorty

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.filters.LargeTest
import com.example.rickandmorty.characters.CharactersViewModel
import com.example.rickandmorty.data.CharactersRepository
import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.data.SeriesCharacterDetail
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@LargeTest
@HiltAndroidTest
class CharactersTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

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

    @Inject
    lateinit var repository: CharactersRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun viewCharacterTest() {
        (repository as FakeCharactersRepository).addCharacters(getFakeCharacters())
        (repository as FakeCharactersRepository).addCharacterDetail(getFakeCharacter())
        setContent()

        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()
        composeTestRule.onNodeWithText("Rick Sanchez").performClick()

        val nodes = composeTestRule.onAllNodes(hasText("Rick Sanchez"))
        nodes[0].assertIsDisplayed()
        val nameLabel = composeTestRule.activity.getString(R.string.character_detail_name) + ": "
        composeTestRule.onNodeWithText(nameLabel).assertIsDisplayed()
        val speciesLabel =
            composeTestRule.activity.getString(R.string.character_detail_species) + ": "
        composeTestRule.onNodeWithText(speciesLabel).assertIsDisplayed()
    }

    private fun setContent() {
        composeTestRule.setContent {
            RickAndMortyTheme {
                RickAndMortyNavGraph()
            }
        }
    }

}