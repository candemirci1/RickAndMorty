package com.example.rickandmorty.di

import com.example.rickandmorty.data.repository.*
import com.example.rickandmorty.data.service.RickAndMortyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(service: RickAndMortyService):CharactersRepository{
        return CharacterRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(service: RickAndMortyService):LocationRepository{
        return LocationRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideEpisodeRepository(service: RickAndMortyService):EpisodeRepository{
        return EpisodeRepositoryImpl(service)
    }
}