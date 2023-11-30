package com.example.hpcharactersapp.domain.use_case

import com.example.hpcharactersapp.data.repository.CharacterRepository
import com.example.hpcharactersapp.domain.model.Character
import com.example.hpcharactersapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase(
    private val repository: CharacterRepository
) {
    suspend fun execute(characterId: String): Flow<Resource<Character>> {
        return repository.getCharacter(characterId)
    }
}