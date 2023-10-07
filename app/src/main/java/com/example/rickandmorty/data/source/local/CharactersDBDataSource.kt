package com.example.rickandmorty.data.source.local

import com.example.rickandmorty.data.SeriesCharacter

class CharactersDBDataSource : CharactersLocalDataSource {

    override suspend fun getCharacters(): List<SeriesCharacter> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCharacters(characters: List<SeriesCharacter>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCharacters() {
        TODO("Not yet implemented")
    }


}