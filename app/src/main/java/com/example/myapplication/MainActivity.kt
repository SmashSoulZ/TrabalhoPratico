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


        //pop up definicoes
        //fazer funcionar

       val btn_filtros = findViewById<Button>(R.id.btn_filtros)

        btn_filtros.setOnClickListener {
            val dialogBinding = layoutInflater.inflate(R.layout.layout_definicoes, null)

            val myDialog = Dialog ( this)
            myDialog.setContentView(dialogBinding)

            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()
        }
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
              true
          }
          R.id.nav_filtros -> {
              Toast.makeText(this, "nav_filtros", Toast.LENGTH_SHORT).show()
              true
          }
          R.id.nav_lotacao -> {
              Toast.makeText(this, "nav_lotação", Toast.LENGTH_SHORT).show()
              true
          }
          R.id.nav_reportar -> {
              Toast.makeText(this, "nav_reportar", Toast.LENGTH_SHORT).show()
              true
          }
          R.id.nav_perfil -> {
              Toast.makeText(this, "nav_perfil", Toast.LENGTH_SHORT).show()
              startActivity(Intent(this, perfil::class.java))
              true
          }
          R.id.nav_login -> {
              Toast.makeText(this, "nav_login", Toast.LENGTH_SHORT).show()
              startActivity(Intent(this, Login::class.java))
              true
          }
          R.id.nav_registar -> {
              Toast.makeText(this, "nav_registar", Toast.LENGTH_SHORT).show()
              startActivity(Intent(this, Register::class.java))
              true
          }
            else -> {super.onOptionsItemSelected(item)}
        }
    }
}