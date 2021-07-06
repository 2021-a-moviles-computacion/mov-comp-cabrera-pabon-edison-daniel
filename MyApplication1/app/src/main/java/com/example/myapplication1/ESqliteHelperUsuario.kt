package com.example.myapplication1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class ESqliteHelperUsuario(
    contexto: Context?
):SQLiteOpenHelper (
    contexto,
    "moviles",
    null,
    1
        ){

    override fun onCreate(db: SQLiteDatabase?){
                val scriptCrearTablaUsuario =
                    """
                    create table usuario(
                    id integer primary key autoincrement,
                    nombre varchar(50),
                    descripcion varchar(50)
                    )
                    """.trimIndent()
                Log.i("bbd","Creando la tabla de usuario")
                db?.execSQL(scriptCrearTablaUsuario)
            }

    fun crearUsuarioFormulario(
        nombre: String,
        descripcion: String

    ):Boolean{
        //val conexionEscritura = this.writableDatabase
        val conexionEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre",nombre)
        valoresAGuardar.put("descripcion",descripcion)
        val resultadoEscritura: Long = conexionEscritura.insert(
            "usuario",
            null,
            valoresAGuardar
        )
        conexionEscritura.close()
        println("vale metodo crear")
        //return resultadoEscritura.toInt() != -1
        return if (resultadoEscritura.toInt() == -1) false else true

    }


    fun consultarUsuarioPorId(id:Int):EUsuarioBDD {
        val scriptConsultarUsuario = "select * from usuario where id = ${id}"
        //val baseDatosLectura = this.readableDatabase
        val baseDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultarUsuario, null)
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = EUsuarioBDD(0, "", "")
        if (existeUsuario){
            do {
                val id = resultadoConsultaLectura.getInt(0)//columna indice 0 -> ID
                val nombre = resultadoConsultaLectura.getString(1)//columna indice 1 -> NOMBRE
                val descripcion =
                    resultadoConsultaLectura.getString(2)//columna indice 2 -> DESCRIPCION

                if (id != null) {
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                    /* arregloUsuario.add(usuarioEncontrado) */
                }
            } while (resultadoConsultaLectura.moveToNext())
    }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        println("vale metodo consultar")
        return usuarioEncontrado
    }
    fun eliminarUsuarioFormulario(id:Int):Boolean{
        //val conexionEscritura = this.writableDatabase
        val conexionEscritura = writableDatabase
        val resultadoEliminado = conexionEscritura.delete(
            "usuario",
            "id=?",
            arrayOf(
                id.toString()
            )
        )
        conexionEscritura.close()
        //return resultadoEliminado.toInt() != -1
        println("vale metodo eliminar")
        return if (resultadoEliminado.toInt() == -1) false else true
    }
    fun actualizarUsuarioFormulario(
        idActualizar:Int,
        nombre: String,
        descripcion: String,
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre",nombre)
        valoresActualizar.put("descripcion",descripcion)
        val resultadoActualizacion = conexionEscritura.update(
            "usuario", //nombre tabla
            valoresActualizar, //valores actualizar
            "id=?", // clasula where
            arrayOf(
                idActualizar.toString()
            )
        )
        conexionEscritura.close()
        println("vale  metodo actualizar")
        return if(resultadoActualizacion == -1) false else true
    }


        override fun onUpgrade(p0: SQLiteDatabase?, p1:Int, p2:Int){
            TODO("Not yet implemented")
        }
        }

