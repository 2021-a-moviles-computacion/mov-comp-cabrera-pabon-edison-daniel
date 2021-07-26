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

class EditarProducto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_producto)
        val id_actual = intent.getIntExtra("id_actual",0)
        val id_persona = intent.getIntExtra("id_persona",0)
        var db:Database.PersonasDatabase  = Database.PersonasDatabase.getInstance(this)

        val ADao = db.productoDao

        val nombreProducto = findViewById<TextInputLayout>(R.id.tinModificarNombreProducto)
        val precio = findViewById<TextInputLayout>(R.id.tinModificarPrecio)
        val fechaIng = findViewById<TextInputLayout>(R.id.tinModificarFechaIngreso)
        val disponibilidad = findViewById<TextInputLayout>(R.id.tinModificarDisponibilidad)
        val cantidad = findViewById<TextInputLayout>(R.id.tinModificarCantidad)
        var disp: Boolean = false

        val actual = ADao.get_id_producto(id_actual)

        actual?.observe(this, Observer{ words ->
            words?.let {
                nombreProducto.hint = it.Nombre_producto
                precio.hint = it.Precio_producto.toString()
                fechaIng.hint = it.Fecha_ingreso_producto
                if(it.Disponibilidad_producto){
                    disponibilidad.hint = "S"
                } else {
                    disponibilidad.hint = "N"
                }
                cantidad.hint = it.Cantidad_producto.toString()
            }
        });

        val btnActualizarProducto = findViewById<Button>(R.id.btnActualizarProducto)
        btnActualizarProducto.setOnClickListener {
            var nombreProductoT=""
            var precioT = ""
            var cantidadT = ""
            var bandera = 0

            if (nombreProducto.editText?.text.toString() == "") {
                nombreProducto.boxBackgroundColor = Color.rgb(224, 224, 224)
                nombreProductoT = nombreProducto.hint.toString()
                bandera++
            } else {
                nombreProducto.boxBackgroundColor = Color.rgb(224, 224, 224)
                nombreProductoT = nombreProducto.editText?.text.toString()
                bandera++
            }

            if (precio.editText?.text.toString() == "") {
                precio.boxBackgroundColor = Color.rgb(224, 224, 224)
                precioT = precio.hint.toString()
                bandera++
            } else {
                precio.boxBackgroundColor = Color.rgb(224, 224, 224)
                precioT = precio.editText?.text.toString()
                bandera++
            }

            when {
                fechaIng.editText?.text.toString() == "" -> {
                    fechaIng.boxBackgroundColor = Color.RED
                    fechaIng.hint.toString()
                    bandera++
                }
                fechaIng.editText?.text.toString().matches(Regex("""((((0[1-9]|1[0-9]|2[0-8])[\/](0[1-9]|1[012]))|((29|30|31)[\/](0[13578]|1[02]))|((29|30)[\/](0[4,6,9]|11)))[\/](19|[2-9][0-9])\d\d${'$'})|(^29[\/]02[\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96))""")) -> {
                    fechaIng.boxBackgroundColor = Color.rgb(224, 224, 224)
                    bandera++
                }
                else -> {
                    fechaIng.boxBackgroundColor = Color.RED
                    fechaIng.hint = "Ingrese la Fecha en el formato especificado"
                    bandera++
                }
            }

            when {
                disponibilidad.editText?.text.toString() == "S" || disponibilidad.editText?.text.toString() == "N" -> {
                    disp = disponibilidad.editText?.text.toString() == "S"
                    bandera++
                    disponibilidad.boxBackgroundColor = Color.rgb(224, 224, 224)
                }
                else -> {
                    disponibilidad.boxBackgroundColor = Color.RED
                    disponibilidad.hint = "Sólo se permite S o N"
                    bandera++
                }
            }

            if (cantidad.editText?.text.toString() == "") {
                cantidadT = cantidad.hint.toString()
                bandera++
            } else {
                cantidad.boxBackgroundColor = Color.rgb(224, 224, 224)
                cantidadT = cantidad.editText?.text.toString()
                bandera++
            }

            if (bandera == 5) {
                val producto = ProductoEntity(
                    id_Persona = id_persona,
                    id_Producto = id_actual,
                    Nombre_producto = nombreProductoT,
                    Precio_producto = precioT.toDouble(),
                    Fecha_ingreso_producto = fechaIng.editText?.text.toString(),
                    Disponibilidad_producto = disp,
                    Cantidad_producto = Integer.parseInt(cantidadT)
                )
                GlobalScope.launch(Dispatchers.IO) {
                    ADao.update_producto(producto)
                }
                Toast.makeText(this,"Producto actualizado corectamente", Toast.LENGTH_SHORT).show()
                this.finish()
            }
        }
    }
}