package com.example.myapplication

import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase




class parque : AppCompatActivity() {

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
        setContentView(R.layout.layout_principal)

        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("Parques")

        val gridLayout = findViewById<GridLayout>(R.id.grid_layout)

        collectionRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val parked = document.getBoolean("parked") ?: false

                    val rect = View(this).apply {
                        id = document.id.hashCode()
                        setBackgroundResource(R.drawable.border)
                        setBackgroundColor(if (parked) Color.GREEN else Color.RED)
                        layoutParams = GridLayout.LayoutParams().apply {
                            width = resources.getDimensionPixelSize(R.dimen.rect_width)
                            height = resources.getDimensionPixelSize(R.dimen.rect_height)
                        }
                    }
                    gridLayout.addView(rect)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }



}
