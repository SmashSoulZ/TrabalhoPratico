package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class Report : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        db = FirebaseFirestore.getInstance()

        val reportButton = findViewById<Button>(R.id.btnSubmit)
        reportButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.email).text.toString().trim()
            val report = findViewById<EditText>(R.id.report).text.toString().trim()

            if (email.isNotEmpty() && report.isNotEmpty()) {
                val userReport = hashMapOf(
                    "email" to email,
                    "report" to report
                )

                db.collection("Report")
                    .add(userReport)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Report enviado com sucesso!", Toast.LENGTH_SHORT).show()
                        findViewById<EditText>(R.id.email).setText("")
                        findViewById<EditText>(R.id.report).setText("")
                    }
                    .addOnFailureListener { e ->
                        Log.e("parque", "Erro ao enviar o report", e)
                        Toast.makeText(this, "Erro ao enviar o report", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
