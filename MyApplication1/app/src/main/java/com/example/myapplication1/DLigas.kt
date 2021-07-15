package com.example.myapplication1

import android.os.Parcel
import android.os.Parcelable

class DLigas (
    val nombre: String?,
    val descripcion: String?,
    val liga: DLigas? = null,

    ):Parcelable{
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(DLigas::class.java.classLoader)
        ) {
        }

        override fun toString(): String{
            return "${nombre} -${descripcion}"
        }

        override fun describeContents(): Int {
            return  0
        }

        override fun writeToParcel(parcel: Parcel?, flag: Int) {
            parcel?.writeString(nombre)
            parcel?.writeString(descripcion)
            parcel?.writeParcelable(liga,flag)

        }

        companion object CREATOR : Parcelable.Creator<BEntrenador> {
            override fun createFromParcel(parcel: Parcel): BEntrenador {
                return BEntrenador(parcel)
            }

            override fun newArray(size: Int): Array<BEntrenador?> {
                return arrayOfNulls(size)
            }
        }
}