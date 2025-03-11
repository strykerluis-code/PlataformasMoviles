package com.lmontes.finalapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lmontes.finalapp.adapter.HabitacionAdapter
import com.lmontes.finalapp.api.ApiResponse
import com.lmontes.finalapp.api.RetrofitClient
import com.lmontes.finalapp.data.Habitacion
import com.lmontes.finalapp.data.Reserva
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaHabitacionesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var habitacionAdapter: HabitacionAdapter
    private var habitaciones = mutableListOf<Habitacion>() // Lista mutable vacía

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lista_habitaciones)

        recyclerView = findViewById(R.id.recyclerViewHabitaciones)
        recyclerView.layoutManager = LinearLayoutManager(this)

        habitacionAdapter = HabitacionAdapter(habitaciones) { habitacion ->
            val intent = Intent(this, DetalleHabitacionActivity::class.java).apply {
                putExtra("nombre", habitacion.nombre)
                putExtra("descripcion", habitacion.descripcion)
                putExtra("capacidad", habitacion.capacidad.toString())
                putExtra("ubicacion", habitacion.ubicacion)
                putExtra("incluye", habitacion.incluye)
                putExtra("costo", habitacion.costo.toString())
                putExtra("imagenUrl", habitacion.imagenUrl)
                putParcelableArrayListExtra("reservas", ArrayList(habitacion.reservas))
            }
            startActivityForResult(intent, REQUEST_CODE_RESERVA)
        }
        recyclerView.adapter = habitacionAdapter

        obtenerHabitacionesDesdeAPI()
    }

    private fun obtenerHabitacionesDesdeAPI() {
        RetrofitClient.instance.obtenerHabitaciones().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    habitaciones.clear()

                    val habitacionesMap = mutableMapOf<Int, Habitacion>()

                    response.body()!!.data.forEach { habitacion ->
                        if (habitacionesMap.containsKey(habitacion.id)) {
                            // Si ya existe la habitación, agregar las reservas a la lista existente
                            val habitacionExistente = habitacionesMap[habitacion.id]!!
                            val nuevasReservas = habitacionExistente.reservas.toMutableList()
                            nuevasReservas.addAll(habitacion.reservas)
                            habitacionesMap[habitacion.id] = habitacionExistente.copy(reservas = nuevasReservas)
                        } else {
                            // Si es la primera vez que se encuentra esta habitación, agregarla al mapa
                            habitacionesMap[habitacion.id] = habitacion
                        }
                    }

                    // Agregar las habitaciones agrupadas a la lista final
                    habitaciones.addAll(habitacionesMap.values)

                    habitacionAdapter.notifyDataSetChanged()
                } else {
                    Log.e("API_ERROR", "Error en respuesta: ${response.code()} - ${response.message()}")
                    Toast.makeText(this@ListaHabitacionesActivity, "Error en API: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("API_ERROR", "Fallo en la conexión con la API", t)
                Toast.makeText(this@ListaHabitacionesActivity, "Error de conexión: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    companion object {
        private const val REQUEST_CODE_RESERVA = 1001
    }
}
