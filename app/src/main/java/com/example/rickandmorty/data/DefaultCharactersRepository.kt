package com.example.rickandmorty.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultCharactersRepository @Inject constructor(
    private val charactersRemoteDataSource: CharactersDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : CharactersRepository {

    override fun getCharacters(): Flow<List<SeriesCharacter>> = flow {
        val characters = charactersRemoteDataSource.getCharacters()
        emit(characters)
    }.flowOn(ioDispatcher)

}