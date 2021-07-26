package com.example.examenprimerbimestre

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.examenpokentrpp1.activEntren.AdaptadorPersonas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    var idItemSeleccionado = 0
    lateinit var adaptador : AdaptadorPersonas
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var db:Database.PersonasDatabase  = Database.PersonasDatabase.getInstance(this)
        val list = findViewById<ListView>(R.id.lst_personas)
        adaptador = AdaptadorPersonas(this)
        list.adapter = adaptador
        var SDao = db.personaDao
        val lista = SDao.AllPersonas()


        lista.observe(this, Observer{ words ->
            // Update the cached copy of the words in the adapter.
            words?.let {
                adaptador.update(it)
            }
        });

        registerForContextMenu((list))
        val btnIngresarPersona = findViewById<Button>(R.id.btnIngresarPersona)

        btnIngresarPersona.setOnClickListener {
            irActividad(IngresarPersona::class.java)
        }

        list.setOnItemClickListener{parent, view, position, id ->
            val actual = adaptador.getItem(position) as PersonaEntity
            irActividad(Productos::class.java,arrayListOf(Pair("id_actual",actual.id_Persona)))
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
        val SDao = db.personaDao
        val list = findViewById<ListView>(R.id.lst_personas)

        return when (item?.itemId){
            R.id.mi_editar -> {
                val actual = adaptador.getItem(idItemSeleccionado) as PersonaEntity
                irActividad(EditarPersona::class.java,arrayListOf(Pair("id_actual",actual.id_Persona)))
                return true
            }
            R.id.mi_eliminar -> {
                val actual = adaptador.getItem(idItemSeleccionado) as PersonaEntity
                GlobalScope.launch (Dispatchers.IO) {
                    SDao.delete_persona(actual.Nombre_persona,actual.Apellido_persona)
                }
                Toast.makeText(this,"Persona eliminada corectamente", Toast.LENGTH_SHORT).show()
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