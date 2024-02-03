package com.example.hpcharactersapp.domain

import com.example.hpcharactersapp.data.repository.SuggestionRepository
import com.example.hpcharactersapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class SuggestionRepositoryTestImpl: SuggestionRepository {
    override suspend fun addSuggestion(suggestion: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getSuggestions(): Flow<Resource<List<String>>> {
        TODO("Not yet implemented")
    }
}