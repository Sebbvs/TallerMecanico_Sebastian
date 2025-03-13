package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tipoaveria(
    @SerialName(value = "cod_tipo_averia")
    val cod_tipo_averia: Int = 0,
    @SerialName(value = "nombre")
    val nombre: String = "",
//    PIVOT?
)
