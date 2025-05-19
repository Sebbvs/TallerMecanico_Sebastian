package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pieza(
    @SerialName(value = "cod_pieza")
    val cod_pieza: Int? = 0,
    @SerialName(value = "descripcion")
    val descripcion: String? = "",
    @SerialName(value = "cantidad")
    val cantidad: Int? = 0,
    @SerialName(value = "cod_tipo")
    val cod_tipo: Int? = 0,
    @SerialName(value = "tipo_pieza")
    val tipo_pieza: Tipopieza? = null,
)
