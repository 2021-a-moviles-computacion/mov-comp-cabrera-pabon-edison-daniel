package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.examen2b.dto.FirebasePersonaDTO
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearProducto : AppCompatActivity() {
    var posicionItemSelecionado = 0
    var idPersona: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_producto)

        val persona = intent.getParcelableExtra<FirebasePersonaDTO>("persona")

        idPersona = persona!!.id

        val idPersona = findViewById<TextView>(R.id.txt_id_persona_cp)
        idPersona.text = persona.id.toString()


        val bt_ingresar_producto = findViewById<Button>(R.id.btn_guardarp)

        bt_ingresar_producto.setOnClickListener {
            crearProducto()
            abrirActividad(Persona::class.java)
        }

        val btn_cancelar = findViewById<Button>(R.id.btn_cancelarp)
        btn_cancelar.setOnClickListener {
            abrirActividad(Persona::class.java)
        }
    }

    fun crearProducto(){
        val nombre_producto = findViewById<TextView>(R.id.txt_nombre_producto)
        val precio = findViewById<TextView>(R.id.txt_precio)
        val disp = findViewById<TextView>(R.id.txt_disponibilidad)
        val fechaIng = findViewById<TextView>(R.id.txt_fec_ing)
        val cantidad = findViewById<TextView>(R.id.txt_cantidad)
        val idPersona = findViewById<TextView>(R.id.txt_id_persona_cp)
        val latProducto = findViewById<TextView>(R.id.txt_latitud)
        val longProducto = findViewById<TextView>(R.id.txt_longitud)

        val ingresoNombre = nombre_producto.text.toString()
        val ingresoPrecio = precio.text.toString().toDouble()
        val ingresoDis = disp.text.toString()
        val ingresoFI = fechaIng.text.toString()
        val ingresoCantidad = Integer.parseInt(cantidad.text.toString())
        val latProductoIngreso = latProducto.text.toString().toDouble()
        val longProductoIngreso = longProducto.text.toString().toDouble()
        val idPersonaIngreso = this.idPersona.toString()

        val nuevoProducto = hashMapOf<String, Any>(
            "Nombre" to ingresoNombre,
            "Precio" to ingresoPrecio,
            "Disponibilidad" to ingresoDis,
            "Fecha de Ingreso" to ingresoFI,
            "Cantidad" to ingresoCantidad,
            "latitud" to latProductoIngreso,
            "longitud" to longProductoIngreso,
            "idPersona" to idPersonaIngreso,
        )
        val db = Firebase.firestore
        val referencia = db.collection("productos")

        referencia
            .add(nuevoProducto)
            .addOnSuccessListener {
                nombre_producto.text = ""
                precio.text = ""
                disp.text = ""
                fechaIng.text = ""
                cantidad.text = ""
                idPersona.text = ""
                latProducto.text = ""
                longProducto.text = ""
            }
            .addOnFailureListener {
                Log.i("firestore-producto", "no se pudo cargar los datos al firestore ")
            }

    }

    fun abrirActividad(
        clase: Class<*>,
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }


}