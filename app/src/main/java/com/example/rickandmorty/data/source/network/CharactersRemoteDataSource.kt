package com.example.rickandmorty.data.source.network

import com.example.rickandmorty.data.CharactersDataSource
import com.example.rickandmorty.data.SeriesCharacter
import javax.inject.Inject

class CharactersRemoteDataSource @Inject constructor(
    private val charactersService: CharactersService
) : CharactersDataSource {

    override suspend fun getCharacters(): List<SeriesCharacter> {
        return charactersService.getCharacters().results.map {
            it.toSeriesCharacter()
        }
    }

}