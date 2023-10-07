package com.example.rickandmorty.data

import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacters() : Flow<List<SeriesCharacter>>

}