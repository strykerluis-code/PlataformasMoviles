package com.lmontes.finalapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lmontes.finalapp.R
import com.lmontes.finalapp.data.Habitacion

class HabitacionAdapter(
    private val listaHabitaciones: List<Habitacion>,
    private val onClick: (Habitacion) -> Unit
) : RecyclerView.Adapter<HabitacionAdapter.HabitacionViewHolder>() {

    class HabitacionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.nombreHabitacion)
        val descripcion: TextView = view.findViewById(R.id.descripcionHabitacion)
        val imagen: ImageView = view.findViewById(R.id.imagenHabitacion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitacionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habitacion, parent, false)
        return HabitacionViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitacionViewHolder, position: Int) {
        val habitacion = listaHabitaciones[position]
        holder.nombre.text = habitacion.nombre
        holder.descripcion.text = habitacion.descripcion
        Glide.with(holder.itemView.context)
            .load(habitacion.imagenUrl)
            .placeholder(R.drawable.ic_placeholder) // Imagen de carga
            .error(R.drawable.ic_placeholder) // Imagen si falla la carga
            .diskCacheStrategy(DiskCacheStrategy.ALL) // Habilita caché
            .into(holder.imagen)

        holder.itemView.setOnClickListener { onClick(habitacion) }
    }

    override fun getItemCount(): Int = listaHabitaciones.size
}
