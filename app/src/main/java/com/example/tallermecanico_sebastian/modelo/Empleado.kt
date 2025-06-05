package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Empleado(
    @SerialName(value = "cod_empleado")
    val cod_empleado: Int? = 0,
    @SerialName(value = "nombre")
    val nombre: String? = "",//OBLIGATORIO
    @SerialName(value = "apellido1")
    val apellido1: String? = "",
    @SerialName(value = "apellido2")
    val apellido2: String? = "",
    @SerialName(value = "email")
    val email: String? = "",
    @SerialName(value = "direccion")
    val direccion: String? = "",
    @SerialName(value = "usuario")
    val usuario: String? = "",//OBLIGATORIO
    @SerialName(value = "contraseña")
    val contrasenya: String? = "",//OBLIGATORIO
    @SerialName(value = "cod_rol")
    val cod_rol: Int? = 0,
    @SerialName(value = "rol")
    val rol: Rol? = null,
//    Rol(),
)
