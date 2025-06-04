package com.example.tallermecanico_sebastian.datos.repos

import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.modelo.Tipopieza

interface TipopiezaRepositorio {
    suspend fun obtenerTipopieza(): List<Tipopieza>
    suspend fun insertarTipopieza(tipopieza: Tipopieza): Tipopieza
    suspend fun actualizarTipopieza(id: Int, tipopieza: Tipopieza): Tipopieza
    suspend fun eliminarTipopieza(id: Int): Tipopieza
}

class ConexionTipopiezaRepositorio(
    private val servicioApi: ServicioApi
) : TipopiezaRepositorio {
    override suspend fun obtenerTipopieza(): List<Tipopieza> = servicioApi.obtenerTipopiezas()

    override suspend fun insertarTipopieza(tipopieza: Tipopieza): Tipopieza =
        servicioApi.insertarTipopieza(tipopieza)

    override suspend fun actualizarTipopieza(id: Int, tipopieza: Tipopieza): Tipopieza =
        actualizarTipopieza(id, tipopieza)

    override suspend fun eliminarTipopieza(id: Int): Tipopieza = eliminarTipopieza(id)

}