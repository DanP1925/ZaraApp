package com.example.rickandmorty.data

import androidx.annotation.VisibleForTesting

class FakeCharactersDataSource : CharactersDataSource{

    private var savedCharacters : MutableList<SeriesCharacter> = emptyList<SeriesCharacter>().toMutableList()
    override suspend fun getCharacters(): List<SeriesCharacter> {
        return savedCharacters
    }

    @VisibleForTesting
    fun addCharacters(characters: List<SeriesCharacter>) {
        savedCharacters.clear()
        savedCharacters.addAll(characters)
    }
}