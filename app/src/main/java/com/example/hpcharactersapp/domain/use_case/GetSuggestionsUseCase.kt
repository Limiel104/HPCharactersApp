package com.example.hpcharactersapp.domain.use_case

class GetSuggestionsUseCase() {

    suspend fun execute(): List<String> {
        return listOf("one","two","three","four")
    }
}