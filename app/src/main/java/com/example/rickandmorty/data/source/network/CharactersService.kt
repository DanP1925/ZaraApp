package com.example.rickandmorty.data.source.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {

    @GET("character")
    suspend fun getCharacters(): GetCharactersResponse

    @GET("character/{id}")
    suspend fun getCharacterDetail(@Path("id") id: Int): GetCharacterDetailResponse

    @GET("character/")
    suspend fun getFilteredCharacters(@Query("name") text: String): GetCharactersResponse

}