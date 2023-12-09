package com.example.hpcharactersapp.domain.use_case

import com.example.hpcharactersapp.data.repository.SuggestionRepository
import com.example.hpcharactersapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetSuggestionsUseCase(
    private val repository: SuggestionRepository
) {

    suspend fun execute(): Flow<Resource<List<String>>> {
        return repository.getSuggestions()
    }
}