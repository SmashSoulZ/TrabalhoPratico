package com.example.myapplication.api

import Article
import retrofit2.Call
import retrofit2.http.*

interface EndPoints {
    @GET("/users/")
    fun getArticle(): Call<List<Article>>


}