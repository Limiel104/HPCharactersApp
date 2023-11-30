package com.example.hpcharactersapp.data.mapper

import com.example.hpcharactersapp.data.local.entity.AlternateNameEntity
import com.example.hpcharactersapp.data.local.entity.CharacterEntity
import com.example.hpcharactersapp.data.local.relation.CharacterWithAlternateName
import com.example.hpcharactersapp.data.remote.CharacterDto
import com.example.hpcharactersapp.data.remote.WandDto
import com.example.hpcharactersapp.domain.model.Character
import com.example.hpcharactersapp.domain.model.Wand

fun CharacterDto.toCharacter(): Character {
    return Character(
        id = id,
        actor = actor,
        alive = alive,
        alternateNames = alternateNames,
        ancestry = ancestry,
        dateOfBirth = dateOfBirth.orEmpty(),
        eyeColour = eyeColour,
        gender = gender,
        hairColour = hairColour,
        hogwartsStaff = hogwartsStaff,
        hogwartsStudent = hogwartsStudent,
        house = house,
        imageUrl = imageUrl,
        name = name,
        patronus = patronus,
        species = species,
        wand = wandDto.toWand(),
        wizard = wizard,
        yearOfBirth = yearOfBirth ?: 0
    )
}

fun WandDto.toWand(): Wand {
    return Wand(
        core = core.orEmpty(),
        length = length ?: 0.0,
        wood = wood.orEmpty()
    )
}

fun Character.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        actor = actor,
        alive = alive,
        ancestry = ancestry,
        dateOfBirth = dateOfBirth,
        eyeColour = eyeColour,
        gender = gender,
        hairColour = hairColour,
        hogwartsStaff = hogwartsStaff,
        hogwartsStudent = hogwartsStudent,
        house = house,
        imageUrl = imageUrl,
        name = name,
        patronus = patronus,
        species = species,
        wand = wand,
        wizard = wizard,
        yearOfBirth = yearOfBirth
    )
}

fun CharacterWithAlternateName.toCharacter(): Character {

    val alternateNameList = mutableListOf<String>()
    for(name in alternateNames) {
        alternateNameList.add(name.alternateName)
    }

    return Character(
        id = character.id,
        actor = character.actor,
        alive = character.alive,
        alternateNames = alternateNameList,
        ancestry = character.ancestry,
        dateOfBirth = character.dateOfBirth,
        eyeColour = character.eyeColour,
        gender = character.gender,
        hairColour = character.hairColour,
        hogwartsStaff = character.hogwartsStaff,
        hogwartsStudent = character.hogwartsStudent,
        house = character.house,
        imageUrl = character.imageUrl,
        name = character.name,
        patronus = character.patronus,
        species = character.species,
        wand = character.wand,
        wizard = character.wizard,
        yearOfBirth = character.yearOfBirth
    )
}

fun Character.getAlternateNamesEntityList(): List<AlternateNameEntity> {
    val alternateNameEntityList = mutableListOf<AlternateNameEntity>()

    for(name in alternateNames) {
        val newEntity = AlternateNameEntity(
            alternateNameId = 0,
            characterId = id,
            alternateName = name
        )
        alternateNameEntityList.add(newEntity)
    }

    return alternateNameEntityList
}