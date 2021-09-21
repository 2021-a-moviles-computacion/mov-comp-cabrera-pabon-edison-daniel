package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ir_aplicacion = findViewById<Button>(R.id.btn_empezar_examen)

        ir_aplicacion.setOnClickListener {
            irAṕlicacion()

        }
    }

    fun irAṕlicacion() {
        val intentExplicito = Intent(
            this,
            Persona::class.java
        )
        startActivity(intentExplicito)

    }

}