package com.example.firebase1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.firebase1.dto.FirebaseUsuarioDto
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val CODIGO_INICIO_SESION=102
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonLogin = findViewById<Button>(R.id.btn_login)
        val botonLogout = findViewById<Button>(R.id.btn_logout)

        botonLogin.setOnClickListener {
            llamarLoginUsuario()
        }

        botonLogout.setOnClickListener {
            solicitarSalirdeAplicativo()
        }

        val cproducto = findViewById<Button>(R.id.btn_cproducto)
        cproducto.setOnClickListener {
            val intent = Intent(
                this,
                CProducto::class.java
            )
            startActivity(intent)
        }

        val botonRestaurante = findViewById<Button>(R.id.btn_restaurante)
        botonRestaurante.setOnClickListener {
            val intent = Intent(
                this,
                DRestaurante::class.java
            )
            startActivity(intent)
        }

        val botonOrdenes = findViewById<Button>(R.id.btn_ordenes)
        botonOrdenes.setOnClickListener {
            val intent = Intent(
                this,
                EOrdenes::class.java
            )
            startActivity(intent)
        }
    }



    fun llamarLoginUsuario(){
        val providers = arrayListOf(
            //lista de proveedores(facebook, twitter, ...)
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).setTosAndPrivacyPolicyUrls(
                "https:://example.com/terms.html",
            "https://example.com/privacy.html"
            ).build(),
            CODIGO_INICIO_SESION
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODIGO_INICIO_SESION -> {
                if (resultCode == Activity.RESULT_OK) {
                    val usuario = IdpResponse.fromResultIntent(data)
                    if (usuario?.isNewUser == true) {
                        Log.i("firebase-login", "Nuevo Usuario")
                        registrarUsuarioPorPrimeraVez(usuario)
                    } else {
                        setearUsuarioFirebase()
                        setearBienvenida()
                        Log.i("firebase-login", "Usuario Antiguo")
                    }
                } else {
                    Log.i("firebase-login", "El usuario cancelo")
                }
            }
        }
    }

    fun registrarUsuarioPorPrimeraVez(usuario: IdpResponse){
        val usuarioLogueado = FirebaseAuth.getInstance().currentUser //recibo el usuario

        if(usuario.email != null && usuarioLogueado != null){
            // roles: ["usuario", "admin"]
            val db = Firebase.firestore
            val rolesUsuario = arrayListOf("usuario")
            val identificarUsuario = usuario.email

            val nuevoUsuario = hashMapOf<String, Any>(
                "roles" to rolesUsuario,
                "uid" to usuarioLogueado.uid,
                "email" to identificarUsuario.toString()
            )
            //Forma a crea el identificador
            /*db.collection("usuario").add(nuevoUsuario).addOnSuccessListener{
                    Log.i("firebase-firestore", "Se creo el usuario correctamente")
                }.addOnFailureListener{
                Log.i("firebase-firestore","Fallo la creación del Usuario")
            }*/

            //Forma b seteo el identificador
            db.collection("usuario").document(identificarUsuario.toString())
                .set(nuevoUsuario).addOnSuccessListener{
                Log.i("firebase-firestore", "Se creo el usuario correctamente")
            }.addOnFailureListener{
                    Log.i("firebase-firestore","Fallo la creación del Usuario")
                }

        }else{
            Log.i("firebase-login", "ERROR")
        }
    }

    fun setearUsuarioFirebase(){
        val instanciaAuth = FirebaseAuth.getInstance()
        val usuarioLocal = instanciaAuth.currentUser
        if (usuarioLocal != null) {
            if(usuarioLocal.email != null){
                val db = Firebase.firestore
                val referencia = db.collection("usuario")
                    .document(usuarioLocal.email.toString())

                referencia.get().addOnSuccessListener{
                    val usuarioCargado: FirebaseUsuarioDto? =
                        it.toObject(FirebaseUsuarioDto::class.java)
                    if (usuarioCargado != null) {
                        BAuthUsuario.usuario = BUsuarioFirebase(
                            usuarioCargado.uid,
                            usuarioCargado.email,
                            usuarioCargado.roles
                        )
                    }
                    setearBienvenida()
                    Log.i("firebase-firestore","Usuario Cargado")
                }
                    .addOnFailureListener{
                        Log.i("firebase-firestore","Fallo Cargar Usuario")
                    }

            }
        }
    }

    fun setearBienvenida(){
        val textViewBienvenida = findViewById<TextView>(R.id.tv_bienvenida)
        val botonLogin = findViewById<Button>(R.id.btn_login)
        val botonLogout = findViewById<Button>(R.id.btn_logout)
        val botonCProducto = findViewById<Button>(R.id.btn_cproducto)
        val botonRestaurante = findViewById<Button>(R.id.btn_restaurante)
        val botonOrdenes = findViewById<Button>(R.id.btn_ordenes)

        if(BAuthUsuario.usuario != null){
            textViewBienvenida.text = "Bienvenido ${BAuthUsuario.usuario?.email}"
            botonLogin.visibility = View.INVISIBLE
            botonLogout.visibility = View.VISIBLE
            botonCProducto.visibility = View.VISIBLE
            botonRestaurante.visibility = View.VISIBLE
            botonOrdenes.visibility = View.VISIBLE
        }else{
            textViewBienvenida.text = "Ingresa al Aplicativo"
            botonLogin.visibility = View.VISIBLE
            botonLogout.visibility = View.INVISIBLE
            botonCProducto.visibility = View.INVISIBLE
            botonRestaurante.visibility = View.INVISIBLE
            botonOrdenes.visibility = View.INVISIBLE
        }
    }


    fun solicitarSalirdeAplicativo(){
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                BAuthUsuario.usuario = null
            }
    }






}