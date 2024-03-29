package com.tsu.myfirstapplication.models

data class WordModel (
    var word: String,
    var phonetic: String,
    var phonetics: ArrayList<PhoneticsModel> = ArrayList(),
    var meanings: ArrayList<MeaningModel> = ArrayList()
)