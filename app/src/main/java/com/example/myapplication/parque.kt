package com.example.myapplication

import android.content.ContentValues.TAG
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase




class parque : AppCompatActivity() {
    private var switch1Ativo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_principal)

        val button2 = findViewById<Button>(R.id.filtros)

        button2.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setView(R.layout.layout_definicoes)

            val dialog = builder.create()

            dialog.show()
        }
        val dialogView = LayoutInflater.from(this).inflate(R.layout.layout_definicoes, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        val dialog = builder.create()
        dialog.show()


        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("Parques")

        val gridLayout = findViewById<GridLayout>(R.id.grid_layout)

        val button = findViewById<Button>(R.id.botao1)
        button.setOnClickListener {
            showInputDialog()
        }
        collectionRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val parked = document.getBoolean("parked") ?: false

                    val rect = LinearLayout(this).apply {
                        id = document.id.hashCode()
                        setBackgroundResource(R.drawable.border)
                        setBackgroundColor(if (parked) Color.GREEN else Color.RED)
                        layoutParams = GridLayout.LayoutParams().apply {
                            width = resources.getDimensionPixelSize(R.dimen.rect_width)
                            height = resources.getDimensionPixelSize(R.dimen.rect_height)
                            marginStart = resources.getDimensionPixelSize(R.dimen.cell_spacing)
                            marginEnd = resources.getDimensionPixelSize(R.dimen.cell_spacing)
                        }
                    }

                    // Adiciona um TextView no meio do retângulo
                    val textView = TextView(this).apply {
                        text = document.id
                        setTextColor(Color.WHITE)
                        gravity = Gravity.CENTER
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    }
                    rect.addView(textView)

                    gridLayout.addView(rect)

                    Log.d(TAG, "ID do estacionamento: ${document.id}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
        val switch1 = dialog.findViewById<Switch>(R.id.switch1)

        switch1?.setOnCheckedChangeListener { _, isChecked ->
            // Obtenha a quantidade de retângulos
            val count = gridLayout.childCount

            // Se o switch estiver marcado, mostre apenas os três primeiros retângulos
            if (isChecked) {
                for (i in 0 until count) {
                    val rect = gridLayout.getChildAt(i) as? LinearLayout
                    if (rect != null) {
                        if (i < 3) {
                            rect.visibility = View.VISIBLE
                        } else {
                            rect.visibility = View.INVISIBLE
                        }
                    }
                }
            } else { // Se o switch não estiver marcado, mostre todos os retângulos
                for (i in 0 until count) {
                    val rect = gridLayout.getChildAt(i) as? LinearLayout
                    if (rect != null) {
                        rect.visibility = View.VISIBLE
                    }
                }
            }
        }
        val switch2 = dialog.findViewById<Switch>(R.id.switch2)
        switch2?.setOnCheckedChangeListener { _, isChecked ->
            // Obtenha a quantidade de retângulos
            val count = gridLayout.childCount

            // Se o switch estiver marcado, mostre apenas os dois últimos retângulos
            if (isChecked) {
                for (i in 0 until count) {
                    val rect = gridLayout.getChildAt(i) as? LinearLayout
                    if (rect != null) {
                        if (i >= count - 2) {
                            rect.visibility = View.VISIBLE
                        } else {
                            rect.visibility = View.INVISIBLE
                        }
                    }
                }
            } else { // Se o switch não estiver marcado, mostre todos os retângulos
                for (i in 0 until count) {
                    val rect = gridLayout.getChildAt(i) as? LinearLayout
                    if (rect != null) {
                        rect.visibility = View.VISIBLE
                    }
                }
            }
        }


        val switch3 = dialog.findViewById<Switch>(R.id.switch3)
        val numCols = gridLayout.columnCount
        var colIndex = 0
        if (switch3 != null) {
            switch3.setOnCheckedChangeListener { _, isChecked ->
                // Obtenha a quantidade de retângulos
                val count = gridLayout.childCount

                // Se o switch estiver marcado, mostre apenas os retângulos nas colunas 1, 4, 7 e assim por diante
                if (isChecked) {
                    for (i in 0 until count) {
                        val rect = gridLayout.getChildAt(i) as? LinearLayout
                        if (rect != null) {
                            if (colIndex == 1 || (colIndex - 1) % 3 == 0) {
                                rect.visibility = View.VISIBLE
                            } else {
                                rect.visibility = View.INVISIBLE
                            }
                        }
                        colIndex++
                        if (colIndex == numCols) {
                            colIndex = 0
                        }
                    }
                } else { // Se o switch não estiver marcado, mostre todos os retângulos
                    for (i in 0 until count) {
                        val rect = gridLayout.getChildAt(i) as? LinearLayout
                        if (rect != null) {
                            rect.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }











    }

    private fun showInputDialog() {
        val inputDialog = AlertDialog.Builder(this)
        inputDialog.setTitle("Digite o ID do estacionamento:")
        val input = EditText(this)
        inputDialog.setView(input)
        inputDialog.setPositiveButton("OK") { _, _ ->
            val parkingId = input.text.toString()
            updateParking(parkingId)
        }
        inputDialog.setNegativeButton("Cancelar", null)
        inputDialog.show()
    }

    private fun updateParking(parkingId: String) {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("Parques").document(parkingId)
        docRef.get().addOnSuccessListener { document ->
            val parked = document.getBoolean("parked") ?: false
            val newParked = !parked
            val newColor = if (newParked) Color.GREEN else Color.RED
            val rect = findViewById<LinearLayout>(parkingId.hashCode())
            rect.setBackgroundColor(newColor)
            docRef.update("parked", newParked)
        }
    }




}
