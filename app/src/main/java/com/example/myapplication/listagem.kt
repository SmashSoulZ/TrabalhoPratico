package com.example.myapplication

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.EstacionamentoAdapter
import com.example.myapplication.adapter.LineAdapter

import com.example.myapplication.dataclasses.Parked2
import com.example.myapplication.dataclasses.registos

import com.google.firebase.firestore.FirebaseFirestore
import kotlin.random.Random

class listagem : AppCompatActivity() {

    private lateinit var myList: ArrayList<Parked2>
    private lateinit var recycler_view: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listagem)

        myList = ArrayList<Parked2>()



        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("Parques")


        collectionRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val id = document.id
                    val parked = document.getBoolean("parked") ?: false
                    val status = if (parked) "Ocupado" else "Livre"
                    myList.add(Parked2("Número do estacionamento: $id", "Disponibilidade: $status"))
                }


                recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
                recycler_view.adapter = EstacionamentoAdapter(myList)
                recycler_view.layoutManager = LinearLayoutManager(this)
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Erro ao buscar dados da Firestore", exception)
            }
    }



}
