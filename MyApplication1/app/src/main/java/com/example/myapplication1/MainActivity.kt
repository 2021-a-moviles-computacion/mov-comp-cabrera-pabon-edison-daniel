package com.example.myapplication1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
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
        val botonIrIntent = findViewById<Button>(
            R.id.btn_ir_intent
        )
        botonIrIntent
            .setOnClickListener{abrirActividadConParametros(CIntentExplicitoParametros::class.java)}

        val botonAbrirIntentImplicito = findViewById<Button>(
            R.id.btn_ir_intent_implicito
        )
        botonAbrirIntentImplicito.setOnClickListener {
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            startActivityForResult(intentConRespuesta,CODIGO_RESPUESTA_INTENT_EXPLICITO)

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

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intenExplicito = Intent(
            this,
            clase
        )
        intenExplicito.putExtra("nombre","Edison")
        intenExplicito.putExtra("apellido","Cabrera")
        intenExplicito.putExtra("edad",24)
        intenExplicito.putExtra(
            "entrenador",
            BEntrenador("Edison","Cabrera")
        )

        startActivityForResult(intenExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
        /*registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                Activity.RESULT_OK ->{
                    it.data?.getStringExtra("nombreModificado")
                    it.data?.getIntExtra("edadModificada",0)
                    it.data?.getParcelableExtra<BEntrenador>("entrenadorModificado")
                }
            }
        }*/

    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CODIGO_RESPUESTA_INTENT_EXPLICITO -> {
                /*if(resultCode == RESULT_OK){
                    Log.i("intent-explicito","SI actualizó los datos")
                }*/
                if(resultCode == RESULT_OK){
                    Log.i("intent-explicito","SI Actulizó los Datos")
                    if(data!=null){
                        val nombre = data.getStringExtra("nombreModificado")
                        val edad = data.getIntExtra("edadModificada",0)
                        val entrenador = data.getParcelableExtra<BEntrenador>("entrenadorModificado")
                        Log.i("intent-explicito", "${nombre}")
                        Log.i("intent-explicito", "${edad}")
                        Log.i("intent-explicito", "${entrenador}")
                    }
                }
            }
        }
    }*/



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CODIGO_RESPUESTA_INTENT_EXPLICITO -> {
                /*if(resultCode == RESULT_OK){
                    Log.i("intent-explicito","SI actualizó los datos")
                }*/
                if(resultCode == RESULT_OK){
                    Log.i("intent-explicito","SI Actulizó los Datos")
                    if(data!=null){
                        val nombre = data.getStringExtra("nombreModificado")
                        val edad = data.getIntExtra("edadModificada",0)
                        val entrenador = data.getParcelableExtra<BEntrenador>("entrenadorModificado")
                        Log.i("intent-explicito", "${nombre}")
                        Log.i("intent-explicito", "${edad}")
                        Log.i("intent-explicito", "${entrenador}")
                    }
                }
            }
            CODIGO_RESPUESTA_INTENT_EXPLICITO ->{
                if(resultCode == RESULT_OK){
                    if (data != null) {
                        if(data.data != null){
                            val uri: Uri = data.data!!
                            val cursor = contentResolver.query(
                                uri,
                                null,
                                null,
                                null,
                                null,
                                null,
                                )
                            cursor?.moveToFirst()
                            val indiceTelefono = cursor?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getString(
                                indiceTelefono!!
                            )
                            cursor?.close()
                            Log.i("resultado","Telefono ${telefono}")

                        }
                    }
                }
            }
        }
    }


}