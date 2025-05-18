package com.example.tallermecanico_sebastian.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Averia(
    @SerialName(value = "cod_averia")
    val cod_averia: Int = 0,
    @SerialName(value = "descripcion")
    val descripcion: String? = "", //OBLIGATORIO
    @SerialName(value = "precio")
    val precio: String? = "",
    @SerialName(value = "estado")
    val estado: String? = "",//OBLIGATORIO
    @SerialName(value = "cod_empleado")
    val cod_empleado: Int? = 0,
    @SerialName(value = "fecha_recepcion")
    val fecha_recepcion: String? = "",//OBLIGATORIO
    @SerialName(value = "fecha_resolucion")
    val fecha_resolucion: String? = "",
    @SerialName(value = "cod_cliente")
    val cod_cliente: Int? = 0,
    @SerialName(value = "observaciones")
    val observaciones: String? = "",
    @SerialName(value = "cod_vehiculo")
    val cod_vehiculo: Int? = 0,
    @SerialName(value = "empleado")
    val empleado: Empleado? = Empleado(),
    @SerialName(value = "cliente")
    val cliente: Cliente? = Cliente(),
    @SerialName(value = "vehiculo")
    val vehiculo: Vehiculo? = Vehiculo(),
    @SerialName(value = "averia_piezas")
    val averia_piezas: List<Averiapieza>?,
    @SerialName(value = "tipo_averias")
    val tipo_averias: List<Tipoaveria>?,
)