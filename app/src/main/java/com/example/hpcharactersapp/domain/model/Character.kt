package com.example.hpcharactersapp.domain.model

import com.google.gson.annotations.SerializedName

data class Character(
    val id: String,
    val actor: String,
    val alive: Boolean,
    @SerializedName("alternate_names")
    val alternateNames: List<String>,
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
    val wand: Wand,
    val wizard: Boolean,
    val yearOfBirth: Int
)
