package com.example.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val MoviesElements: Array<Movies> = arrayOf(
            Movies("Avengers", "2019 film", R.drawable.avenger),
            Movies("Venom", "2018 film", R.drawable.venom),
            Movies("Batman Begins", "2005 film", R.drawable.batman),
            Movies("Jumanji", "2019 film", R.drawable.jumanji),
            Movies("Good Deeds", "2012 film", R.drawable.good_deeds),
            Movies("Hulk", "2003 film", R.drawable.hulk),
            Movies("Avatar", "2009 film", R.drawable.avatar)
        )
        val myMovieAdapter = AdapterMovies()
        myMovieAdapter.AdapterMovies(MoviesElements)
        recyclerView.adapter = myMovieAdapter
    }
}