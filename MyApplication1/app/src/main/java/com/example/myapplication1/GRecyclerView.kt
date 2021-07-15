package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {
    var totalLikes = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)

        val listaEntrenador = arrayListOf<BEntrenador>()
        val ligaPokemon = DLigas("Kanto","Liga Kanto")
        listaEntrenador.add(
            BEntrenador(
                "Vicente",
                "1718137159",
                ligaPokemon
            )
        )
        listaEntrenador.add(
            BEntrenador(
                "Edison",
                "1725866170",
                ligaPokemon
            )
        )
        val recyclerViewEntrenador = findViewById<RecyclerView>(
            R.id.rv_entrenadores
        )
        //this.iniciarRecyclerView()
        iniciarRecyclerView(
            listaEntrenador,
            this,
            recyclerViewEntrenador
        )

    }

    fun iniciarRecyclerView(
        lista:List<BEntrenador>,
        actividad: GRecyclerView,
        recyclerView: RecyclerView
    ){
        val adaptador = FRecyclerViewAdaptadorNombreCedula(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)

    }

    fun aumentarTotalLikes(){
        totalLikes = totalLikes + 1
        val textView = findViewById<TextView>(
            R.id.tv_total_likes
        )
        textView.text = totalLikes.toString()
    }
}