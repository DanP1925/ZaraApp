package com.example.rickandmorty.data.source.network

import com.example.rickandmorty.data.SeriesCharacter
import com.google.gson.annotations.SerializedName

data class GetCharactersResponse(
    @SerializedName("info") val getCharactersInfo : GetCharactersInfo,
    @SerializedName("results") val results: List<NetworkSeriesCharacter>
)

data class GetCharactersInfo(
    @SerializedName("count") val count : Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?
)

data class NetworkSeriesCharacter(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)

fun NetworkSeriesCharacter.toSeriesCharacter() = SeriesCharacter(
    id, name, image
)