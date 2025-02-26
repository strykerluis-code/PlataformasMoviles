package com.lmontes.finalapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lmontes.finalapp.adapter.HabitacionAdapter
import com.lmontes.finalapp.data.Habitacion
import com.lmontes.finalapp.data.Reserva
import java.util.ArrayList

class ListaHabitacionesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var habitacionAdapter: HabitacionAdapter
    private var habitaciones = obtenerListaHabitaciones().toMutableList() // Convertimos la lista a mutable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
            startActivityForResult(intent, REQUEST_CODE_RESERVA) // Esperamos el resultado
        }
        recyclerView.adapter = habitacionAdapter

    }


    // Método para recibir datos de DetalleHabitacionActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_RESERVA && resultCode == RESULT_OK) {
            val reservasActualizadas = data?.getParcelableArrayListExtra<Reserva>("reservasActualizadas")
            val nombreHabitacion = data?.getStringExtra("nombreHabitacion")

            val habitacionIndex = habitaciones.indexOfFirst { it.nombre == nombreHabitacion }

            if (habitacionIndex != -1 && reservasActualizadas != null) {
                // Actualizar la habitación con la nueva lista de reservas
                habitaciones[habitacionIndex] = habitaciones[habitacionIndex].copy(reservas = reservasActualizadas)

                // Notificar al adapter que los datos cambiaron
                habitacionAdapter.notifyItemChanged(habitacionIndex)
            }
        }
    }


    fun obtenerListaHabitaciones(): List<Habitacion> {
        return listOf(
            Habitacion("Habitación Simple", "Ideal para una persona", "https://picsum.photos/200/200", 1, "Piso 1, vista al jardín", "WiFi, escritorio, baño privado", 80.0,
                listOf(
                    Reserva("Juan Pérez", "2025-03-10", "2025-03-15"),
                    Reserva("Ana Gómez", "2025-03-20", "2025-03-25")
                )),
            Habitacion("Habitación Doble", "Dos camas individuales", "https://picsum.photos/200/200", 2, "Piso 2, vista a la ciudad", "Baño privado, desayuno, TV", 120.0,
                listOf(
                Reserva("Carlos Ramírez", "2025-03-12", "2025-03-18")
            )),
            Habitacion("Habitación Triple", "Para grupos pequeños", "https://picsum.photos/200/200", 3, "Piso 2, ventana a la calle", "Baño privado, desayuno, SmartTV", 100.0,
                listOf(
                    Reserva("Adriana Jáuregui", "2025-03-10", "2025-03-15"),
                    Reserva("Penélope García", "2025-03-20", "2025-03-25"),
                    Reserva("Amanda Pinedo", "2025-02-28", "2025-02-28"),
                )),
            Habitacion("Habitación Familiar", "Espacio para familias", "https://picsum.photos/200/200", 4, "Piso 3, vista al parque", "Baño privado, desayuno, sala de estar", 180.0,
                listOf(
                    Reserva("Adriana Jáuregui", "2025-03-10", "2025-03-15"),
                    Reserva("Penélope García", "2025-03-20", "2025-03-25"),
                    Reserva("Amanda Pinedo", "2025-02-28", "2025-02-28"),
                )
                ),
            Habitacion("Suite Ejecutiva", "Confort para negocios", "https://picsum.photos/200/200", 2, "Piso 5, área ejecutiva", "Escritorio, WiFi, minibar", 200.0,
                emptyList()),

        )
    }

    companion object {
        private const val REQUEST_CODE_RESERVA = 1001
    }
}