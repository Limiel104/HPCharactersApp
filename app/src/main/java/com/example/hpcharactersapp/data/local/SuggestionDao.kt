package com.example.hpcharactersapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hpcharactersapp.data.local.entity.SuggestionEntity

@Dao
interface SuggestionDao {
    @Insert
    suspend fun insertSuggestion(suggestion: SuggestionEntity)

    @Query(
        """
            SELECT * 
            FROM suggestionentity
            ORDER BY id DESC
            LIMIT 30
        """
    )
    suspend fun getSuggestions(): List<SuggestionEntity>
}