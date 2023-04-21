package com.example.myapplication.dataclasses

data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticle>
)

data class NewsArticle(
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String
)
