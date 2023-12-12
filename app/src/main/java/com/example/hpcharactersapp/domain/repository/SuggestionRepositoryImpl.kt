package com.example.hpcharactersapp.domain.repository

import com.example.hpcharactersapp.data.local.SuggestionDao
import com.example.hpcharactersapp.data.local.entity.SuggestionEntity
import com.example.hpcharactersapp.data.repository.SuggestionRepository
import com.example.hpcharactersapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SuggestionRepositoryImpl @Inject constructor(
    private val dao: SuggestionDao
): SuggestionRepository {
    override suspend fun addSuggestion(suggestion: String) {
        val suggestionEntity = SuggestionEntity(
            id = 0,
            text = suggestion
        )
        dao.insertSuggestion(suggestionEntity)
    }

    override suspend fun getSuggestions(): Flow<Resource<List<String>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val suggestions = dao.getSuggestions().map { it.text }
            emit(Resource.Success(suggestions))
            emit(Resource.Loading(isLoading = false))
        }
    }
}