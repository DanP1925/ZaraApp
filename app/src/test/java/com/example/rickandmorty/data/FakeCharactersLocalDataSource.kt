package com.example.rickandmorty.data

import com.example.rickandmorty.data.source.local.CharactersLocalDataSource

class FakeCharactersLocalDataSource : CharactersLocalDataSource {

    private val dbCharacters: MutableList<SeriesCharacter> =
        emptyList<SeriesCharacter>().toMutableList()

    override suspend fun getCharacters(): List<SeriesCharacter> {
        return dbCharacters
    }

    override suspend fun saveCharacters(characters: List<SeriesCharacter>) {
        dbCharacters.addAll(characters)
    }

    override suspend fun deleteCharacters() {
        dbCharacters.clear()
    }

}