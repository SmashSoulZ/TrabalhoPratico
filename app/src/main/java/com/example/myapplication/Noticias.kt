package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.adapter.NewsAdaptor
import com.example.myapplication.dataclasses.News


class Noticias : AppCompatActivity() {

    private lateinit var adaptor: NewsAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adaptor = NewsAdaptor()
        recyclerView.adapter = adaptor

    }

    fun fetchNews(){
val queue = Volley.newRequestQueue(this)
        val url  = "https://saurav.tech/NewsAPI/top-headlines/category/general/in.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length()){
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("titlle"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }
                adaptor.updateDate(newsArray)
            },
            Response.ErrorListener {

            }
        )
        queue.add(jsonObjectRequest)
    }

}