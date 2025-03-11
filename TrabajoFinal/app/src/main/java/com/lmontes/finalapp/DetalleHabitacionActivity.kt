package com.lmontes.finalapp

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lmontes.finalapp.data.Reserva
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetalleHabitacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_habitacion)

        // Referencias a los elementos de la UI
        val imagenHabitacion: ImageView = findViewById(R.id.imagenDetalleHabitacion)
        val nombreHabitacion: TextView = findViewById(R.id.nombreDetalleHabitacion)
        val descripcionHabitacion: TextView = findViewById(R.id.descripcionDetalleHabitacion)
        val capacidad: TextView = findViewById(R.id.capacidadHabitacion)
        val ubicacion: TextView = findViewById(R.id.ubicacionHabitacion)
        val incluye: TextView = findViewById(R.id.incluyeHabitacion)
        val costo: TextView = findViewById(R.id.costoHabitacion)
        //val calendarView: MaterialCalendarView = findViewById(R.id.materialCalendarView)

        val listaReservas: TextView = findViewById(R.id.listaReservas)

        val fechaInicioReserva: EditText = findViewById(R.id.fechaInicioReserva)
        val fechaFinReserva: EditText = findViewById(R.id.fechaFinReserva)

        val botonReservar: Button = findViewById(R.id.botonReservar)

        // Obtener datos de la intención
        val nombre = intent.getStringExtra("nombre")
        val descripcion = intent.getStringExtra("descripcion")
        val capacidadTexto = intent.getStringExtra("capacidad")
        val ubicacionTexto = intent.getStringExtra("ubicacion")
        val incluyeTexto = intent.getStringExtra("incluye")
        val costoTexto = intent.getStringExtra("costo")
        val imagenUrl = intent.getStringExtra("imagenUrl")

        // Recuperar la lista de reservas
        val reservas: ArrayList<Reserva>? = intent.getParcelableArrayListExtra("reservas")

        // Asignar valores a los componentes
        nombreHabitacion.text = nombre
        descripcionHabitacion.text = descripcion
        capacidad.text = "Capacidad: $capacidadTexto personas"
        ubicacion.text = "Ubicación: $ubicacionTexto"
        incluye.text = "Incluye: $incluyeTexto"
        costo.text = "Costo:S/ $costoTexto "

        Glide.with(this).load(imagenUrl).placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder).diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imagenHabitacion)




        listaReservas.text = reservas?.joinToString("\n") {
            "Reserva: ${it.fechaInicio} - ${it.fechaFin}"
        } ?: "No hay reservas registradas."


        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        fechaInicioReserva.setOnClickListener { showDatePicker(fechaInicioReserva) }
        fechaFinReserva.setOnClickListener { showDatePicker(fechaFinReserva) }


        /*
        // Marcar fechas reservadas en el calendario
        reservas?.let {
            val reservedDates = getReservedDates(it)
            calendarView.addDecorator(ReservedDatesDecorator(reservedDates))
        }
         */


        // Configurar la acción del botón de reserva (puedes personalizarlo)
        botonReservar.setOnClickListener {
            val inicio = fechaInicioReserva.text.toString()
            val fin = fechaFinReserva.text.toString()

            if (inicio.isEmpty() || fin.isEmpty()) {
                Toast.makeText(this, "Selecciona ambas fechas", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val inicioDate = dateFormat.parse(inicio)
            val finDate = dateFormat.parse(fin)

            if (inicioDate == null || finDate == null) {
                Toast.makeText(this, "Formato de fecha inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (inicioDate.after(finDate)) {
                Toast.makeText(this, "La fecha de inicio no puede ser posterior a la fecha de fin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificar conflictos con reservas existentes
            val conflicto = reservas?.any { reserva ->
                val reservaInicio = dateFormat.parse(reserva.fechaInicio)
                val reservaFin = dateFormat.parse(reserva.fechaFin)

                if (reservaInicio != null && reservaFin != null) {
                    !(finDate.before(reservaInicio) || inicioDate.after(reservaFin))
                } else {
                    false
                }
            } == true

            if (conflicto) {
                Toast.makeText(this, "¡Error! Fechas en conflicto con una reserva existente.", Toast.LENGTH_LONG).show()
            } else {
                // **Agregar la nueva reserva**
                val nuevaReserva = Reserva("Usuario Prueba", inicio, fin)
                val nuevasReservas = ArrayList(reservas ?: emptyList())
                nuevasReservas.add(nuevaReserva)

                // **Enviar la lista actualizada de reservas a `ListaHabitacionesActivity`**
                val intent = Intent()
                intent.putParcelableArrayListExtra("reservasActualizadas", nuevasReservas)
                intent.putExtra("nombreHabitacion", nombre)
                setResult(RESULT_OK, intent)

                // **Volver a la pantalla anterior**
                Toast.makeText(this, "Reserva agregada exitosamente.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }



    }


    private fun showDatePicker(editText: EditText) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(this, { _, year, month, day ->
            editText.setText("$year-${month + 1}-$day")
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }


    /*
     // Función para convertir la lista de reservas en fechas destacadas
    private fun getReservedDates(reservas: List<Reserva>): List<CalendarDay> {
        val dateList = mutableListOf<CalendarDay>()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        for (reserva in reservas) {
            val startDate = sdf.parse(reserva.fechaInicio)
            val endDate = sdf.parse(reserva.fechaFin)

            if (startDate != null && endDate != null) {
                val calendar = Calendar.getInstance()
                calendar.time = startDate

                while (!calendar.time.after(endDate)) {
                    val day = CalendarDay.from(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH) + 1, // Calendar.MONTH es 0-based
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    dateList.add(day)
                    calendar.add(Calendar.DAY_OF_MONTH, 1)
                }
            }
        }
        return dateList
    }

     */


}