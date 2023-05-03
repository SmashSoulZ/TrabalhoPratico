package com.example.myapplication.dataclasses

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class LayoutDefinicoesActivity : AppCompatActivity() {

    private val PREFS_DEFINICOES = "MyPrefs"
    private lateinit var sharedPref: SharedPreferences
    private lateinit var switch1: Switch
    private lateinit var switch2: Switch
    private lateinit var switch3: Switch
    private lateinit var btnSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_definicoes)

        // Obter uma instância do SharedPreferences
        sharedPref = getSharedPreferences(PREFS_DEFINICOES, Context.MODE_PRIVATE)

        // Associar as variáveis com os Switches e o botão da layout XML
        switch1 = findViewById(R.id.switch1)
        switch2 = findViewById(R.id.switch2)
        switch3 = findViewById(R.id.switch3)
        btnSalvar = findViewById(R.id.btnSalvar)

        // Configurar os listeners dos Switches
        switch1.setOnCheckedChangeListener { _, isChecked ->
            sharedPref.edit().putBoolean("switch1", isChecked).apply()
        }
        switch2.setOnCheckedChangeListener { _, isChecked ->
            sharedPref.edit().putBoolean("switch2", isChecked).apply()
        }
        switch3.setOnCheckedChangeListener { _, isChecked ->
            sharedPref.edit().putBoolean("switch3", isChecked).apply()
        }

        // Configurar a posição inicial dos Switches
        switch1.isChecked = sharedPref.getBoolean("switch1", false)
        switch2.isChecked = sharedPref.getBoolean("switch2", false)
        switch3.isChecked = sharedPref.getBoolean("switch3", false)

        // Configurar o listener de clique no botão "Salvar"
        btnSalvar.setOnClickListener {
            // Atualizar o SharedPreferences com os valores dos Switches
            sharedPref.edit().apply {
                putBoolean("switch1", switch1.isChecked)
                putBoolean("switch2", switch2.isChecked)
                putBoolean("switch3", switch3.isChecked)
                apply()
            }


            val btnSave = findViewById<Button>(R.id.btnSalvar)
            btnSave.setOnClickListener {
                val editor = sharedPref.edit()
                editor.putBoolean("switch1", switch1.isChecked)
                editor.putBoolean("switch2", switch2.isChecked)
                editor.putBoolean("switch3", switch3.isChecked)
                editor.apply()

            }
            // Mostrar uma mensagem para o usuário indicando que as configurações foram salvas
            Toast.makeText(this, "Configurações salvas com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }
}


