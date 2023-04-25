package com.example.myapplication.adapter

import Article
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R



class ArticlesAdapter(val articles: List<Article>): RecyclerView.Adapter<ArticlesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview, parent, false)
        return ArticlesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        return holder.bind(articles[position])
    }
}

class ArticlesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val author: TextView = itemView.findViewById(R.id.author)
    private val title:TextView = itemView.findViewById(R.id.title)

    fun bind(article: Article) {
        author.text = article.author
        title.text = article.title
    }

}
