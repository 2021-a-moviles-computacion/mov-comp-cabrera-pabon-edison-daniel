package com.example.examenprimerbimestre

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdaptadorProducto(private val context: Context,
                        private var dataSource: List<ProductoEntity> = listOf(ProductoEntity())) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount(): Int {
        return dataSource.size
    }


    override fun getItem(position: Int): Any {
        return dataSource[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun update(list: List<ProductoEntity>) {
        this.dataSource = list
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.productos_layout, parent, false)

        val nombre = rowView.findViewById(R.id.nombreProductos_ly) as TextView
        val precio = rowView.findViewById(R.id.precioProductos_ly) as TextView
        val fechaIng = rowView.findViewById(R.id.fechaNacActores_ly) as TextView
        val disponibilidad = rowView.findViewById(R.id.disponibilidadProductos_ly) as TextView
        val cantidad = rowView.findViewById(R.id.cantidadProductos_ly) as TextView

        val entidad = getItem(position) as ProductoEntity

        nombre.text = entidad.Nombre_producto
        precio.text = entidad.Precio_producto.toString()
        fechaIng.text = entidad.Fecha_ingreso_producto
        if(entidad.Disponibilidad_producto){
            disponibilidad.text = "Disponible"
        } else {
            disponibilidad.text = "No está Disponible"
        }
        cantidad.text = entidad.Cantidad_producto.toString()

        return rowView
    }
}