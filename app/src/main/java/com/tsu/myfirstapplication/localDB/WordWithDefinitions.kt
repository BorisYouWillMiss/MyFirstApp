package com.tsu.myfirstapplication.localDB

import androidx.room.Embedded
import androidx.room.Relation

data class WordWithDefinitions (
    @Embedded val Word: WordEntity,
    @Relation(
        parentColumn = "word",
        entityColumn = "word"
    )
    val definitions: List<DefinitionEntity>
)