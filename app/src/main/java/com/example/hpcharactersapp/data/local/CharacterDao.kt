package com.example.hpcharactersapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.hpcharactersapp.data.local.entity.AlternateNameEntity
import com.example.hpcharactersapp.data.local.entity.CharacterEntity
import com.example.hpcharactersapp.data.local.relation.CharacterWithAlternateName

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlternateNames(alternateNames: List<AlternateNameEntity>)

    @Transaction
    suspend fun insertCharacterWithAlternateNames(character: CharacterEntity,alternateNames: List<AlternateNameEntity>) {
        insertCharacter(character)
        insertAlternateNames(alternateNames)
    }

    @Transaction
    @Query(
        """
            SELECT *
            FROM characterentity
        """
    )
    suspend fun getCharacters(): List<CharacterWithAlternateName>

    @Transaction
    @Query(
        """
            SELECT *
            FROM characterentity
            WHERE id = :characterId
        """
    )
    suspend fun getCharacter(characterId: String): CharacterWithAlternateName?

    @Query("DELETE FROM characterentity")
    suspend fun deleteCharacters()
}