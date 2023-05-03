package com.example.myapplication

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.LineAdapter
import com.example.myapplication.dataclasses.registos
import com.google.android.material.snackbar.Snackbar
import kotlin.math.max
import kotlin.random.Random

class perfil : AppCompatActivity() {
    private lateinit var myList: ArrayList<registos>
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

            else -> {super.onOptionsItemSelected(item)}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        myList = ArrayList<registos>()


        for (i in 0 until 20) {
            val randomLugar = Random.nextInt(0, 10)
            val randomDay = Random.nextInt(1, 31)
            val randomMonth = Random.nextInt(1, 12)
            val randomHourE = Random.nextInt(0, 24)
            val randomHourS = Random.nextInt(0, 24)

            myList.add(registos("Data - $randomDay/$randomMonth","Hora de Entrada: $randomHourE","Hora de Saida: $randomHourS" , "Lugar - $randomLugar"))
        }

        val total = myList.size

        //val NTT = findViewById<TextView>(R.id.NTT)
        //NTT.text = "Nº Total de Estacionamentos: "+total.toString()

        val FLL = findViewById<TextView>(R.id.h2)
        FLL.text = "Lugar Preferido: " + favLot()

        recycler_view = findViewById<RecyclerView>(R.id.recView)
        recycler_view.adapter = LineAdapter(myList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        swipeToDelete()

    }

    private fun favLot(): Int {
        val countMap = mutableMapOf<Int, Int>()
        var mostRepeatedNum = 0
        var maxCount = 0
        //var t=0

        for (i in 0 until myList.size) {
            val lugarNum = myList[i].lugar.filter { it.isDigit() }.toInt()

            if (countMap.containsKey(lugarNum)) {
                countMap[lugarNum] = countMap[lugarNum]!! + 1
            } else {
                countMap[lugarNum] = 1
            }

            if (countMap[lugarNum]!! > maxCount) {
                maxCount = countMap[lugarNum]!!
                mostRepeatedNum = lugarNum
                //t++
            }
        }
        //Toast.makeText(applicationContext,"$t",Toast.LENGTH_SHORT).show()
        return mostRepeatedNum
    }


    private fun swipeToDelete() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = myList[position]
                myList.removeAt(position)
                Snackbar.make(
                    viewHolder.itemView,
                    "Item '$position' apagado",
                    Snackbar.LENGTH_SHORT
                ).apply {
                    setAction("Desfazer"){
                        myList.add(position, item)
                        recycler_view.adapter?.notifyItemInserted(position)
                    }
                    show()
                }
                recycler_view.adapter?.notifyItemRemoved(position)
            }
        })
        itemTouchHelper.attachToRecyclerView(recycler_view)
    }

}
