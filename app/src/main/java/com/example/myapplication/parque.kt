package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout


class parque : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_principal)

        val gridLayout = findViewById<GridLayout>(R.id.grid_layout)

        val values = arrayOf(1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1)

        for (i in 0 until values.size) {
            val rect = View(this).apply {
                id = View.generateViewId()
                setBackgroundResource(R.drawable.border)
                setBackgroundColor(if (values[i] == 0) Color.RED else Color.GREEN)
                layoutParams = GridLayout.LayoutParams().apply {
                    width = resources.getDimensionPixelSize(R.dimen.rect_width)
                    height = resources.getDimensionPixelSize(R.dimen.rect_height)

                }
            }
            gridLayout.addView(rect)
        }

    }
}
