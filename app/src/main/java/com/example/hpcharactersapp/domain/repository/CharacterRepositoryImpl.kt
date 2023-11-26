package com.example.hpcharactersapp.domain.repository

import com.example.hpcharactersapp.data.mapper.toCharacter
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
    private val api: HPApi
): CharacterRepository {
    override suspend fun getCharacters(): Flow<Resource<List<Character>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val characters = try {
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

            emit(Resource.Success(characters))
            emit(Resource.Loading(isLoading = false))
        }
    }

    override suspend fun getCharacter(characterId: String): Flow<Resource<List<Character>>> {
        TODO("Not yet implemented")
    }
}