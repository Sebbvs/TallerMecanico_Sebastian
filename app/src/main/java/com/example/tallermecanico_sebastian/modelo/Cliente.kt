package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cliente(
    @SerialName(value = "cod_cliente")
    val cod_cliente: String = "",
    @SerialName(value = "nombre")
    val nombre: String = "",
    @SerialName(value = "apellido1")
    val apellido1: String = "",
    @SerialName(value = "apellido2")
    val apellido2: String = "",
    @SerialName(value = "email")
    val email: String = "",
    @SerialName(value = "direccion")
    val direccion: String = "",
)
