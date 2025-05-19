package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rol(
    @SerialName(value = "cod_rol")
    val cod_rol: Int? = 0,
    @SerialName(value = "nombre")
    val nombre: String? = "",
)
