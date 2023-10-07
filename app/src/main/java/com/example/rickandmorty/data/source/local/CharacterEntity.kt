package com.example.rickandmorty.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.SeriesCharacter

@Entity(tableName = "SERIES_CHARACTER")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "image") val image : String?
)

fun CharacterEntity.toSeriesCharacter() : SeriesCharacter {
    return SeriesCharacter(
        id = id,
        name = name ?: "",
        image = image ?: ""
    )
}