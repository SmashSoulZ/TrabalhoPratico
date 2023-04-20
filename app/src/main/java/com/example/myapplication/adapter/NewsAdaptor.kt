package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.dataclasses.News

class NewsAdaptor: RecyclerView.Adapter<NewsAdaptor.NewsViewHolder>() {

    private val items = ArrayList<News>()
    class NewsViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView){
        val titleView : TextView = itemsView.findViewById(R.id.title)
        val author : TextView = itemsView.findViewById(R.id.author)
        val image : ImageView = itemsView.findViewById(R.id.image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
       val currentNews = items[position]
        holder.titleView.text = currentNews.title
        holder.author.text = currentNews.author

        Glide.with(holder.itemView.context).load(currentNews.imageUrl).into(holder.image)
    }

    fun updateDate(newData:ArrayList<News>){
        items.clear()
        items.addAll(newData)

        notifyDataSetChanged()
    }

}