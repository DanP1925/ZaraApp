package com.example.rickandmorty.data.source.network

import retrofit2.http.GET

interface CharactersService {

    @GET("character")
    suspend fun getCharacters() : GetCharactersResponse

}