package com.example.rickandmorty20.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty20.common.Converters
import com.example.rickandmorty20.data.database.dao.FavoriteCharactersDao
import com.example.rickandmorty20.data.models.CharacterData
import com.example.rickandmorty20.data.models.CharacterLocationData
import com.example.rickandmorty20.data.models.CharacterOriginData


const val DatabaseName = "characters_db";

@Database(
    entities = [CharacterData::class, CharacterOriginData::class, CharacterLocationData::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DataBase : RoomDatabase() {
    abstract fun favoriteCharactersDao(): FavoriteCharactersDao

    companion object {
        @Volatile
        private var Instance: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DataBase::class.java, DatabaseName).build()
                    .also { Instance = it }
            }
        }
    }

}