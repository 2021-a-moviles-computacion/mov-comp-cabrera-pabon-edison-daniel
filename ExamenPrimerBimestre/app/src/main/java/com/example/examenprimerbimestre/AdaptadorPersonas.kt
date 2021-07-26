package com.example.examenpokentrpp1.activEntren


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.examenprimerbimestre.R
import com.example.examenprimerbimestre.PersonaEntity


class AdaptadorPersonas(private val context: Context,
                        private var dataSource: List<PersonaEntity> = listOf(PersonaEntity())) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount(): Int {
        return dataSource.size
    }


    override fun getItem(position: Int): Any {
        return dataSource[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun update(list: List<PersonaEntity>){
        this.dataSource = list
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.personas_layout, parent, false)

        val nombre = rowView.findViewById(R.id.nombrePersonas_ly) as TextView
        val apellido = rowView.findViewById(R.id.apellidoPersonas_ly) as TextView
        val edad = rowView.findViewById(R.id.edadPersonas_ly) as TextView
        val email = rowView.findViewById(R.id.correoPersonas_ly) as TextView
        val telefono = rowView.findViewById(R.id.telefonoPersonas_ly) as TextView

        val entidad = getItem(position) as PersonaEntity

        nombre.text = entidad.Nombre_persona
        apellido.text = entidad.Apellido_persona
        edad.text = entidad.Edad_persona.toString()
        email.text = entidad.Email_persona
        telefono.text = entidad.Telefono_persona

        return rowView
    }
}