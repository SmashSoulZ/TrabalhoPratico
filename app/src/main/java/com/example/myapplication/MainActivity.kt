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
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast

class MainActivity : AppCompatActivity() {


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
          R.id.nav_filtros -> {
              val dialogBinding = layoutInflater.inflate(R.layout.layout_definicoes, null)

              val myDialog = Dialog ( this)
              myDialog.setContentView(dialogBinding)

              myDialog.setCancelable(true)
              myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
              myDialog.show()
              true
          }
          R.id.nav_lotacao -> {
              Toast.makeText(this, "nav_lotação", Toast.LENGTH_SHORT).show()
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