package com.example.rickandmorty.data

import com.example.rickandmorty.data.source.local.CharactersLocalDataSource
import com.example.rickandmorty.data.source.network.CharactersRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import javax.inject.Inject

class DefaultCharactersRepository @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource,
    private val charactersLocalDataSource: CharactersLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : CharactersRepository {

    private fun getFakeCharacter() = SeriesCharacterDetail(
        1,
        "Rick Sanchez",
        "Alive",
        "Human",
        "",
        "Male",
        "Earth (C-137)",
        "Citadel of Ricks",
        "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
    )

    override fun getCharacters(): Flow<List<SeriesCharacter>> = flow {
        try{
            val characters = charactersRemoteDataSource.getCharacters()
            if (characters.isNotEmpty()){
                charactersLocalDataSource.deleteCharacters()
                charactersLocalDataSource.saveCharacters(characters)
                emit(characters)
            }
        } catch (exception: IOException){
            emit(charactersLocalDataSource.getCharacters())
        }
    }.flowOn(ioDispatcher)

    override fun getCharacterDetail(): Flow<SeriesCharacterDetail> = flow {
        emit(getFakeCharacter())
    }.flowOn(ioDispatcher)

}