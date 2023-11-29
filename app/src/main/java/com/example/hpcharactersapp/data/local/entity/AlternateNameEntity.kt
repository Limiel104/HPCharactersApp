package com.example.hpcharactersapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlternateNameEntity(
    @PrimaryKey(autoGenerate = true)
    val alternateNameId: Int,
    val characterId: String,
    val alternateName: String
)