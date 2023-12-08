package com.example.hpcharactersapp.data.repository

import com.example.hpcharactersapp.domain.model.Character
import com.example.hpcharactersapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getCharacters(query: String): Flow<Resource<List<Character>>>

    suspend fun getCharacter(characterId: String): Flow<Resource<Character>>
}