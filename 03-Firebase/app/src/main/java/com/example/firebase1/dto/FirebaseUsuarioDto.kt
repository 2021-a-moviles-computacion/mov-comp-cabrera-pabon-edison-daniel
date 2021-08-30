package com.example.firebase1.dto

data class FirebaseUsuarioDto(
    val uid: String ="",
    val email:String ="",
    val roles: ArrayList<String> = arrayListOf()
) {
}