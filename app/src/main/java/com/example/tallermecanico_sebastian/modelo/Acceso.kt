package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Acceso(
    @SerialName(value = "usuario")
    val usuario: String? = "",
    @SerialName(value = "contraseña")
    val contrasenya: String? = "",
)