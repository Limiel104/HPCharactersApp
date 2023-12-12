package com.example.hpcharactersapp.domain.use_case

import com.example.hpcharactersapp.data.repository.SuggestionRepository

class AddSuggestionUseCase(
    private val repository: SuggestionRepository
) {

    suspend fun execute(suggestion:  String) {
        repository.addSuggestion(suggestion)
    }
}