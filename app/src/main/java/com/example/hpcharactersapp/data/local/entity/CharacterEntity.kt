package com.example.hpcharactersapp.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hpcharactersapp.domain.model.Wand

@Entity
data class CharacterEntity(
    @PrimaryKey
    val id: String,
    val actor: String,
    val alive: Boolean,
    val ancestry: String,
    val dateOfBirth: String,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val imageUrl: String,
    val name: String,
    val patronus: String,
    val species: String,
    @Embedded
    val wand: Wand,
    val wizard: Boolean,
    val yearOfBirth: Int
)