package com.example.tallermecanico_sebastian.datos.repos

import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.modelo.Averiapieza

interface AveriapiezaRepositorio {
    suspend fun insertarAveriapieza(averiapieza: Averiapieza): Averiapieza
    suspend fun eliminarAveriapieza(idA: Int, idP: Int): Averiapieza
}

class ConexionAveriapiezaRepositorio(
    private val servicioApi: ServicioApi
) : AveriapiezaRepositorio {
    override suspend fun insertarAveriapieza(averiapieza: Averiapieza): Averiapieza =
        servicioApi.insertarAveriapieza(averiapieza)

    override suspend fun eliminarAveriapieza(idA: Int, idP: Int): Averiapieza =
        servicioApi.eliminarAveriapieza(idA, idP)
}