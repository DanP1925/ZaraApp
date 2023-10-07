package com.example.rickandmorty.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultCharacterRepository : CharacterRepository{
    override fun getCharacters(): Flow<List<SeriesCharacter>> = flow{

    }

}