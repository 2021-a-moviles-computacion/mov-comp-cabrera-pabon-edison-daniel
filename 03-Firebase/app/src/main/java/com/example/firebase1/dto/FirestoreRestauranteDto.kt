package com.example.firebase1.dto

data class FirestoreRestauranteDto(
    var uid:String = "",
    var nombre:String = ""
) {
    override fun toString():String{
        return nombre
    }
}