package com.example.tallermecanico_sebastian.datos.repos

import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.modelo.Tipoaveria
import com.example.tallermecanico_sebastian.modelo.Vehiculo

interface TipoaveriaRepositorio {
    suspend fun obtenerTipoaveria(): List<Tipoaveria>
    suspend fun insertarTipoaveria(tipoaveria: Tipoaveria): Tipoaveria
    suspend fun actualizarTipoaveria(id: Int, tipoaveria: Tipoaveria): Tipoaveria
    suspend fun eliminarTipoaveria(id: Int): Tipoaveria
}

class ConexionTipoaveriaRepositorio(
    private val servicioApi: ServicioApi
) : TipoaveriaRepositorio {
    override suspend fun obtenerTipoaveria(): List<Tipoaveria> = servicioApi.obtenerTipoaveria()

    override suspend fun insertarTipoaveria(tipoaveria: Tipoaveria): Tipoaveria =
        servicioApi.insertarTipoaveria(tipoaveria)

    override suspend fun actualizarTipoaveria(id: Int, tipoaveria: Tipoaveria): Tipoaveria =
        servicioApi.actualizarTipoaveria(id, tipoaveria)

    override suspend fun eliminarTipoaveria(id: Int): Tipoaveria =
        servicioApi.eliminarTipoaveria(id)
}