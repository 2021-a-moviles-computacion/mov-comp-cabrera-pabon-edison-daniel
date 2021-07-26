package com.example.examenprimerbimestre

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.examenpokentrpp1.activEntren.AdaptadorPersonas
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class IngresarProducto : AppCompatActivity() {
    private lateinit var ADao: ProductoDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_producto)
        var db: Database.PersonasDatabase = Database.PersonasDatabase.getInstance(this)

        ADao = db.productoDao
        val button = findViewById<Button>(R.id.btnIngresoProducto)
        var id_per = findViewById<TextInputLayout>(R.id.tinIdPersona)
        val nombre = findViewById<TextInputLayout>(R.id.tinNombreProducto)
        val precio = findViewById<TextInputLayout>(R.id.tinPrecio)
        val fechaIng = findViewById<TextInputLayout>(R.id.tinFechaIngreso)
        val disponibilidad = findViewById<TextInputLayout>(R.id.tinDisponibilidad)
        val cantidad = findViewById<TextInputLayout>(R.id.tinCantidad)
        var disp: Boolean = false

        button.setOnClickListener {
            var bandera = 0

            if (id_per.editText?.text.toString() == "") {
                id_per.boxBackgroundColor = Color.RED
                id_per.hint = "Ingrese de la persona"
                bandera = 0
            } else {
                id_per.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera++
            }

            if (nombre.editText?.text.toString() == "") {
                nombre.boxBackgroundColor = Color.RED
                nombre.hint = "Ingrese el nombre del producto"
                bandera = 0
            } else {
                nombre.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera++
            }

            if (precio.editText?.text.toString() == "") {
                precio.boxBackgroundColor = Color.RED
                precio.hint = "Ingrese el precio del producto"
                bandera = 0
            } else {
                precio.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera++
            }

            when {
                fechaIng.editText?.text.toString() == "" -> {
                    fechaIng.boxBackgroundColor = Color.RED
                    fechaIng.hint = "Ingrese la Fecha de Ingreso del Producto"
                    bandera = 0
                }
                fechaIng.editText?.text.toString().matches(Regex("""((((0[1-9]|1[0-9]|2[0-8])[\/](0[1-9]|1[012]))|((29|30|31)[\/](0[13578]|1[02]))|((29|30)[\/](0[4,6,9]|11)))[\/](19|[2-9][0-9])\d\d${'$'})|(^29[\/]02[\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96))""")) -> {
                    fechaIng.boxBackgroundColor = Color.rgb(224, 224, 224)
                    bandera++
                }
                else -> {
                    fechaIng.boxBackgroundColor = Color.RED
                    fechaIng.hint = "Ingrese la Fecha en el formato especificado"
                    bandera = 0
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
                    bandera = 0
                }
            }

            if (cantidad.editText?.text.toString() == "") {
                cantidad.boxBackgroundColor = Color.RED
                cantidad.hint = "Ingrese la cantidad de productos deseados"
                bandera = 0
            } else {
                cantidad.boxBackgroundColor = Color.rgb(224, 224, 224)
                bandera++
            }
            if (bandera == 6) {
                val producto = ProductoEntity(
                    id_Persona = id_per.editText?.text.toString().toInt(),
                    Nombre_producto = nombre.editText?.text.toString(),
                    Precio_producto = precio.editText?.text.toString().toDouble(),
                    Fecha_ingreso_producto = fechaIng.editText?.text.toString(),
                    Disponibilidad_producto = disp,
                    Cantidad_producto = Integer.parseInt(cantidad.editText?.text.toString())
                )
                GlobalScope.launch(Dispatchers.IO) {
                    ADao.insert_producto(producto)
                }
                Toast.makeText(this,"Producto ingresado corectamente", Toast.LENGTH_SHORT).show()
                this.finish()
            }
        }

    }

}