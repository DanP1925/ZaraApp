package com.example.rickandmorty.data

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class DefaultCharacterRepositoryTest {

    private lateinit var characterRepository: DefaultCharacterRepository

    @Before
    fun createRepository(){
        characterRepository = DefaultCharacterRepository()
    }

    @Test
    fun getCharactersTest() = runTest{
        val characters = characterRepository.getCharacters().first()
        assertEquals(characters[0].name,"Rick Sanchez")
    }

}