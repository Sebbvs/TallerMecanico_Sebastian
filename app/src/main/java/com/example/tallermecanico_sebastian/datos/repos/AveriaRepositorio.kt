package com.example.tallermecanico_sebastian.datos.repos

import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.modelo.Averia

interface AveriaRepositorio {
    suspend fun obtenerAverias(): List<Averia>
    suspend fun insertarAveria(averia: Averia): Averia
    suspend fun actualizarAveria(id: Int, averia: Averia): Averia
    suspend fun eliminarAveria(id: Int): Averia
}

class ConexionAveriaRepositorio(
    private val servicioApi: ServicioApi
) : AveriaRepositorio {
    override suspend fun obtenerAverias(): List<Averia> = servicioApi.obtenerAverias()

    override suspend fun insertarAveria(averia: Averia): Averia = servicioApi.insertarAveria(averia)

    override suspend fun actualizarAveria(id: Int, averia: Averia): Averia =
        servicioApi.actualizarAveria(id, averia)

    override suspend fun eliminarAveria(id: Int): Averia = servicioApi.eliminarAveria(id)
}