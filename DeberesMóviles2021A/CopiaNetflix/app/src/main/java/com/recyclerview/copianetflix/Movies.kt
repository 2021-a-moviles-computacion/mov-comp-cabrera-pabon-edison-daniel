package com.recyclerview.copianetflix

import android.os.Parcel
import android.os.Parcelable

class Movies(
    val movieName : String?,
    val movieDate: String?,
    val movieImage: Int?
    ): Parcelable {
        constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
        ) {
        }

        override fun toString(): String{
            return "${movieName} -${movieDate} -${movieImage}"
        }

        override fun describeContents(): Int {
            return  0
        }

        override fun writeToParcel(parcel: Parcel?, flags: Int) {
            parcel?.writeString(movieName)
            parcel?.writeString(movieDate)
            if (movieImage != null) {
                parcel?.writeInt(movieImage)
            }
        }

    companion object CREATOR : Parcelable.Creator<Movies> {
        override fun createFromParcel(parcel: Parcel): Movies {
            return Movies(parcel)
        }

        override fun newArray(size: Int): Array<Movies?> {
            return arrayOfNulls(size)
        }
    }


}
