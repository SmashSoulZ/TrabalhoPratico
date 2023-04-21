import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.dataclasses.NewsApiResponse
import com.google.gson.Gson
import java.net.URL


class Noticias : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_recycler)
        val apiKey = "167ece40127d45a9a9691d08a1b45906"
        val url = "https://newsapi.org/v2/everything?q=bitcoin&from=2023-03-21&sortBy=publishedAt&apiKey=$apiKey"

        recyclerView = findViewById(R.id.recyclerview)

        // Fetch the news articles from the News API
        val request = URL(url).readText()
        val response = Gson().fromJson(request, NewsApiResponse::class.java)

        // Set up the RecyclerView with the fetched news articles
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NewsAdapter(response.articles)
    }
}
