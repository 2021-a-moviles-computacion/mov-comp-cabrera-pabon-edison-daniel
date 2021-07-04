package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class Listview : AppCompatActivity() {
    var posicionItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)

        /*val arregloNumeros = arrayListOf<BEntrenador>(
           BEntrenador("Adrian","a@a.com"),
            BEntrenador("Vicente","v@a.com"),
            BEntrenador("Carolina","c@a.com"),
        )*/
        val arregloNumeros = BBaseDatosMemoria.arregloBEntrenador
        val adaptador = ArrayAdapter(
            this, //contexto
             android.R.layout.simple_list_item_1, //layout (visual)
             arregloNumeros //arreglo
        )
        val listViewEjemplo = findViewById<ListView>(R.id.ltv_ejemplo)
        listViewEjemplo.adapter = adaptador

        val botonAñadirNumero = findViewById<Button>(R.id.btn_anadir_numero)
        botonAñadirNumero.setOnClickListener{añadirItemsAListView(
            BEntrenador("Prueba","d@d.com"),
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
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
        Log.i("lis-view","List view ${posicionItemSeleccionado}")
        Log.i("lis-view","Entrenador ${BBaseDatosMemoria.arregloBEntrenador[id]}")

        /*val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id*/
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            //Editar
            R.id.mi_editar -> {
                Log.i(
                    "list-view", "Editar ${
                        BBaseDatosMemoria.arregloBEntrenador[posicionItemSeleccionado]
                    }"
                )
                return true
            }
            //Eliminar
            R.id.mi_eliminar -> {
                Log.i(
                    "list-view", "Eliminar ${
                        BBaseDatosMemoria.arregloBEntrenador[posicionItemSeleccionado]
                    }"
                )
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    /*fun añadirItemsAListView(
        valor: Int,
        arreglo: ArrayList<Int>,
        adaptador: ArrayAdapter<Int>
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged() //actualiza la interfaz
    }*/

    fun añadirItemsAListView(
        valor: BEntrenador,
        arreglo: ArrayList<BEntrenador>,
        adaptador: ArrayAdapter<BEntrenador>
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged() //actualiza la interfaz
    }


}