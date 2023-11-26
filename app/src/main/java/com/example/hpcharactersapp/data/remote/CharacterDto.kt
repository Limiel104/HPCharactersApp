package com.example.hpcharactersapp.data.remote

import com.google.gson.annotations.SerializedName

data class CharacterDto(
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
    @SerializedName("image")
    val imageUrl: String,
    val name: String,
    val patronus: String,
    val species: String,
    val wandDto: WandDto,
    val wizard: Boolean,
    val yearOfBirth: Int
)