package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class Listview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)

        val arregloNumeros = arrayListOf<Int>(
            1,
            2,
            3
        )

        val adaptador = ArrayAdapter(
            this, //contexto
             android.R.layout.simple_list_item_1, //layout (visual)
             arregloNumeros //arreglo
        )
        val listViewEjemplo = findViewById<ListView>(R.id.ltv_ejemplo)
        listViewEjemplo.adapter = adaptador

        val botonAñadirNumero = findViewById<Button>(R.id.btn_anadir_numero)
        botonAñadirNumero.setOnClickListener{añadirItemsAListView(
            1,
            arregloNumeros,
            adaptador
        )}
        /*listViewEjemplo
            .setOnItemLongClickListener { adapterView, view, position, id ->
                Log.i("list-view","Dio click ${position}")
                return@setOnItemLongClickListener true
            }*/
        registerForContextMenu(listViewEjemplo)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val  inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        /*val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id*/
    }

    fun añadirItemsAListView(
        valor: Int,
        arreglo: ArrayList<Int>,
        adaptador: ArrayAdapter<Int>
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged() //actualiza la interfaz
    }


}