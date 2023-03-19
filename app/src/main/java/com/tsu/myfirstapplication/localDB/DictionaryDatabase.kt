package com.tsu.myfirstapplication.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        WordEntity::class,
        DefinitionEntity::class
    ],
    version = 1
)
abstract class DictionaryDatabase : RoomDatabase (){

    abstract val wordDao: WordDao

    companion object{
        @Volatile
        private var INSTANCE: DictionaryDatabase? = null

        fun getInstance(context: Context): DictionaryDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    DictionaryDatabase::class.java,
                    "dictionary_db"
                ).build().also{
                    INSTANCE = it
                }
            }
        }
    }
}