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

class Report : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
    }

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
}