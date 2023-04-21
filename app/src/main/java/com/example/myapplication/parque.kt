package com.example.myapplication

import android.content.ContentValues.TAG
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase




class parque : AppCompatActivity() {

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
