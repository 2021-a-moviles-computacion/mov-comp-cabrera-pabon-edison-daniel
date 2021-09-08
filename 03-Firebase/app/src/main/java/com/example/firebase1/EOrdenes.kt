package com.example.firebase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.firebase1.dto.FirestoreOrdenesDto
import com.example.firebase1.dto.FirestoreProductoDto
import com.example.firebase1.dto.FirestoreRestauranteDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EOrdenes : AppCompatActivity() {
    var arregloRestaurantes = arrayListOf<FirestoreRestauranteDto>()
    var adaptadorRestaurantes: ArrayAdapter<FirestoreRestauranteDto>? = null
    var restauranteSeleccionado: FirestoreRestauranteDto? = null

    var arregloProductos = arrayListOf<FirestoreProductoDto>()
    var adaptadorProductos: ArrayAdapter<FirestoreProductoDto>? = null
    var productoSeleccionado= FirestoreProductoDto()

    var arregloOrdenes = ArrayList<FirestoreOrdenesDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eordenes)
        if(adaptadorRestaurantes == null) {
            adaptadorRestaurantes = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                arregloRestaurantes
            )
            adaptadorRestaurantes?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cargarRestaurantes()
        }

        if(adaptadorProductos == null) {
            adaptadorProductos = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                arregloProductos
            )
            adaptadorProductos?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cargarProductos()
        }

        val textViewEcabezado = findViewById<TextView>(R.id.tv_encabezado_text_view)
        textViewEcabezado.text = "PRODUCTO \t VALOR UNIT. \t CANT. \t VALOR"


        val editTextCantidadProductos = findViewById<EditText>(R.id.et_cantidad_producto)
        val botonAdd = findViewById<Button>(R.id.btn_addTipoComida)

        val listViewProductos = findViewById<ListView>(R.id.lv_lista_productos)
        val adaptadorListaProductos = ArrayAdapter(this, android.R.layout.simple_selectable_list_item , arregloOrdenes)
        listViewProductos.adapter = adaptadorListaProductos


        botonAdd.setOnClickListener {
            var editTextCantidadProductosValorCorregido:String = editTextCantidadProductos.text.toString()
            if(editTextCantidadProductosValorCorregido == ""){
                editTextCantidadProductosValorCorregido = "1"
            }
            val orden = FirestoreOrdenesDto(productoSeleccionado.nombre,productoSeleccionado.precio!!,editTextCantidadProductosValorCorregido.toInt())
            añadirItemsAlListView(orden,adaptadorListaProductos)
            editTextCantidadProductos.text.clear()
        }

        val botonCompletarPedido = findViewById<Button>(R.id.btn_completar_pedido)
        botonCompletarPedido
            .setOnClickListener {
                val textViewTotalAPagar = findViewById<TextView>(R.id.tv_total_a_pagar)
                adaptadorListaProductos.clear()
                textViewTotalAPagar.text="0"
            }
    }


    fun cargarRestaurantes(){
        val spinnerRestaurantes = findViewById<Spinner>(R.id.spn_restaurantes)
        spinnerRestaurantes.adapter = adaptadorRestaurantes
        spinnerRestaurantes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                restauranteSeleccionado = arregloRestaurantes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.i("firebase-firestore", "No seleccionó nada")
            }
        }

        val db = Firebase.firestore

        val referencia = db.collection("restaurantes")
            .get()

        referencia
            .addOnSuccessListener{
                for (document in it) {
                    Log.i("firebase-firestore", "${document.id} => ${document.data}")
                    var restaurante = document.toObject(FirestoreRestauranteDto::class.java)
                    restaurante.uid = document.id
                    arregloRestaurantes.add(restaurante)
                    adaptadorRestaurantes?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener{

            }
    }


    fun cargarProductos(){
        val spinnerProductos = findViewById<Spinner>(R.id.spn_productos)
        spinnerProductos.adapter = adaptadorProductos
        spinnerProductos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                productoSeleccionado = arregloProductos[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.i("firebase-firestore", "No seleccionó nada")
            }
        }

        val db = Firebase.firestore

        val referencia = db.collection("producto")
            .get()

        referencia
            .addOnSuccessListener{
                for (document in it) {
                    //Log.i("firebase-firestore", "${document.id} => ${document.data}")
                    var producto = document.toObject(FirestoreProductoDto::class.java)
                    producto.uid = document.id
                    arregloProductos.add(producto)
                    adaptadorProductos?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener{

            }
    }

    fun añadirItemsAlListView(objeto:FirestoreOrdenesDto, adaptador: ArrayAdapter<FirestoreOrdenesDto>){
        arregloOrdenes.add(objeto)
        adaptador.notifyDataSetChanged()
        val textViewTotalAPagar = findViewById<TextView>(R.id.tv_total_a_pagar)
        textViewTotalAPagar.text = (textViewTotalAPagar.text.toString().toDouble() + objeto.calcularTotal()).toString()
    }


}