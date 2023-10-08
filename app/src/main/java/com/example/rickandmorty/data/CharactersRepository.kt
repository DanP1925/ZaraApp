package com.example.rickandmorty.data

import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters() : Flow<List<SeriesCharacter>>

    fun getCharacterDetail(id: Int) : Flow<SeriesCharacterDetail>

}