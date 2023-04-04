package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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

//Csdad
        myList = ArrayList<registos>()


        for (i in 0 until 20){
            val randomLugar = Random.nextInt(0, 32)
            val randomDay = Random.nextInt(1, 31)
            val randomMonth = Random.nextInt(1, 12)
            val randomHourE = Random.nextInt(0, 24)
            val randomHourS = Random.nextInt(0, 24)


            myList.add(registos("Data - $randomDay/$randomMonth","Hora de Entrada: $randomHourE","Hora de Saida: $randomHourS" , "Lugar - $randomLugar"))
        }

        val total=myList.size
        val NTT = findViewById<TextView>(R.id.NTT)
        NTT.text = "Nº Total de Estacionamentos: "+total.toString()


        recycler_view = findViewById<RecyclerView>(R.id.recView)
        recycler_view.adapter=LineAdapter(myList)
        recycler_view.layoutManager = LinearLayoutManager(this)
    }
}