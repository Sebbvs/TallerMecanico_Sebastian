package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Averiatipoaveria(
    @SerialName(value = "cod_averia")
    val cod_averia: Int? = 0,
    @SerialName(value = "cod_tipo")
    val cod_tipo: Int? = 0,
)
