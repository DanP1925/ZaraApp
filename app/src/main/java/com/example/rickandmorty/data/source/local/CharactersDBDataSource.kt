package com.example.rickandmorty.data.source.local

import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.data.SeriesCharacterDetail
import com.example.rickandmorty.data.toCharacterDetailEntity
import com.example.rickandmorty.data.toCharacterEntity

class CharactersDBDataSource(
    private val characterDao: CharacterDao,
    private val characterDetailDao: CharacterDetailDao
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

    override suspend fun getCharacterDetail(id: Int): SeriesCharacterDetail {
        return characterDetailDao.getCharacterDetail(id).toSeriesCharacterDetail()
    }

    override suspend fun saveCharacterDetail(characterDetail: SeriesCharacterDetail) {
        characterDetailDao.insertCharacterDetail(characterDetail.toCharacterDetailEntity())
    }

    override suspend fun deleteCharacterDetail(id: Int) {
        characterDetailDao.deleteCharacterDetail(id)
    }


}