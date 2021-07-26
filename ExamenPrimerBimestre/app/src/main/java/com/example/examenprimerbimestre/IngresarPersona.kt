package com.example.examenprimerbimestre

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*


class IngresarPersona : AppCompatActivity() {
    private lateinit var PDao: PersonaDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_persona)

        var db: Database.PersonasDatabase = Database.PersonasDatabase.getInstance(this)

        PDao = db.personaDao
        val button = findViewById<Button>(R.id.btnIngresoPersona)
        var nombre = findViewById<TextInputLayout>(R.id.tinNombre)
        val apellido = findViewById<TextInputLayout>(R.id.tinApellido)
        val edad = findViewById<TextInputLayout>(R.id.tinEdad)
        val email = findViewById<TextInputLayout>(R.id.tinCorreo)
        val telefon = findViewById<TextInputLayout>(R.id.tinTelefono)

        button.setOnClickListener {
            var bandera = 0

            if (nombre.editText?.text.toString() == "") {
                nombre.boxBackgroundColor = Color.RED
                nombre.hint = "Ingrese el nombre de la persona"
                bandera = 0
            } else {
                nombre.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera++
            }

            if (apellido.editText?.text.toString() == "") {
                apellido.boxBackgroundColor = Color.RED
                apellido.hint = "Ingrese el apellido de la persona"
                bandera = 0
            } else {
                apellido.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera++
            }

            if (edad.editText?.text.toString() == "") {
                edad.boxBackgroundColor = Color.RED
                edad.hint = "Ingrese la edad de la persona"
                bandera = 0
            } else {
                edad.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera++
            }

            if (email.editText?.text.toString() == "") {
                email.boxBackgroundColor = Color.RED
                email.hint = "Ingrese el Correo de la persona"
                bandera = 0
            } else {
                email.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera++
            }

            if (telefon.editText?.text.toString() == "") {
                telefon.boxBackgroundColor = Color.RED
                telefon.hint = "Ingrese el Telefono de la persona"
                bandera = 0
            } else {
                telefon.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera++
            }


            if (bandera == 5) {
                val persona = PersonaEntity(
                    Nombre_persona = nombre.editText?.text.toString(),
                    Apellido_persona = apellido.editText?.text.toString(),
                    Edad_persona = Integer.parseInt(edad.editText?.text.toString()),
                    Email_persona = email.editText?.text.toString(),
                    Telefono_persona = telefon.editText?.text.toString()
                )
                GlobalScope.launch(Dispatchers.IO) {
                    PDao.insert_persona(persona)
                }
                Toast.makeText(this,"Persona ingresada corectamente", Toast.LENGTH_SHORT).show()
                this.finish()
            }
        }


    }
}