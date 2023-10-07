package com.example.rickandmorty.data.source.network

import com.example.rickandmorty.data.SeriesCharacter

interface CharactersRemoteDataSource {
    suspend fun getCharacters() : List<SeriesCharacter>

}