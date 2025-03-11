package com.lmontes.finalapp.api

import com.lmontes.finalapp.data.Habitacion
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("habitaciones")
    fun registrarHabitacion(@Body habitacion: Habitacion): Call<Void>

    @GET("habitaciones")
    fun obtenerHabitaciones(): Call<ApiResponse>
}
