package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class CRUD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)
        val botonCrear = findViewById<Button>(
            R.id.btn_crear_user
        )
        val botonActualizar = findViewById<Button>(
            R.id.btn_actualizar_user
        )
        val botonConsultar = findViewById<Button>(
            R.id.btn_consultar_user
        )
        val botonEliminar = findViewById<Button>(
            R.id.btn_eliminar_user
        )

        val nombre = findViewById<EditText>(
            R.id.txt_name
        )
        val descripcion = findViewById<EditText>(
            R.id.txt_descript
        )
        val id = findViewById<EditText>(
            R.id.txt_id
        )


        botonCrear.setOnClickListener {
            if(EBaseDatos.TablaUsuario!=null){
                EBaseDatos.TablaUsuario!!.crearUsuarioFormulario(nombre.text.toString(),descripcion.text.toString())
                Log.i("bbd", "Usuario ${nombre.text} con descripcion: ${descripcion.text} creado")
            }

                nombre.setText("");
                descripcion.setText("");
                id.setText("");

        }

        botonConsultar.setOnClickListener {
            if(EBaseDatos.TablaUsuario!=null){
                val user= EBaseDatos.TablaUsuario!!.consultarUsuarioPorId(id.text.toString().toInt())
                Log.i("bbd", "El id consultado: ${id.text} corresponde al usuario " +
                        "${user.nombre} con descripcion: ${user.descripcion} ")
            }
            nombre.setText("");
            descripcion.setText("");
            id.setText("");
        }


        botonActualizar.setOnClickListener {
            if(EBaseDatos.TablaUsuario!=null){
                val actualizado = EBaseDatos.TablaUsuario!!.actualizarUsuarioFormulario(id.text.toString().toInt(),
                    nombre.text.toString(),descripcion.text.toString())
                Log.i("bbd", "El usuario con el id: ${id.text} ha sido actualizado ${actualizado} ")
            }
            nombre.setText("");
            descripcion.setText("");
            id.setText("");
        }


        botonEliminar.setOnClickListener {
            if(EBaseDatos.TablaUsuario!=null){
                val eliminacion = EBaseDatos.TablaUsuario!!.eliminarUsuarioFormulario(id.text.toString().toInt())
                Log.i("bbd", "El usuario con el id: ${id.text} ha sido eliminado ${eliminacion} ")
            }
        }
        nombre.setText("");
        descripcion.setText("");
        id.setText("");

    }
}