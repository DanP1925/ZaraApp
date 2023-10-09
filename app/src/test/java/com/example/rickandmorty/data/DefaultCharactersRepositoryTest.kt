package com.example.rickandmorty.data

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
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
    fun setupDataSources(){
        fakeRemoteDataSource = FakeCharactersRemoteDataSource()
        fakeLocalDataSource = FakeCharactersLocalDataSource()
    }

    @Test
    fun getCharactersTest() = runTest{
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

    @Test
    fun getCharactersTest_NetworkError() = runTest {
        fakeRemoteDataSource.makeItFail()
        fakeLocalDataSource.saveCharacters(getFakeCharacters())
        characterRepository = DefaultCharactersRepository(
            fakeRemoteDataSource,
            fakeLocalDataSource,
            StandardTestDispatcher(testScheduler)
        )

        val characters = characterRepository.getCharacters().first()
        advanceUntilIdle()

        assertEquals(characters[0].name, "Rick Sanchez")
    }

    @Test
    fun getCharacterTest() = runTest{
        val fakeCharacter = getFakeCharacter()
        fakeRemoteDataSource.addCharacterDetail(fakeCharacter)
        characterRepository = DefaultCharactersRepository(
            fakeRemoteDataSource,
            fakeLocalDataSource,
            StandardTestDispatcher(testScheduler)
        )

        val characterDetail = characterRepository.getCharacterDetail(fakeCharacter.id).first()
        advanceUntilIdle()
        assertEquals(characterDetail.name , "Rick Sanchez")
        assertEquals(characterDetail.status, "Alive")
        assertEquals(characterDetail.species, "Human")
        assertEquals(fakeLocalDataSource.getCharacterDetail(fakeCharacter.id).name, "Rick Sanchez")
    }

    @Test
    fun getCharacterTest_NetworkError() = runTest {
        val fakeCharacter = getFakeCharacter()
        fakeRemoteDataSource.makeItFail()
        fakeLocalDataSource.saveCharacterDetail(getFakeCharacter())
        characterRepository = DefaultCharactersRepository(
            fakeRemoteDataSource,
            fakeLocalDataSource,
            StandardTestDispatcher(testScheduler)
        )

        val characterDetail = characterRepository.getCharacterDetail(fakeCharacter.id).first()
        advanceUntilIdle()
        assertEquals(characterDetail.name, fakeCharacter.name)
        assertEquals(characterDetail.status, fakeCharacter.status)
        assertEquals(characterDetail.species, fakeCharacter.species)
    }

}