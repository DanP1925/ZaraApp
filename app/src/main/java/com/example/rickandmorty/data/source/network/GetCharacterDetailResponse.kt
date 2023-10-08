package com.example.rickandmorty.data.source.network

import com.example.rickandmorty.data.SeriesCharacterDetail
import com.google.gson.annotations.SerializedName

data class GetCharacterDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("origin") val origin: GetCharacterDetailOrigin,
    @SerializedName("location") val location: GetCharacterDetailLocation,
    @SerializedName("image") val image: String
)

data class GetCharacterDetailOrigin(
    @SerializedName("name") val name: String
)
data class GetCharacterDetailLocation(
    @SerializedName("name") val name: String
)

fun GetCharacterDetailResponse.toSeriesCharacterDetail() : SeriesCharacterDetail{
    return SeriesCharacterDetail(
        id,
        name,
        status,
        species,
        type,
        gender,
        origin.name,
        location.name,
        image
    )
}
