package com.example.rickandmorty.di

import com.example.rickandmorty.data.service.RickAndMortyService
import com.example.rickandmorty.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): RickAndMortyService {
        return retrofit.create(RickAndMortyService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }
}