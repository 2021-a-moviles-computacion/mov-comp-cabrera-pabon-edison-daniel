package com.example.examen2b.dto

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi


class FirebaseProductoDTO (
    var id: String? = "",
    var nombre_producto: String? = "",
    var precio: Double? = 0.0,
    var disponibilidad: String? = "",
    var fechaIngreso: String? = "",
    var cantidad: Int = 0,
    var latitud: Double? = 0.0,
    var longitud: Double? = 0.0,
    var idPersona: String? = "",
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
    ) {
    }

    override fun toString(): String {

        return "ID Producto: ${id} -" +
                "ID Persona: ${idPersona} -" +
                "Nombre: ${nombre_producto} -" +
                "Precio:  ${precio} -" +
                "Disponibilidad: ${disponibilidad} -" +
                "Fecha de Ingreso: ${fechaIngreso} -" +
                "Cantidad: ${cantidad} -" +
                "Latiud: ${latitud} -" +
                "Longitud ${longitud}"

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nombre_producto)
        parcel.writeDouble(precio!!)
        parcel.writeString(disponibilidad)
        parcel.writeString(fechaIngreso)
        parcel.writeInt(cantidad)
        parcel.writeDouble(latitud!!)
        parcel.writeDouble(longitud!!)
        parcel.writeString(idPersona)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirebaseProductoDTO> {
        override fun createFromParcel(parcel: Parcel): FirebaseProductoDTO{
            return FirebaseProductoDTO(parcel)
        }

        override fun newArray(size: Int): Array<FirebaseProductoDTO?> {
            return arrayOfNulls(size)
        }
    }

}