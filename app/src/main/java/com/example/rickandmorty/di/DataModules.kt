package com.example.rickandmorty.di

import com.example.rickandmorty.data.CharactersRepository
import com.example.rickandmorty.data.DefaultCharactersRepository
import com.example.rickandmorty.data.CharactersDataSource
import com.example.rickandmorty.data.source.network.CharactersRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteCharactersDataSource

@Module
@InstallIn(SingletonComponent::class)
object CharactersRepositoryModule {

    @Singleton
    @RemoteCharactersDataSource
    @Provides
    fun providesRemoteCharactersDataSource(): CharactersDataSource {
        return CharactersRemoteDataSource()
    }

    @Singleton
    @Provides
    fun providesCharactersRepository(
        @RemoteCharactersDataSource charactersRemoteDataSource: CharactersDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): CharactersRepository {
        return DefaultCharactersRepository(charactersRemoteDataSource, ioDispatcher)
    }

}
