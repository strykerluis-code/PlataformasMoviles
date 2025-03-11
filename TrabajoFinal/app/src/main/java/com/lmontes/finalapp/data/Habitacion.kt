package com.lmontes.finalapp.data

data class Habitacion(
    val id:Int,
    val nombre: String,
    val descripcion: String,
    val imagenUrl: String,
    val capacidad: Int,
    val ubicacion: String,
    val incluye: String,
    val costo: Double,
    val reservas:List<Reserva> = emptyList()
)
