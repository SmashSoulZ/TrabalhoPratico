package com.example.myapplication

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
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
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_mapa -> {
                Toast.makeText(this,"nav_mapa", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, parque::class.java))
                true
            }

            R.id.nav_lotacao -> {
                Toast.makeText(this, "nav_lotação", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, lotacao::class.java))
                true
            }
            R.id.nav_listagem -> {
                Toast.makeText(this, "nav_listagem", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, listagem::class.java))
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
