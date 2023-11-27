package com.example.hpcharactersapp.data.mapper

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
        wand = wandDto.toWand(),
        wizard = wizard,
        yearOfBirth = yearOfBirth
    )
}

fun WandDto.toWand(): Wand {
    return Wand(
        core = core,
        length = length,
        wood = wood
    )
}