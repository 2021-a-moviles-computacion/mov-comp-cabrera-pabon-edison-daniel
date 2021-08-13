package com.recyclerview.copianetflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class RecyclerMovies : AppCompatActivity(), AdapterMovies.MoviesListener {
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

        val myMovieAdapter = AdapterMovies(MoviesElements,this)
        recyclerView.adapter = myMovieAdapter
    }

    override fun onItemClick(movies: String) {

        Toast.makeText(baseContext, movies, Toast.LENGTH_SHORT)
            .show()

        Snackbar.make(findViewById(android.R.id.content), movies, Snackbar.LENGTH_LONG).show()

        val intent = Intent(baseContext, VistaMovie::class.java)
        startActivity(intent)
    }
}