package com.example.hpcharactersapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hpcharactersapp.data.local.entity.AlternateNameEntity
import com.example.hpcharactersapp.data.local.entity.CharacterEntity
import com.example.hpcharactersapp.data.local.entity.SuggestionEntity

@Database(
    entities = [CharacterEntity::class,AlternateNameEntity::class,SuggestionEntity::class],
    version = 1
)
abstract class CharactersDatabase: RoomDatabase() {

    abstract val characterDao: CharacterDao
    abstract val suggestionDao: SuggestionDao

    companion object {
        const val DATABASE_NAME = "characters.db"
    }
}