package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vehiculo(
    @SerialName(value = "cod_vehiculo")
    val cod_vehiculo: Int = 0,
    @SerialName(value = "marca")
    val marca: String = "",
    @SerialName(value = "modelo")
    val modelo: String = "",
    @SerialName(value = "especificaciones")
    val especificaciones: String = "",
    @SerialName(value = "matricula")
    val matricula: String = "",
    @SerialName(value = "vin")
    val vin: String = "",
    @SerialName(value = "cod_cliente")
    val cod_cliente: Int = 0,
    @SerialName(value = "cliente")
    val cliente: Cliente,
)
