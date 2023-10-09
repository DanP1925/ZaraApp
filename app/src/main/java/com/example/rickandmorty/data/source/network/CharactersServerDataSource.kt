package com.example.rickandmorty.data.source.network

import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.data.SeriesCharacterDetail
import javax.inject.Inject

class CharactersServerDataSource @Inject constructor(
    private val charactersService: CharactersService
) : CharactersRemoteDataSource {

    override suspend fun getCharacters(): List<SeriesCharacter> {
        return charactersService.getCharacters().results.map {
            it.toSeriesCharacter()
        }
    }

    override suspend fun getCharacterDetail(id: Int): SeriesCharacterDetail {
        return charactersService.getCharacterDetail(id).toSeriesCharacterDetail()
    }

    override suspend fun getFilteredCharacters(text: String): List<SeriesCharacter> {
        return charactersService.getFilteredCharacters(text).results.map {
            it.toSeriesCharacter()
        }
    }

}