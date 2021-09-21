package com.example.examen2b.dto
import android.os.Parcel
import android.os.Parcelable


class FirebasePersonaDTO (
    var id: String? ="",
    var nombre: String? ="",
    var apellido: String? = "",
    var edad: Int? = 0,
    var email: String? = "",
    var telefono: String? = "",
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readInt(),
        parcel?.readString(),
        parcel?.readString()
    ) {
    }

    override fun toString(): String {
        return "ID: ${id} -" +
                "Nombre: ${nombre} -" +
                "Apellido: ${apellido} -" +
                "Edad:  ${edad} -" +
                "Correo Electrónico: ${email} -" +
                "Teléfono: ${telefono} "

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel?.writeString(id)
        parcel?.writeString(nombre)
        parcel?.writeString(apellido)
        parcel?.writeInt(edad!!)
        parcel?.writeString(email)
        parcel?.writeString(telefono)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirebasePersonaDTO> {
        override fun createFromParcel(parcel: Parcel): FirebasePersonaDTO {
            return FirebasePersonaDTO(parcel)
        }

        override fun newArray(size: Int): Array<FirebasePersonaDTO?> {
            return arrayOfNulls(size)
        }
    }

}
