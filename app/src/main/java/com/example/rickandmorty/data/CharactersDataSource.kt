package com.example.rickandmorty.data

interface CharactersDataSource {
    suspend fun getCharacters() : List<SeriesCharacter>

}