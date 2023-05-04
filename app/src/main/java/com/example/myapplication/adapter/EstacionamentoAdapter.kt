package com.example.myapplication.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.dataclasses.Parked2

class EstacionamentoAdapter(val list: ArrayList<Parked2>): RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layoutrecycler2, parent, false);
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPlace = list[position]

        holder._data.text = currentPlace.id
        holder._horaE.text = currentPlace.parked.toString()
    }

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val _data: TextView
    val _horaE: TextView


    init {
        _data = itemView.findViewById(R.id.txtdata)
        _horaE = itemView.findViewById(R.id.txthoraE)

    }

}