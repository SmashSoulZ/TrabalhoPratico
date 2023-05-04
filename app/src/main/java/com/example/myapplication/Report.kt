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
import android.widget.Toast
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class Report : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
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
                Toast.makeText(this, "nav_lotação", Toast.LENGTH_SHORT).show()
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
            R.id.nav_logout -> {
                FirebaseAuth.getInstance().signOut() // sign out the user from Firebase Authentication
                startActivity(Intent(this, Login::class.java)) // redirect to Login Activity
                finish() // finish current activity
                true
            }
            else -> {super.onOptionsItemSelected(item)}
        }
    }
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
                    .addOnSuccessListener { documentReference ->
                        val documentId = documentReference.id
                        userReport["id"] = documentId // Add the document ID to the userReport map
                        db.collection("Report").document(documentId).set(userReport)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Report enviado com sucesso!", Toast.LENGTH_SHORT).show()
                                findViewById<EditText>(R.id.email).setText("")
                                findViewById<EditText>(R.id.report).setText("")
                            }
                            .addOnFailureListener { e ->
                                Log.e("parque", "Erro ao enviar o report", e)
                                Toast.makeText(this, "Erro ao enviar o report", Toast.LENGTH_SHORT).show()
                            }
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
