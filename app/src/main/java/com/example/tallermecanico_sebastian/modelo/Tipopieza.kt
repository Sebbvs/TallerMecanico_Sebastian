package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tipopieza(
    @SerialName(value = "cod_tipo_pieza")
    val cod_tipo_pieza: Int = 0,
    @SerialName(value = "nombre")
    val nombre: String = "",
)