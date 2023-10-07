package com.example.rickandmorty.data.source.network

import com.example.rickandmorty.data.CharactersDataSource
import com.example.rickandmorty.data.SeriesCharacter

class CharactersRemoteDataSource : CharactersDataSource {

    private fun getFakeCharacters() = listOf<SeriesCharacter>(
        SeriesCharacter(
            1,
            "Rick Sanchez",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        ),
        SeriesCharacter(
            2,
            "Morty Smith",
            "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
        ),
        SeriesCharacter(
            3,
            "Summer Smith",
            "https://rickandmortyapi.com/api/character/avatar/3.jpeg"
        )
    )
    override suspend fun getCharacters(): List<SeriesCharacter> {
        return getFakeCharacters()
    }

}