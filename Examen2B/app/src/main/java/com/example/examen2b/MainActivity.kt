package com.example.examen2b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ir_aplicacion = findViewById<ImageView>(R.id.img_inicio)

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