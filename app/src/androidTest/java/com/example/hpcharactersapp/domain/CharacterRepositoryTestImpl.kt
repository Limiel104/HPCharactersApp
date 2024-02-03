package com.example.hpcharactersapp.domain

import com.example.hpcharactersapp.data.repository.CharacterRepository
import com.example.hpcharactersapp.domain.model.Character
import com.example.hpcharactersapp.domain.model.Wand
import com.example.hpcharactersapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepositoryTestImpl: CharacterRepository {

    private val wand1 =  Wand(
        wood = "holly",
        core = "phoenix tail feather",
        length = 11.0
    )

    private val wand2 = Wand(
        wood = "vine",
        core = "dragon heartstring",
        length = 10.75
    )

    private val character1 = Character(
        id = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
        actor = "Daniel Radcliffe",
        alive = true,
        alternateNames = listOf("The Boy Who Lived","The Chosen One","Undesirable No. 1","Potty"),
        ancestry = "half-blood",
        dateOfBirth = "31-07-1980",
        eyeColour = "green",
        gender = "male",
        hairColour = "black",
        hogwartsStaff = false,
        hogwartsStudent= true,
        house = "Gryffindor",
        imageUrl = "https://ik.imagekit.io/hpapi/harry.jpg",
        name = "Harry Potter",
        patronus = "stag",
        species = "human",
        wand = wand1,
        wizard = true,
        yearOfBirth = 1980
    )

    private val character2 = Character(
        id = "4c7e6819-a91a-45b2-a454-f931e4a7cce3",
        actor = "Emma Watson",
        alive = true,
        alternateNames = listOf("Hermy","Know-it-all","Miss Grant","Herm-own-ninny"),
        ancestry = "muggleborn",
        dateOfBirth = "19-09-1979",
        eyeColour = "brown",
        gender = "female",
        hairColour = "brown",
        hogwartsStaff = false,
        hogwartsStudent= true,
        house = "Gryffindor",
        imageUrl = "https://ik.imagekit.io/hpapi/hermione.jpeg",
        name = "Hermione Granger",
        patronus = "otter",
        species = "human",
        wand = wand2,
        wizard = true,
        yearOfBirth = 1979
    )

    override suspend fun getCharacters(query: String): Flow<Resource<List<Character>>> {
        return flow { emit(Resource.Success(listOf(character1,character2))) }
    }

    override suspend fun getCharacter(characterId: String): Flow<Resource<Character>> {
        return flow { emit(Resource.Success(character1)) }
    }
}