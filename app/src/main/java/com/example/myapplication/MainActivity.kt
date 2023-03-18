package com.example.myapplication

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow

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
}