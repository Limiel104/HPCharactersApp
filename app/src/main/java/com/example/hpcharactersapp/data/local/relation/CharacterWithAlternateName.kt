package com.example.hpcharactersapp.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hpcharactersapp.data.local.entity.AlternateNameEntity
import com.example.hpcharactersapp.data.local.entity.CharacterEntity

data class CharacterWithAlternateName(
    @Embedded
    val character: CharacterEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val alternateNames: List<AlternateNameEntity>
)