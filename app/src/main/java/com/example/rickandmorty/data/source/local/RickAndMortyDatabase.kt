package com.example.rickandmorty.data.source.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterEntity::class, CharacterDetailEntity::class],
    version = 2,
)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun characterDetailDao(): CharacterDetailDao
}