package com.example.hpcharactersapp.data.repository

import com.example.hpcharactersapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface SuggestionRepository {

    suspend fun addSuggestion(suggestion: String)
    suspend fun getSuggestions(): Flow<Resource<List<String>>>
}