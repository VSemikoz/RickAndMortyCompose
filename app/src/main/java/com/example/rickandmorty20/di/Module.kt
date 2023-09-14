package com.example.rickandmorty20.di

import android.content.Context
import com.example.rickandmorty20.common.network.NetworkClient
import com.example.rickandmorty20.data.database.DataBase
import com.example.rickandmorty20.data.api.CharacterApi
import com.example.rickandmorty20.data.repository.CharacterRepository
import com.example.rickandmorty20.data.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Module {
    @Binds
    abstract fun bindRepository(characterRepository: CharacterRepositoryImpl): CharacterRepository

    companion object {
        @Provides
        @Singleton
        fun provideMainService(retrofit: Retrofit): CharacterApi =
            retrofit.create(CharacterApi::class.java)

        @Provides
        @Singleton
        fun provideClient(): Retrofit {
            return NetworkClient.getClient()
        }

        @Provides
        @Singleton
        fun provideDataBase(@ApplicationContext app: Context): DataBase {
            return DataBase.getDatabase(app)
        }
    }
}
