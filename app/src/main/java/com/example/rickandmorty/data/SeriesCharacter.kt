package com.example.rickandmorty.data

import com.example.rickandmorty.data.source.local.CharacterEntity

data class SeriesCharacter(
    val id: Int,
    val name: String,
    val image: String
)

fun SeriesCharacter.toCharacterEntity() : CharacterEntity{
    return CharacterEntity(
        id = id,
        name = name,
        image = image
    )
}
