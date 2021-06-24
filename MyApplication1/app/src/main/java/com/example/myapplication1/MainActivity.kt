package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //this.findViewById<>()
        val botonIrA1CicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )
        /*botonIrA1CicloVida
            .setOnClickListener{
                abrirCicloVida()
            }*/

        botonIrA1CicloVida
            .setOnClickListener{
                abrirActividad(A1CicloVida::class.java)
            }
        val botonIrListview = findViewById<Button>(
            R.id.btn_list_view
        )
        botonIrListview
            .setOnClickListener{
                abrirActividad(Listview::class.java)
            }
    }
     /*fun abrirCicloVida(){
         val intentExplicito = Intent(
             this,
             A1CicloVida::class.java
         )
         //this.startActivity(intent)
         startActivity(intentExplicito)
     }*/

    fun abrirActividad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }


}