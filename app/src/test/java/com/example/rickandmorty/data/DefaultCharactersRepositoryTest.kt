package com.example.rickandmorty.data

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DefaultCharactersRepositoryTest {

    private lateinit var characterRepository: DefaultCharactersRepository

    @Before
    fun createRepository(){
        characterRepository = DefaultCharactersRepository()
    }

    @Test
    fun getCharactersTest() = runTest{
        val characters = characterRepository.getCharacters().first()
        assertEquals(characters[0].name,"Rick Sanchez")
    }

}