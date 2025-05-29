package com.example.tallermecanico_sebastian.datos.repos

import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.modelo.Permiso

interface PermisoRepositorio {
    suspend fun obtenerPermisos(): List<Permiso>
}

class ConexionPermisoRepositorio(
    private val servicioApi: ServicioApi
) : PermisoRepositorio {
    override suspend fun obtenerPermisos(): List<Permiso> = servicioApi.obtenerPermisos()
}