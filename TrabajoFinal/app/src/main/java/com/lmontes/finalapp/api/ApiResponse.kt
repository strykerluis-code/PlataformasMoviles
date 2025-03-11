package com.lmontes.finalapp.api

import com.lmontes.finalapp.data.Habitacion

data class ApiResponse(
    val data: List<Habitacion> // La API devuelve un objeto con una clave "data" que contiene la lista
)