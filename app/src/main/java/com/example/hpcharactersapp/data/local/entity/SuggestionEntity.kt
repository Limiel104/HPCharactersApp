package com.example.hpcharactersapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SuggestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String
)