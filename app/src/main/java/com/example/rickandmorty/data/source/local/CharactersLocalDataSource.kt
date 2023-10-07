package com.example.rickandmorty.data.source.local

import com.example.rickandmorty.data.SeriesCharacter

interface CharactersLocalDataSource {

    suspend fun getCharacters(): List<SeriesCharacter>

    suspend fun saveCharacters(characters: List<SeriesCharacter>)

    suspend fun deleteCharacters()

}