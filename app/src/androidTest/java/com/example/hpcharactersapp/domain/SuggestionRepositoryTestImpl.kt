package com.example.hpcharactersapp.domain

import com.example.hpcharactersapp.data.repository.SuggestionRepository
import com.example.hpcharactersapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SuggestionRepositoryTestImpl: SuggestionRepository {
    override suspend fun addSuggestion(suggestion: String) {}

    override suspend fun getSuggestions(): Flow<Resource<List<String>>> {
        return flow { emit(Resource.Success(emptyList())) }
    }
}