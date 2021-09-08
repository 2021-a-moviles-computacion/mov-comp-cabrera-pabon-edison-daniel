package com.example.firebase1.dto

import java.text.NumberFormat
import java.util.*

data class FirestoreOrdenesDto(
    var nombreProducto: String,
    var precioUnitario: Double,
    var cantidad: Int
    ) {
    override fun toString(): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.setMaximumFractionDigits(2)
        format.setCurrency(Currency.getInstance("USD"))

        return "${nombreProducto} -> ${format.format(precioUnitario)} -> $cantidad -> ${format.format(calcularTotal())} "
    }

    fun calcularTotal():Double{
        return precioUnitario * cantidad.toDouble()
    }
}