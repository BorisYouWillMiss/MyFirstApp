package com.tsu.myfirstapplication

import com.tsu.myfirstapplication.models.WordModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitApi {
    @GET("{word}")
    fun word(@Path(value = "word", encoded = true) word : String): Call<List<WordModel>>
}