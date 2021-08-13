package com.recyclerview.copianetflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class RecyclerSeries : AppCompatActivity(), AdapterSeries.SeriesListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.series)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView2)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val SeriesElements: Array<Series> = arrayOf(
            Series("La Casa de Papel", "2017 film", R.drawable.la_casa_de_papel_2017),
            Series("Sabrina", "2018 film", R.drawable.sabrina_2018),
            Series("Sex Education", "2018 film", R.drawable.sex_education_2018),
            Series("Fear the Walking Dead", "2015 film", R.drawable.fear_walking_dead_2015),
            Series("Game of Thrones", "2011 film", R.drawable.game_of_thrones_2011),
            Series("Big Band Theory", "2007 film", R.drawable.the_big_bang_theory_2007),
            Series("The Society", "2019 film", R.drawable.the_society_2019),
            Series("Elite", "2018 film", R.drawable.elite_2018),
            Series("The Walking Dead", "2012 film", R.drawable.the_walking_dead_2012),
            Series("Vickingos", "2013 film", R.drawable.vickingos_2013),
        )
        val mySerieAdapter = AdapterSeries(SeriesElements,this)
        recyclerView.adapter = mySerieAdapter
    }

    override fun onItemClick(series: String) {
        Toast.makeText(baseContext, series, Toast.LENGTH_SHORT)
            .show()
        Snackbar.make(findViewById(android.R.id.content), series, Snackbar.LENGTH_LONG).show()
        val intent = Intent(baseContext, VistaSerie::class.java)
        startActivity(intent)
    }
}