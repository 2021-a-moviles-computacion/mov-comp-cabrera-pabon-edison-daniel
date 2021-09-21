package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.examen2b.dto.FirebasePersonaDTO
import com.example.examen2b.dto.FirebaseProductoDTO
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Producto : AppCompatActivity() {
    var posiconElementoSeleccionado = 0
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 400
    var idPersona: String? = ""
    var personaSeleccionada: FirebaseProductoDTO? = null
    var adpatador: ArrayAdapter<FirebaseProductoDTO>? = null
    var arregloProductos = arrayListOf<FirebaseProductoDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        val persona = intent.getParcelableExtra<FirebasePersonaDTO>("persona")
        idPersona = persona!!.id
        val idenPersona = findViewById<TextView>(R.id.txt_nombre_persona_producto)
        idenPersona.text = persona.nombre


        cargarProducto(idPersona!!)
        adpatador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloProductos

        )

        val listViewProductos = findViewById<ListView>(R.id.list_view_productos)
        listViewProductos.adapter = adpatador
        listViewProductos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long)
            {
                personaSeleccionada = arregloProductos[position]
            }

            override fun onNothingSelected(
                p0: AdapterView<*>?)
            {
                Log.i("firestore-persona", "No ha seleccionado ningun item")
            }
        }


        registerForContextMenu(listViewProductos)

        val btnnuevoProducto = findViewById<Button>(R.id.btn_producto_nuevo)
        btnnuevoProducto.setOnClickListener {
            abrirActiviad(CrearProducto::class.java, persona!!)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menuproducto,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        posiconElementoSeleccionado = id

        Log.i("list-view","onCreate ${id}")

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var selproducto = arregloProductos[posiconElementoSeleccionado]
        return when(item?.itemId){
            // Editar
            R.id.editar_producto -> {
                Log.i("list-view", "Editar ${selproducto} ")
                abrirActividadProductos(EditarProducto::class.java, selproducto)
                return true
            }
            //Eliinar
            R.id.eliminar_producto -> {
                Log.i("list-view", "Eliminar ${selproducto} ")
                val db = FirebaseFirestore.getInstance()
                db.collection("productos")
                    .document(selproducto.id!!)
                    .delete()
                    .addOnSuccessListener {
                        adpatador?.remove(adpatador!!.getItem(posiconElementoSeleccionado))
                        adpatador?.notifyDataSetChanged()
                    }

                return true
            }
            R.id.ver_mapa -> {
                abrirActividadProductos(Geo::class.java, selproducto)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadProductos(
        clase: Class<*>,
        producto: FirebaseProductoDTO
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("producto", producto)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun abrirActiviad(
        clase: Class<*>,
        persona: FirebasePersonaDTO
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("persona", persona)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }


    fun cargarProducto(idPersona: String){
        val db = Firebase.firestore
        val referencia = db.collection("productos")

        referencia
            .whereEqualTo("idPersona", idPersona)
            .get()
            .addOnSuccessListener {
                for (document in it){

                    var producto = document.toObject(FirebaseProductoDTO::class.java)
                    producto!!.id = document.id
                    producto!!.nombre_producto = document.get("Nombre").toString()
                    producto!!.precio = document.getDouble("Precio")
                    producto!!.disponibilidad = document.get("Disponibilidad").toString()
                    producto!!.fechaIngreso = document.get("Fecha de Ingreso").toString()
                    producto!!.cantidad = document.getLong("Cantidad")!!.toInt()
                    producto!!.latitud = document.getDouble("latitud")
                    producto!!.longitud = document.getDouble("longitud")
                    producto!!.idPersona = document.get("idPersona").toString()

                    arregloProductos.add(producto)
                    adpatador?.notifyDataSetChanged()

                }
            }
            .addOnFailureListener {
                Log.i("NO INGRESO AL ADD ON SUCCES FILE LISTENER  ", "${idPersona}")
            }

    }
}