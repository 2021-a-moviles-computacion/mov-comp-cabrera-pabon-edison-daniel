package com.example.firebase1.dto

import com.google.firebase.Timestamp
import java.util.*

data class FirestoreProductoDto(
    var uid:String = "",
    var nombre:String = "",
    var precio: String =""

    ){

    override fun toString():String{
        return nombre
    }
}
