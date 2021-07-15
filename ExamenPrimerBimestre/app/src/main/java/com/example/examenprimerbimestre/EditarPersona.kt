package com.example.examenprimerbimestre

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
        val nombre = findViewById<TextInputLayout>(R.id.tinModificarNombreSerie)
        val apellido = findViewById<TextInputLayout>(R.id.tinModificarClasificacionSerie)
        val edad = findViewById<TextInputLayout>(R.id.tinModificarAlAire)

        var SDao = db.personaDao
        val actual = SDao.get_id_persona(id_actual)

        actual?.observe(this, Observer{ words ->
            // Update the cached copy of the words in the adapter.
            words?.let {
                nombre.hint = it.Nombre_persona
                apellido.hint = it.Apellido_persona
                edad.hint = it.Edad_persona.toString()
                /*if(it.Al_aire_serie){
                    edad.hint = "S"
                } else {
                    edad.hint = "N"
                }*/
            }
        });

        val btnModificarSerie = findViewById<Button>(R.id.btnModificarSerie)
        btnModificarSerie.setOnClickListener {
            var nombreT= ""
            var apellidoT = ""
            var edadT = ""
            var bandera = 0

            if (nombre.editText?.text.toString() == "") {
                nombreT = nombre.hint.toString()
                nombre.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera ++
            } else {
                nombre.boxBackgroundColor = Color.rgb(224, 224, 224)
                nombreT = nombre.editText?.text.toString()
                bandera++
            }
            if (apellido.editText?.text.toString() == "") {
                apellidoT = apellido.hint.toString()
                apellido.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera ++
            } else {
                apellido.boxBackgroundColor = Color.rgb(224, 224, 224)
                apellidoT = nombre.editText?.text.toString()
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


            if (bandera == 3) {
                val serie = PersonaEntity(
                    id_Persona = id_actual,
                    Nombre_persona = nombreT,
                    Apellido_persona = apellidoT,
                    Edad_persona = Integer.parseInt(edadT)
                )
                GlobalScope.launch(Dispatchers.IO) {
                    SDao.update_persona(serie)
                }
                this.finish()
            }
        }
    }
}