package com.example.rickandmorty.data

import androidx.annotation.VisibleForTesting
import com.example.rickandmorty.data.source.network.CharactersRemoteDataSource
import java.io.IOException

class FakeCharactersRemoteDataSource : CharactersRemoteDataSource {

    private var savedCharacters : MutableList<SeriesCharacter> = emptyList<SeriesCharacter>().toMutableList()
    private var savedCharacterDetail = SeriesCharacterDetail()
    private var shouldFail = false

    override suspend fun getCharacters(): List<SeriesCharacter> {
        if(!shouldFail){
            return savedCharacters
        } else{
            throw IOException("Test exception")
        }
    }

    override suspend fun getCharacterDetail(id: Int): SeriesCharacterDetail {
        if (!shouldFail){
            return if (id == savedCharacterDetail.id){
                savedCharacterDetail
            } else {
                SeriesCharacterDetail()
            }
        } else{
            throw IOException("Test exception")
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

    @VisibleForTesting
    fun makeItFail(){
        shouldFail = true
    }

    @VisibleForTesting
    fun cleanFailFlag(){
        shouldFail = false
    }

}