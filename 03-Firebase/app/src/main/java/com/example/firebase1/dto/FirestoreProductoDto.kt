package com.example.firebase1.dto

data class FirestoreProductoDto(
    var uid:String = "",
    var nombre:String = "",
    var precio: Double? = null

    ){

    override fun toString():String{
        return "$nombre -- $ ${precio.toString()}"
    }
}
