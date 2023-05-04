package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.ArticlesAdapter
import com.example.myapplication.api.Article
import com.example.myapplication.api.ServiceBuilder
import com.example.myapplication.api.ServiceBuilder.newsApi
import com.example.myapplication.api.TopHeadlinesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Noticias : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_mapa -> {
                Toast.makeText(this,"nav_mapa", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, parque::class.java))
                true
            }

            R.id.nav_reportar -> {
                Toast.makeText(this, "nav_reportar", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Report::class.java))
                true
            }
            R.id.nav_perfil -> {
                Toast.makeText(this, "nav_perfil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, perfil::class.java))
                true
            }

            R.id.nav_noticias -> {
                Toast.makeText(this, "nav_noticias", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Noticias::class.java))
                true
            }
            else -> {super.onOptionsItemSelected(item)}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)




        val call = newsApi.getTopHeadlines("us", "167ece40127d45a9a9691d08a1b45906")

        call.enqueue(object : Callback<TopHeadlinesResponse> {
            override fun onResponse(
                call: Call<TopHeadlinesResponse>,
                response: Response<TopHeadlinesResponse>
            ) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles
                    (findViewById<RecyclerView>(R.id.recyclerView)).apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@Noticias)
                        adapter = response.body()?.articles?.let { ArticlesAdapter(it) }

                    }
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<TopHeadlinesResponse>, t: Throwable) {
                // Handle the error
            }
        })

    }
}