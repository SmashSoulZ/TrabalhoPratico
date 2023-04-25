package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api.Article
import com.example.myapplication.api.TopHeadlinesResponse
import com.squareup.picasso.Picasso


class ArticlesAdapter(val articles: List<Article>): RecyclerView.Adapter<TopHeadlinesResponseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadlinesResponseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview, parent, false)
        return TopHeadlinesResponseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: TopHeadlinesResponseViewHolder, position: Int) {
        return holder.bind(articles[position])
    }
}

class TopHeadlinesResponseViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val image : ImageView = itemView.findViewById(R.id.image)
    private val author: TextView = itemView.findViewById(R.id.author)
    private val title:TextView = itemView.findViewById(R.id.title)

    fun bind(article: Article) {

        Picasso.get().load(article.urlToImage).into(image)
        author.text = article.author
        title.text = article.title
    }

}

