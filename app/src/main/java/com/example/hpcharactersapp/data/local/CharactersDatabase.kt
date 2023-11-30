package com.example.hpcharactersapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hpcharactersapp.data.local.entity.AlternateNameEntity
import com.example.hpcharactersapp.data.local.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class,AlternateNameEntity::class],
    version = 1
)
abstract class CharactersDatabase: RoomDatabase() {

    abstract val characterDao: CharacterDao

    companion object {
        const val DATABASE_NAME = "characters.db"
    }
}