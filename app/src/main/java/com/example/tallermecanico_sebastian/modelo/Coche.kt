package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coche(
    @SerialName(value = "id")
    val id: String = ""
)