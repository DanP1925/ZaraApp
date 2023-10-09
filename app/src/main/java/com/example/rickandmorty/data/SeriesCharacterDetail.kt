package com.example.rickandmorty.data

import com.example.rickandmorty.data.source.local.CharacterDetailEntity

data class SeriesCharacterDetail(
    val id: Int = -1,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val origin: String = "",
    val location: String = "",
    val image: String = ""
)

fun SeriesCharacterDetail.toCharacterDetailEntity(): CharacterDetailEntity {
    return CharacterDetailEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location,
        image = image
    )
}