package com.recyclerview.carouselviewexample

import android.os.Parcel
import android.os.Parcelable

class Series(
    val serieName : String?,
    val serieDate: String?,
    val serieImage: Int?
    ): Parcelable {
        constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
        ) {
        }

        override fun toString(): String{
            return "${serieName} -${serieDate} -${serieImage}"
        }

        override fun describeContents(): Int {
            return  0
        }

        override fun writeToParcel(parcel: Parcel?, flags: Int) {
            parcel?.writeString(serieName)
            parcel?.writeString(serieDate)
            if (serieImage != null) {
                parcel?.writeInt(serieImage)
            }
        }

    companion object CREATOR : Parcelable.Creator<Series> {
        override fun createFromParcel(parcel: Parcel): Series {
            return Series(parcel)
        }

        override fun newArray(size: Int): Array<Series?> {
            return arrayOfNulls(size)
        }
    }


}
