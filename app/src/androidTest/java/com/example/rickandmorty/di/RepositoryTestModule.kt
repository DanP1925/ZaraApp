package com.example.rickandmorty.di

import com.example.rickandmorty.FakeCharactersRepository
import com.example.rickandmorty.data.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CharactersRepositoryModule::class]
)
object RepositoryTestModule {

    @Singleton
    @Provides
    fun provideTasksRepository(): CharactersRepository{
        return FakeCharactersRepository()
    }

}