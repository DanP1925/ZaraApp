package com.example.rickandmorty.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharacterDetailDao {

    @Query("SELECT * FROM CHARACTER_DETAIL WHERE id IN (:id)")
    suspend fun getCharacterDetail(id: Int) : CharacterDetailEntity

    @Insert
    suspend fun insertCharacterDetail(characterDetailEntity: CharacterDetailEntity)

    @Query("DELETE FROM CHARACTER_DETAIL WHERE id IN (:id)")
    suspend fun deleteCharacterDetail(id: Int)

}