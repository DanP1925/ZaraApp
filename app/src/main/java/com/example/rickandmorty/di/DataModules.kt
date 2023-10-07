package com.example.rickandmorty.di

import com.example.rickandmorty.data.CharactersRepository
import com.example.rickandmorty.data.DefaultCharactersRepository
import com.example.rickandmorty.data.CharactersDataSource
import com.example.rickandmorty.data.source.network.CharactersRemoteDataSource
import com.example.rickandmorty.data.source.network.CharactersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteCharactersDataSource

@Module
@InstallIn(SingletonComponent::class)
object CharactersRepositoryModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCharactersService(retrofit: Retrofit): CharactersService {
        return retrofit.create(CharactersService::class.java)
    }

    @Singleton
    @RemoteCharactersDataSource
    @Provides
    fun providesRemoteCharactersDataSource(charactersService: CharactersService): CharactersDataSource {
        return CharactersRemoteDataSource(charactersService)
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
