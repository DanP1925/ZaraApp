package com.example.rickandmorty

import androidx.annotation.VisibleForTesting
import com.example.rickandmorty.data.CharactersRepository
import com.example.rickandmorty.data.SeriesCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCharactersRepository : CharactersRepository {

    private var savedCharacters : MutableList<SeriesCharacter> = emptyList<SeriesCharacter>().toMutableList()

    override fun getCharacters(): Flow<List<SeriesCharacter>> = flow {
        emit(savedCharacters)
    }

    @VisibleForTesting
    fun addCharacters(characters: List<SeriesCharacter>) {
        savedCharacters.clear()
        savedCharacters.addAll(characters)
    }

}