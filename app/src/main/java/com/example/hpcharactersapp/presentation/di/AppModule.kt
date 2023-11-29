package com.example.hpcharactersapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.hpcharactersapp.data.local.CharactersDatabase
import com.example.hpcharactersapp.data.remote.HPApi
import com.example.hpcharactersapp.data.repository.CharacterRepository
import com.example.hpcharactersapp.domain.repository.CharacterRepositoryImpl
import com.example.hpcharactersapp.domain.use_case.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHPApi(): HPApi {
        return Retrofit.Builder()
            .baseUrl(HPApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideCharacterDatabase(application: Application): CharactersDatabase {
        return Room.databaseBuilder(
            application,
            CharactersDatabase::class.java,
            CharactersDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: HPApi, db: CharactersDatabase): CharacterRepository {
        return CharacterRepositoryImpl(api, db.characterDao)
    }

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(repository: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCase(repository)
    }
}