package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Alo Alo Teste
        // O rogerio esteve aquiii

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val navigationIcon = findViewById<ImageView>(R.id.menu)
        navigationIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        fun setupDrawerContent(drawerLayout: DrawerLayout) {
            val navigationView = findViewById<NavigationView>(R.id.navigation_view)
            navigationView.setNavigationItemSelectedListener { menuItem ->
                // Adicione aqui o código para lidar com a seleção do item do menu lateral
                drawerLayout.closeDrawers()
                true
            }
        }
        setupDrawerContent(drawerLayout)

        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.inflateMenu(R.menu.menu_navigation)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_1 -> {
                    // Adicione aqui o código para lidar com a seleção do item 1
                    true
                }
                R.id.menu_item_2 -> {
                    // Adicione aqui o código para lidar com a seleção do item 2
                    true
                }
                R.id.menu_item_3 -> {
                    // Adicione aqui o código para lidar com a seleção do item 3
                    true
                }
                else -> false
            }
        }
    }
}