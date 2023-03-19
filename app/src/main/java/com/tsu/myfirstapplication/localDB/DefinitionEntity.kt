package com.tsu.myfirstapplication.localDB

import android.text.Spannable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "definitions")
data class DefinitionEntity (
            @PrimaryKey(autoGenerate = true)
            val id: Int,
            val word : String,
            val definition : String,
            val example : String
        )