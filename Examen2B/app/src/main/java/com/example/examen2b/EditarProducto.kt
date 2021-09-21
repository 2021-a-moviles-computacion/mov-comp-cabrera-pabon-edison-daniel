package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.examen2b.dto.FirebaseProductoDTO
import com.google.firebase.firestore.FirebaseFirestore

class EditarProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_producto)

        val producto = intent.getParcelableExtra<FirebaseProductoDTO>("producto")

        Log.i("list-view", "Producto seteado ${producto!!}")

        val txt_nombre_ingreso = findViewById<TextView>(R.id.txt_nombrep_editar)
        val txt_precio_ingreso = findViewById<TextView>(R.id.txt_precioep_editar)
        val txt_disp_ingreso = findViewById<TextView>(R.id.txt_disp_editar)
        val txt_fecha_ing_ingreso = findViewById<TextView>(R.id.txt_fec_ing_editar)
        val txt_cantidad_ingreso = findViewById<TextView>(R.id.txt_cantidadep_editar)
        val txt_id_producto = findViewById<TextView>(R.id.txt_id_producto_editar)
        val txt_longitud_pr = findViewById<TextView>(R.id.txt_longitud_pe)
        val txt_latitud_pr = findViewById<TextView>(R.id.txt_latitud_pe)
        val txt_id_persona_ingreso = findViewById<TextView>(R.id.txt_id_persona_ap)


        txt_id_producto.text = producto.id.toString()
        txt_nombre_ingreso.text = producto.nombre_producto
        txt_precio_ingreso.text = producto.precio.toString()
        txt_disp_ingreso.text = producto.disponibilidad.toString()
        txt_fecha_ing_ingreso.text = producto.fechaIngreso
        txt_cantidad_ingreso.text = Integer.parseInt(producto.cantidad.toString()).toString()
        txt_longitud_pr.text = producto.longitud.toString()
        txt_latitud_pr.text = producto.latitud.toString()
        txt_id_persona_ingreso.text = producto.idPersona.toString()

        val bton_editar_empleado = findViewById<Button>(R.id.btn_actualizarep)
        bton_editar_empleado.setOnClickListener {

            val nuevoEmpresa = hashMapOf<String, Any>(
                "Nombre" to txt_nombre_ingreso.text.toString(),
                "Precio" to txt_precio_ingreso.text.toString().toDouble(),
                "Disponibilidad" to txt_disp_ingreso.text.toString(),
                "Fecha de Ingreso" to txt_fecha_ing_ingreso.text.toString(),
                "Cantidad" to txt_cantidad_ingreso.text.toString().toInt(),
                "latitud" to txt_latitud_pr.text.toString().toDouble(),
                "longitud" to txt_longitud_pr.text.toString().toDouble(),
                "idPersona" to txt_id_persona_ingreso.text.toString(),
            )

            val db = FirebaseFirestore.getInstance()
            db.collection("productos")
                .document(producto.id!!)
                .set(
                    nuevoEmpresa
                )
                .addOnSuccessListener {
                    txt_nombre_ingreso.text = ""
                    txt_precio_ingreso.text = ""
                    txt_disp_ingreso.text = ""
                    txt_cantidad_ingreso.text = ""
                    txt_fecha_ing_ingreso.text = ""
                    txt_id_persona_ingreso.text = ""
                    txt_latitud_pr.text = ""
                    txt_longitud_pr.text = ""
                }

            abrirActividad(Persona::class.java)

        }


        val btn_canclar_editar = findViewById<Button>(R.id.btn_cancelarep)
        btn_canclar_editar.setOnClickListener{
            abrirActividad(Persona::class.java)
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