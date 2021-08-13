package com.recyclerview.copianetflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_start_carousel.*

class Principal : AppCompatActivity() {

    private val movies = arrayListOf(R.drawable.venom, R.drawable.avenger, R.drawable.i_am_legend, R.drawable.priates,R.drawable.jumanji)
    private val moviesTitles = arrayListOf("Venom", "Avengers", "Soy Leyenda", "Piratas del Caribe", "Jumanji")
    private val series = arrayListOf(R.drawable.elite_2018, R.drawable.fear_walking_dead_2015, R.drawable.game_of_thrones_2011, R.drawable.the_walking_dead_2012, R.drawable.the_big_bang_theory_2007)
    private val seriesTitles = arrayListOf("Elite", "Fear the Walking Dead", "Game of Thrones", "The Walking Dead", "The Big Bang Theory")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_carousel)

        carouselView1.apply {
            size = movies.size
            resource = R.layout.start_carousel_movies_item
            scaleOnScroll = true
            spacing = 50
            hideIndicator(true)
            setCarouselViewListener { view, position ->
                val imageView = view.findViewById<ImageView>(R.id.imageView)
                imageView.setImageDrawable(resources.getDrawable(movies[position]))
                imageView.setOnClickListener {irActividad(RecyclerMovies::class.java) }
                val textView = view.findViewById<TextView>(R.id.textViewTitle)
                textView.text = moviesTitles[position]
            }
            show()
        }

        carouselView2.apply {
            val trendingSeries = series + movies
            val trendingTitle = seriesTitles + moviesTitles

            size = series.size
            resource = R.layout.start_carousel_series_item
            scaleOnScroll = true
            spacing = 50
            hideIndicator(true)
            setCarouselViewListener { view, position ->
                val imageView = view.findViewById<ImageView>(R.id.imageView)
                imageView.setImageDrawable(resources.getDrawable(trendingSeries[position]))
                imageView.setOnClickListener {irActividad(RecyclerSeries::class.java) }
                val textView = view.findViewById<TextView>(R.id.textViewTitle)
                textView.text = trendingTitle[position]
            }
            show()
        }

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
