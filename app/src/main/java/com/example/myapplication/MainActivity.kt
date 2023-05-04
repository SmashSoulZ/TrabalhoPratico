package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.*

class MainActivity : AppCompatActivity() {

    val PREFS_FILTRAR = "MyPrefs"
    private lateinit var sharedPref: SharedPreferences
    private lateinit var switch1: Switch
    private lateinit var switch2: Switch
    private lateinit var switch3: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
            else -> {super.onOptionsItemSelected(item)}
        }
    }
}