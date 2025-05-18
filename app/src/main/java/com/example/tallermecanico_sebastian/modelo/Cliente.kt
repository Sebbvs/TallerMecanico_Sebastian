package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cliente(
    @SerialName(value = "cod_cliente")
    val cod_cliente: Int = 0,
    @SerialName(value = "nombre")
    val nombre: String? = "",//OBLIGATORIO
    @SerialName(value = "apellido1")
    val apellido1: String? = "",//OBLIGATORIO
    @SerialName(value = "apellido2")
    val apellido2: String? = "",
    @SerialName(value = "email")
    val email: String? = "",//OBLIGATORIO
    @SerialName(value = "direccion")
    val direccion: String? = "",
)
