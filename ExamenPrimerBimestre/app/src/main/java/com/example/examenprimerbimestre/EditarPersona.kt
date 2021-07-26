package com.example.examenprimerbimestre

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class EditarPersona : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_persona)
        val id_actual = intent.getIntExtra("id_actual",0)
        var db:Database.PersonasDatabase  = Database.PersonasDatabase.getInstance(this)
        val nombre = findViewById<TextInputLayout>(R.id.tinModificarNombrePersona)
        val apellido = findViewById<TextInputLayout>(R.id.tinModificarApellidoPersona)
        val edad = findViewById<TextInputLayout>(R.id.tinModificarEdadPersona)
        val correo = findViewById<TextInputLayout>(R.id.tinModificarCorreoPersona)
        val telefono = findViewById<TextInputLayout>(R.id.tinModificarTelefonoPersona)

        var SDao = db.personaDao
        val actual = SDao.get_id_persona(id_actual)

        actual?.observe(this, Observer{ words ->
            words?.let {
                nombre.hint = it.Nombre_persona
                apellido.hint = it.Apellido_persona
                edad.hint = it.Edad_persona.toString()
                correo.hint = it.Email_persona
                telefono.hint = it.Telefono_persona

            }
        });

        val btnModificarSerie = findViewById<Button>(R.id.btnActualizarPersona)
        btnModificarSerie.setOnClickListener {
            var nombreT = ""
            var apellidoT = ""
            var correoT= ""
            var telefonoT = ""
            var edadT = ""
            var bandera = 0

            if (nombre.editText?.text.toString() == "") {
                nombreT = nombre.hint.toString()
                nombre.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera++
            } else {
                nombre.boxBackgroundColor = Color.rgb(224, 224, 224)
                nombreT = nombre.editText?.text.toString()
                bandera++
            }

            if (apellido.editText?.text.toString() == "") {
                apellidoT = apellido.hint.toString()
                apellido.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera++
            } else {
                apellido.boxBackgroundColor = Color.rgb(224, 224, 224)
                apellidoT = apellido.editText?.text.toString()
                bandera++
            }

            if (edad.editText?.text.toString() == "") {
                edadT = edad.hint.toString()
                edad.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera ++
            } else {
                edad.boxBackgroundColor = Color.rgb(224, 224, 224)
                edadT = edad.editText?.text.toString()
                bandera++
            }

            if (correo.editText?.text.toString() == "") {
                correoT = correo.hint.toString()
                correo.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera ++
            } else {
                correo.boxBackgroundColor = Color.rgb(224, 224, 224)
                correoT = correo.editText?.text.toString()
                bandera++
            }
            if (telefono.editText?.text.toString() == "") {
                telefonoT = telefono.hint.toString()
                telefono.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera ++
            } else {
                telefono.boxBackgroundColor = Color.rgb(224, 224, 224)
                telefonoT = telefono.editText?.text.toString()
                bandera++
            }


            if (bandera == 5) {
                val persona = PersonaEntity(
                    id_Persona = id_actual,
                    Nombre_persona = nombreT,
                    Apellido_persona = apellidoT,
                    Edad_persona = Integer.parseInt(edadT) ,
                    Email_persona = correoT,
                    Telefono_persona = telefonoT
                )
                GlobalScope.launch(Dispatchers.IO) {
                    SDao.update_persona(persona)
                }
                Toast.makeText(this,"Persona actualizada corectamente", Toast.LENGTH_SHORT).show()
                this.finish()
            }
        }
    }
}