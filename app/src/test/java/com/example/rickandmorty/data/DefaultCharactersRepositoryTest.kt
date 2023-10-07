package com.example.rickandmorty.data

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultCharactersRepositoryTest {

    private lateinit var characterRepository: DefaultCharactersRepository

    private lateinit var fakeRemoteDataSource: FakeCharactersRemoteDataSource
    private lateinit var fakeLocalDataSource: FakeCharactersLocalDataSource

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
    @Test
    fun getCharactersTest() = runTest{
        fakeRemoteDataSource = FakeCharactersRemoteDataSource()
        fakeLocalDataSource = FakeCharactersLocalDataSource()
        fakeRemoteDataSource.addCharacters(getFakeCharacters())
        characterRepository = DefaultCharactersRepository(
            fakeRemoteDataSource,
            fakeLocalDataSource,
            StandardTestDispatcher(testScheduler)
        )

        val characters = characterRepository.getCharacters().first()
        advanceUntilIdle()
        assertEquals(characters[0].name,"Rick Sanchez")
        assertEquals(fakeLocalDataSource.getCharacters().size,3)
    }

}