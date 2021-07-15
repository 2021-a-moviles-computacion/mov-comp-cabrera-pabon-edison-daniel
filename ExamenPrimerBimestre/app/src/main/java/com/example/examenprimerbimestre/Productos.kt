package com.example.examenprimerbimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Productos : AppCompatActivity() {
    var idItemSeleccionado = 0
    lateinit var adaptador : AdaptadorProducto
    var id_Persona = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reparto)
        id_Persona = intent.getIntExtra("id_actual",0)

        var db:Database.PersonasDatabase  = Database.PersonasDatabase.getInstance(this)
        val list = findViewById<ListView>(R.id.lst_actores)
        adaptador = AdaptadorProducto(this)
        list.adapter = adaptador
        var ADao = db.productoDao
        val lista = ADao.AllProductos(id_Persona)

        lista.observe(this, Observer{ words ->
            words?.let {
                adaptador.update(it)
            }
        });

        registerForContextMenu((list))
        val btnCrearActor = findViewById<Button>(R.id.btnCrearActor)

        btnCrearActor.setOnClickListener {
            irActividad(IngresarProducto::class.java,arrayListOf(Pair("id_persona",id_Persona)))
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val db:Database.PersonasDatabase  = Database.PersonasDatabase.getInstance(this)
        val ADao = db.productoDao
        val list = findViewById<ListView>(R.id.lst_actores)

        return when (item?.itemId){
            R.id.mi_editar -> {
                val actual = adaptador.getItem(idItemSeleccionado) as ProductoEntity
                irActividad(EditarProducto::class.java,arrayListOf(Pair("id_actual",actual.id_Producto),Pair("id_persona",id_Persona)))
                return true
            }
            R.id.mi_eliminar -> {
                val actual = adaptador.getItem(idItemSeleccionado) as ProductoEntity
                GlobalScope.launch (Dispatchers.IO) {
                    ADao.delete_producto(actual.Nombre_producto)
                }
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun irActividad(
        clase: Class<*>,
        parametros:ArrayList<Pair<String,*>>? = null,
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