package com.example.rickandmorty.data.source.network

import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersService {

    @GET("character")
    suspend fun getCharacters(): GetCharactersResponse

    @GET("character/{id}")
    suspend fun getCharacterDetail(@Path("id") id: Int): GetCharacterDetailResponse

}