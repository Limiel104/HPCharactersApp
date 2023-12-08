package com.example.hpcharactersapp.domain.repository

import com.example.hpcharactersapp.data.local.CharacterDao
import com.example.hpcharactersapp.data.mapper.getAlternateNamesEntityList
import com.example.hpcharactersapp.data.mapper.toCharacter
import com.example.hpcharactersapp.data.mapper.toCharacterEntity
import com.example.hpcharactersapp.data.remote.HPApi
import com.example.hpcharactersapp.data.repository.CharacterRepository
import com.example.hpcharactersapp.domain.model.Character
import com.example.hpcharactersapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val api: HPApi,
    private val dao: CharacterDao
): CharacterRepository {
    override suspend fun getCharacters(query: String): Flow<Resource<List<Character>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val characters = dao.getCharacters(query).map { it.toCharacter() }
            val isDbEmpty = characters.isEmpty() && query.isBlank()
            val loadFromCache = !isDbEmpty

            if(loadFromCache) {
                emit(Resource.Success(characters))
                emit(Resource.Loading(isLoading = false))
                return@flow
            }

            val charactersFromRemote = try {
                api.getCharacters().map { it.toCharacter() }
            }
            catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e.message.toString()))
                null
            }
            catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(e.message()))
                null
            }

            charactersFromRemote?.let { characterList ->
                dao.deleteCharacters()
                for(character in characterList) {
                    dao.insertCharacterWithAlternateNames(character.toCharacterEntity(),character.getAlternateNamesEntityList())
                }
                emit(Resource.Success(dao.getCharacters(query).map { it.toCharacter() }))
            }

            emit(Resource.Loading(isLoading = false))
        }
    }

    override suspend fun getCharacter(characterId: String): Flow<Resource<Character>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val character = dao.getCharacter(characterId)

            if(character != null) {
                emit(Resource.Success(character.toCharacter()))
            } else {
                emit(Resource.Error(message = "No product found with this id"))
            }

            emit(Resource.Loading(isLoading = false))
        }
    }
}