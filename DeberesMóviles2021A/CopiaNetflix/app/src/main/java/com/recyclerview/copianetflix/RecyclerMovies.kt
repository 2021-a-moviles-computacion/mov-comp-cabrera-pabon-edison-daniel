package com.recyclerview.carouselviewexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.os.Parcelable
import com.jama.carouselviewexample.R

class RecyclerMovies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView1)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val MoviesElements: Array<Movies> = arrayOf(
            Movies("Avengers", "2019 film", R.drawable.avenger),
            Movies("Venom", "2018 film", R.drawable.venom),
            Movies("Batman Begins", "2005 film", R.drawable.batman),
            Movies("Jumanji", "2019 film", R.drawable.jumanji),
            Movies("Soy Leyenda", "2007 film", R.drawable.i_am_legend),
            Movies("Piratas del Caribe", "2003 film", R.drawable.priates),
            Movies("Avatar", "2009 film", R.drawable.avatar),
            Movies("Mad Max", "2015 film", R.drawable.mad_max),
            Movies("Joker", "2019 film", R.drawable.joker_2019),
            Movies("SuperCool", "2007 film", R.drawable.superbad_2007)
        )
        val myMovieAdapter = AdapterMovies()
        myMovieAdapter.AdapterMovies(MoviesElements)
        recyclerView.adapter = myMovieAdapter
    }

    fun irActividad(
        clase: Class<*>,
        parametros: ArrayList<Pair<String, *>>? = null,
        codigo: Int? = null
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        parametros?.forEach {
            val nombreVariable = it.first
            val valorVariable: Any? = it.second

            when (it.second) {
                is String -> {
                    valorVariable as String
                    intentExplicito.putExtra(nombreVariable, valorVariable)
                }
                is Parcelable -> {
                    valorVariable as Parcelable
                    intentExplicito.putExtra(nombreVariable, valorVariable)
                }
                is Int -> {
                    valorVariable as Int
                    intentExplicito.putExtra(nombreVariable, valorVariable)
                }
                else -> {
                    valorVariable as String
                    intentExplicito.putExtra(nombreVariable, valorVariable)
                }
            }


        }

        if (codigo != null) {
            startActivityForResult(intentExplicito, codigo)
        } else {
            startActivity(intentExplicito)

        }
    }
}