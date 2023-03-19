package com.tsu.myfirstapplication.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: WordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDefinition(definition: DefinitionEntity)

    @Transaction
    @Query("SELECT * FROM words WHERE word = :word")
    suspend fun getWordWithDefinitions(word: String): List<WordWithDefinitions>
}