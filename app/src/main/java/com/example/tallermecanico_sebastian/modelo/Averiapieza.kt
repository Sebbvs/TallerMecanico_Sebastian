package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Averiapieza(
    @SerialName(value = "cod_averia")
    val cod_averia: Int = 0,
    @SerialName(value = "cod_pieza")
    val cod_pieza: Int = 0,
    @SerialName(value = "cantidad")
    val cantidad: Int = 0,
)
