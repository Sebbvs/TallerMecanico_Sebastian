package com.example.tallermecanico_sebastian.datos.repos

import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.modelo.Averiapieza
import com.example.tallermecanico_sebastian.modelo.Averiatipoaveria

interface AveriatipoaveriaRepositorio {
    suspend fun insertarAveriatipoaveria(averiatipoaveria: Averiatipoaveria): Averiatipoaveria
    suspend fun eliminarAveriatipoaveria(idA: Int, idT: Int): Averiatipoaveria
}

class ConexionAveriatipoaveriaRepositorio(
    private val servicioApi: ServicioApi
) : AveriatipoaveriaRepositorio {
    override suspend fun insertarAveriatipoaveria(averiatipoaveria: Averiatipoaveria): Averiatipoaveria =
        servicioApi.insertarAveriatipoaveria(averiatipoaveria)

    override suspend fun eliminarAveriatipoaveria(idA: Int, idT: Int): Averiatipoaveria =
        servicioApi.eliminarAveriatipoaveria(idA, idT)
}