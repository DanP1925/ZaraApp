package com.example.rickandmorty

import androidx.annotation.VisibleForTesting
import com.example.rickandmorty.data.CharactersRepository
import com.example.rickandmorty.data.SeriesCharacter
import com.example.rickandmorty.data.SeriesCharacterDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCharactersRepository : CharactersRepository {

    private var savedCharacters: MutableList<SeriesCharacter> =
        emptyList<SeriesCharacter>().toMutableList()
    private var shouldFail = false

    private var savedCharacterDetail: SeriesCharacterDetail = SeriesCharacterDetail()

    override fun getCharacters(): Flow<List<SeriesCharacter>> = flow {
        if (!shouldFail) {
            emit(savedCharacters)
        } else {
            throw Exception("Test exception")
        }
    }

    override fun getCharacterDetail(id: Int): Flow<SeriesCharacterDetail> = flow {
        if (!shouldFail) {
            emit(savedCharacterDetail)
        } else {
            throw Exception("Test exception")
        }
    }

    @VisibleForTesting
    fun addCharacters(characters: List<SeriesCharacter>) {
        savedCharacters.clear()
        savedCharacters.addAll(characters)
    }

    @VisibleForTesting
    fun makeItFail() {
        shouldFail = true
    }

    @VisibleForTesting
    fun cleanFailFlag() {
        shouldFail = false
    }

    @VisibleForTesting
    fun addCharacterDetail(characterDetail: SeriesCharacterDetail) {
        savedCharacterDetail = characterDetail
    }

    @VisibleForTesting
    fun getNumberOfCharacters(): Int {
        return savedCharacters.size
    }

}