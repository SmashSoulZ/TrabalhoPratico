package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.LineAdapter
import com.example.myapplication.dataclasses.registos
import kotlin.math.max
import kotlin.random.Random

class perfil : AppCompatActivity() {
    private lateinit var myList: ArrayList<registos>
    private lateinit var recycler_view: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        myList = ArrayList<registos>()

        for (i in 0 until 20){
            val randomLugar = Random.nextInt(0, 32)
            val randomDay = Random.nextInt(1, 31)
            val randomMonth = Random.nextInt(1, 12)
            val randomHour = Random.nextInt(0, 24)



            myList.add(registos("Data - $randomDay/$randomMonth","Hora - $randomHour", randomLugar))
        }
        recycler_view = findViewById<RecyclerView>(R.id.recView)
        recycler_view.adapter=LineAdapter(myList)
        recycler_view.layoutManager = LinearLayoutManager(this)
    }
}