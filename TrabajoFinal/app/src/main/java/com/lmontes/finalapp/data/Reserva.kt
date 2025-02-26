package com.lmontes.finalapp.data

import android.os.Parcel
import android.os.Parcelable

data class Reserva(
    val nombreCliente: String,
    val fechaInicio: String,  // Formato: "YYYY-MM-DD"
    val fechaFin: String      // Formato: "YYYY-MM-DD"
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombreCliente)
        parcel.writeString(fechaInicio)
        parcel.writeString(fechaFin)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Reserva> {
        override fun createFromParcel(parcel: Parcel): Reserva = Reserva(parcel)
        override fun newArray(size: Int): Array<Reserva?> = arrayOfNulls(size)
    }
}
