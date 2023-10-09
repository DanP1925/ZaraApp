package com.example.rickandmorty.data

import com.example.rickandmorty.data.source.local.CharactersLocalDataSource

class FakeCharactersLocalDataSource : CharactersLocalDataSource {

    private val dbCharacters: MutableList<SeriesCharacter> =
        emptyList<SeriesCharacter>().toMutableList()

    private val dbCharacterDetails: MutableList<SeriesCharacterDetail> =
        emptyList<SeriesCharacterDetail>().toMutableList()

    override suspend fun getCharacters(): List<SeriesCharacter> {
        return dbCharacters
    }

    override suspend fun saveCharacters(characters: List<SeriesCharacter>) {
        dbCharacters.addAll(characters)
    }

    override suspend fun deleteCharacters() {
        dbCharacters.clear()
    }

    override suspend fun getCharacterDetail(id: Int): SeriesCharacterDetail {
        val characterDetail = dbCharacterDetails.find {
            it.id == id
        }
        return characterDetail ?: throw Exception("Test exception")
    }

    override suspend fun saveCharacterDetail(characterDetail: SeriesCharacterDetail) {
        dbCharacterDetails.add(characterDetail)
    }

    override suspend fun deleteCharacterDetail(id: Int) {
        dbCharacterDetails.removeIf {
            it.id == id
        }
    }

}