package com.example.rickandmorty.data.source.network

import com.example.rickandmorty.data.SeriesCharacter
import javax.inject.Inject

class CharactersServerDataSource @Inject constructor(
    private val charactersService: CharactersService
) : CharactersRemoteDataSource {

    override suspend fun getCharacters(): List<SeriesCharacter> {
        return charactersService.getCharacters().results.map {
            it.toSeriesCharacter()
        }
    }

}