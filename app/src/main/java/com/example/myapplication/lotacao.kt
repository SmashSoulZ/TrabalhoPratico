package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class lotacao : AppCompatActivity() {
    private lateinit var barChart: BarChart
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
                Toast.makeText(this, "nav_listagem", Toast.LENGTH_SHORT).show()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lotacao)

        barChart = findViewById(R.id.bar_chart)

        // Configuração dos dados para o gráfico
        val entries = mutableListOf<BarEntry>()
        entries.add(BarEntry(1f, 10f))
        entries.add(BarEntry(2f, 70f))
        entries.add(BarEntry(3f, 20f))
        entries.add(BarEntry(4f, 45f))
        entries.add(BarEntry(5f, 100f))
        entries.add(BarEntry(6f, 96f))
        entries.add(BarEntry(7f,20f))
        val dataSet = BarDataSet(entries, "Exemplo de dados")
        dataSet.color = Color.BLUE
        val data = BarData(dataSet)

        // Configuração das opções do gráfico
        barChart.data = data
        barChart.setFitBars(true)
        barChart.animateY(1000)
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = false
        barChart.axisRight.isEnabled = false
        barChart.axisLeft.axisMinimum = 0f
        barChart.xAxis.axisMinimum = 0f
        barChart.xAxis.axisMaximum = 8f

        // Formatação dos rótulos do eixo x para mostrar os dias da semana
        val days = listOf("","Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab")
        val formatter = IndexAxisValueFormatter(days)
        barChart.xAxis.granularity = 1f
        barChart.xAxis.valueFormatter = formatter
    }
}
