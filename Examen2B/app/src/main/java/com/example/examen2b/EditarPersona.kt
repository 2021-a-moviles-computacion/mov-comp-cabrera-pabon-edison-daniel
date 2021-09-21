package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.examen2b.dto.FirebasePersonaDTO
import com.google.firebase.firestore.FirebaseFirestore

class EditarPersona : AppCompatActivity() {
    var idPersona: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_persona)

        val persona = intent.getParcelableExtra<FirebasePersonaDTO>("persona")
        idPersona = persona!!.id


        val txtID = findViewById<TextView>(R.id.txt_id_persona_editar)
        val txtNombre = findViewById<TextView>(R.id.txt_disp_editar)
        val txtApellido = findViewById<TextView>(R.id.txt_apellido_editar)
        val txtEdad = findViewById<TextView>(R.id.txt_edad_editar)
        val txtEmail = findViewById<TextView>(R.id.txt_email_editar)
        val txtTelefono = findViewById<TextView>(R.id.txt_telefono_editar)

        txtID.text = persona!!.id
        txtNombre.text = persona!!.nombre
        txtApellido.text = persona!!.apellido
        txtEdad.text = persona!!.edad.toString()
        txtEmail.text = persona!!.email
        txtTelefono.text = persona!!.telefono

        val btn_editar_empresa = findViewById<Button>(R.id.btn_actualizar_persona)
        btn_editar_empresa.setOnClickListener {

            val actualizarUsuario = hashMapOf<String, Any>(
                "Nombre" to txtNombre.text.toString(),
                "Apellido" to txtApellido.text.toString(),
                "Edad" to txtEdad.text.toString().toInt(),
                "Correo Electrónico" to txtEmail.text.toString(),
                "Teléfono" to txtTelefono.text.toString()
            )

            val db = FirebaseFirestore.getInstance()
            db.collection("personas")
                .document(idPersona!!)
                .set(
                    actualizarUsuario
                )
                .addOnSuccessListener {
                    txtNombre.text = ""
                    txtApellido.text = ""
                    txtEdad.text = ""
                    txtEmail.text = ""
                    txtTelefono.text = ""
                }

            abrirActividad(Persona::class.java)
        }


        val btn_cancelar = findViewById<Button>(R.id.btn_cancelare)
        btn_cancelar.setOnClickListener {
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