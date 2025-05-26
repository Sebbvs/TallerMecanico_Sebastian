package com.example.tallermecanico_sebastian.datos.repos

import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.modelo.Pieza

interface PiezaRepositorio {
    suspend fun obtenerPiezas(): List<Pieza>
    suspend fun insertarPieza(pieza: Pieza): Pieza
    suspend fun actualizarPieza(id: String, pieza: Pieza): Pieza
    suspend fun eliminarPieza(id: String): Pieza
}

class ConexionPiezaRepositorio(
    private val servicioApi: ServicioApi
) : PiezaRepositorio {
    override suspend fun obtenerPiezas(): List<Pieza> = servicioApi.obtenerPiezas()

    override suspend fun insertarPieza(pieza: Pieza): Pieza =
        servicioApi.insertarPieza(pieza)

    override suspend fun actualizarPieza(id: String, pieza: Pieza): Pieza =
        servicioApi.actualizarPieza(id, pieza)

    override suspend fun eliminarPieza(id: String): Pieza = servicioApi.eliminarPieza(id)

}