package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cliente(
    @SerialName(value = "id")
    val id: String = ""
)