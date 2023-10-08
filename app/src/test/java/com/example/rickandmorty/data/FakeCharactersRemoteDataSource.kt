package com.example.rickandmorty.data

import androidx.annotation.VisibleForTesting
import com.example.rickandmorty.data.source.network.CharactersRemoteDataSource

class FakeCharactersRemoteDataSource : CharactersRemoteDataSource {

    private var savedCharacters : MutableList<SeriesCharacter> = emptyList<SeriesCharacter>().toMutableList()
    private var savedCharacterDetail = SeriesCharacterDetail()

    override suspend fun getCharacters(): List<SeriesCharacter> {
        return savedCharacters
    }

    override suspend fun getCharacterDetail(id: Int): SeriesCharacterDetail {
        return if (id == savedCharacterDetail.id){
            savedCharacterDetail
        } else {
            SeriesCharacterDetail()
        }
    }

    @VisibleForTesting
    fun addCharacters(characters: List<SeriesCharacter>) {
        savedCharacters.clear()
        savedCharacters.addAll(characters)
    }

    @VisibleForTesting
    fun addCharacterDetail(characterDetail: SeriesCharacterDetail){
        savedCharacterDetail = characterDetail
    }

}