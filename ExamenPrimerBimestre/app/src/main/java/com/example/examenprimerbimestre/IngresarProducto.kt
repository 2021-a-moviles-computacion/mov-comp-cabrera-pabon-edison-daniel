package com.example.examenprimerbimestre

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class IngresarProducto : AppCompatActivity() {
    private lateinit var ADao: ProductoDao
    var id_persona = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_producto)
        id_persona = intent.getIntExtra("id_persona",0)

        var db: Database.PersonasDatabase = Database.PersonasDatabase.getInstance(this)

        ADao = db.productoDao
        val button = findViewById<Button>(R.id.btnCreacionActor)

        val nombre = findViewById<TextInputLayout>(R.id.tinNombre)
        val precio = findViewById<TextInputLayout>(R.id.tinApellido)
        val fechaIng = findViewById<TextInputLayout>(R.id.tinFechaNacimiento)
        val disponibilidad = findViewById<TextInputLayout>(R.id.tinGenero)
        val cantidad = findViewById<TextInputLayout>(R.id.tinEdad)
        var disp: Boolean = false

        button.setOnClickListener {
            var bandera = 0

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
            if (bandera == 5) {
                val actor = ProductoEntity(
                    id_Persona = id_persona,
                    Nombre_producto = nombre.editText?.text.toString(),
                    Precio_producto = precio.editText?.text.toString().toDouble(),
                    Fecha_ingreso_producto = fechaIng.editText?.text.toString(),
                    Disponibilidad_producto = disp,
                    Cantidad_producto = Integer.parseInt(cantidad.editText?.text.toString())
                )
                GlobalScope.launch(Dispatchers.IO) {
                    ADao.insert_producto(actor)
                }
                this.finish()
            }
        }


    }
}