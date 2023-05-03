package com.example.myapplication.api


import retrofit2.Call
import retrofit2.http.*

interface NewsApiService {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<TopHeadlinesResponse>
}
