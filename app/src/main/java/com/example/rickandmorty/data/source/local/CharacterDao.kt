package com.example.rickandmorty.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharacterDao {

    @Query("SELECT * FROM SERIES_CHARACTER")
    suspend fun getAllCharacters() : List<CharacterEntity>

    @Insert
    suspend fun insertAllCharacters(characters: List<CharacterEntity>)

    @Query("DELETE FROM SERIES_CHARACTER")
    suspend fun deleteAllCharacters()

}