package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Permiso(
    @SerialName(value = "cod_permiso")
    val cod_permiso: Int? = 0,
    @SerialName(value = "descripcion")
    val descripcion: String? = "",
)
