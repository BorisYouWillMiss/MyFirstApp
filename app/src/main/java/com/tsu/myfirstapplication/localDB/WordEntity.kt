package com.tsu.myfirstapplication.localDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity (
            @PrimaryKey(autoGenerate = false)
            val word : String,
            val partOfSpeech : String,
            val audio : String,
            val phonetics : String
        )