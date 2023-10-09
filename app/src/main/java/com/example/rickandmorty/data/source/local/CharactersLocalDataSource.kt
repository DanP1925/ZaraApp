package com.example.rickandmorty.data.source.local

import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.data.SeriesCharacterDetail

interface CharactersLocalDataSource {

    suspend fun getCharacters(): List<SeriesCharacter>

    suspend fun saveCharacters(characters: List<SeriesCharacter>)

    suspend fun deleteCharacters()

    suspend fun getCharacterDetail(id: Int): SeriesCharacterDetail

    suspend fun saveCharacterDetail(characterDetail: SeriesCharacterDetail)

    suspend fun deleteCharacterDetail(id: Int)

}