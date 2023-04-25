package com.example.myapplication

import Article
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.ArticlesAdapter
import com.example.myapplication.api.EndPoints
import com.example.retrofit.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Noticias : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)


        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getArticle()
        call.enqueue(object : Callback<List<Article>> {
            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                if (response.isSuccessful){
                    (findViewById<RecyclerView>(R.id.recyclerView)).apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@Noticias)
                        adapter = ArticlesAdapter(response.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                Toast.makeText(this@Noticias, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }
}