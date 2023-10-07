package com.example.rickandmorty.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.CharactersRepository
import com.example.rickandmorty.data.DefaultCharactersRepository
import com.example.rickandmorty.data.source.local.CharacterDao
import com.example.rickandmorty.data.source.local.CharactersDBDataSource
import com.example.rickandmorty.data.source.local.CharactersLocalDataSource
import com.example.rickandmorty.data.source.local.RickAndMortyDatabase
import com.example.rickandmorty.data.source.network.CharactersRemoteDataSource
import com.example.rickandmorty.data.source.network.CharactersServerDataSource
import com.example.rickandmorty.data.source.network.CharactersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteCharactersDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalCharactersDataSource

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
    fun providesRemoteCharactersDataSource(charactersService: CharactersService): CharactersRemoteDataSource {
        return CharactersServerDataSource(charactersService)
    }

    @Singleton
    @Provides
    fun providesRickAndMortyDatabase(@ApplicationContext context: Context): RickAndMortyDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            RickAndMortyDatabase::class.java,
            "rickandmory-db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesCharacterDao(db : RickAndMortyDatabase): CharacterDao{
        return db.characterDao()
    }

    @Singleton
    @LocalCharactersDataSource
    @Provides
    fun providesLocalCharactersDataSource(characterDao : CharacterDao): CharactersLocalDataSource {
        return CharactersDBDataSource(characterDao)
    }

    @Singleton
    @Provides
    fun providesCharactersRepository(
        @RemoteCharactersDataSource charactersRemoteDataSource: CharactersRemoteDataSource,
        @LocalCharactersDataSource charactersLocalDataSource: CharactersLocalDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): CharactersRepository {
        return DefaultCharactersRepository(
            charactersRemoteDataSource,
            charactersLocalDataSource,
            ioDispatcher
        )
    }

}
