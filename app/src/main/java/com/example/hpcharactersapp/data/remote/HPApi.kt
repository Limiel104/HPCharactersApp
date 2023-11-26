package com.example.hpcharactersapp.data.remote

import retrofit2.http.GET

interface HPApi {

    @GET("characters")
    suspend fun getCharacters(): List<CharacterDto>

    @GET("character/{characterId}")
    suspend fun getCharacter(): CharacterDto

    companion object {
        const val BASE_URL = "https://hp-api.onrender.com/api/"
    }
}