package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.dataclasses.registos



class LineAdapter(val list: ArrayList<registos>): RecyclerView.Adapter<LineViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layoutrecycler, parent, false);
        return LineViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        val currentPlace = list[position]

        holder._data.text = currentPlace.data
        holder._horaE.text = currentPlace.horaE
        holder._horaS.text = currentPlace.horaS
        holder._lugar.text = currentPlace.lugar



    }

}

class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val _data: TextView
    val _horaE: TextView
    val _horaS: TextView
    val _lugar: TextView

    init {
        _data = itemView.findViewById(R.id.txtdata)
        _horaE = itemView.findViewById(R.id.txthoraE)
        _horaS = itemView.findViewById(R.id.txthoraS)
        _lugar = itemView.findViewById(R.id.txtlugar)
    }

}
