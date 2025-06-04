package com.example.tallermecanico_sebastian.datos.repos

import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.modelo.Averiapieza

interface AveriapiezaRepositorio {
    suspend fun obtenerAveriapieza(): List<Averiapieza>
    suspend fun insertarAveriapieza(averiapieza: Averiapieza): Averiapieza
    suspend fun actualizarAveriapieza(id: Int, averiapieza: Averiapieza): Averiapieza
    suspend fun eliminarAveriapieza(id: Int): Averiapieza
}

class ConexionAveriapiezaRepositorio(
    private val servicioApi: ServicioApi
) : AveriapiezaRepositorio {
    override suspend fun obtenerAveriapieza(): List<Averiapieza> = servicioApi.obtenerAveriapieza()

    override suspend fun insertarAveriapieza(averiapieza: Averiapieza): Averiapieza =
        servicioApi.insertarAveriapieza(averiapieza)

    override suspend fun actualizarAveriapieza(id: Int, averiapieza: Averiapieza): Averiapieza =
        servicioApi.actualizarAveriapieza(id, averiapieza)

    override suspend fun eliminarAveriapieza(id: Int): Averiapieza =
        servicioApi.eliminarAveriapieza(id)
}