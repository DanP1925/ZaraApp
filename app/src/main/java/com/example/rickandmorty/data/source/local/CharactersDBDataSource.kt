package com.example.rickandmorty.data.source.local

import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.data.toCharacterEntity

class CharactersDBDataSource(
    private val characterDao: CharacterDao
) : CharactersLocalDataSource {

    override suspend fun getCharacters(): List<SeriesCharacter> {
        return characterDao.getAllCharacters().map {
            it.toSeriesCharacter()
        }
    }

    override suspend fun saveCharacters(characters: List<SeriesCharacter>) {
        characterDao.insertAllCharacters(characters = characters.map {
            it.toCharacterEntity()
        })
    }

    override suspend fun deleteCharacters() {
        characterDao.deleteAllCharacters()
    }


}