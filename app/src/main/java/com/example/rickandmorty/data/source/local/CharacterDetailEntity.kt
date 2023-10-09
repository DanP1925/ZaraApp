package com.example.rickandmorty.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.SeriesCharacterDetail

@Entity(tableName = "CHARACTER_DETAIL")
data class CharacterDetailEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "species") val species: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "origin") val origin: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "image") val image: String
)

fun CharacterDetailEntity.toSeriesCharacterDetail(): SeriesCharacterDetail {
    return SeriesCharacterDetail(
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