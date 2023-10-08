package com.example.rickandmorty.data.source.network

import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.data.SeriesCharacterDetail

interface CharactersRemoteDataSource {
    suspend fun getCharacters() : List<SeriesCharacter>

    suspend fun getCharacterDetail(id: Int) : SeriesCharacterDetail

}