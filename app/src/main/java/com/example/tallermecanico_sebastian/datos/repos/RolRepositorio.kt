package com.example.tallermecanico_sebastian.datos.repos

import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.modelo.Rol

interface RolRepositorio {
    suspend fun obtenerRoles(): List<Rol>
    suspend fun insertarRol(rol: Rol): Rol
    suspend fun actualizarRol(id: String, rol: Rol): Rol
    suspend fun eliminarRol(id: String): Rol
}

class ConexionRolRepositorio(
    private val servicioApi: ServicioApi
) : RolRepositorio {
    override suspend fun obtenerRoles(): List<Rol> = servicioApi.obtenerRoles()

    override suspend fun insertarRol(rol: Rol): Rol = servicioApi.insertarRol(rol)

    override suspend fun actualizarRol(id: String, rol: Rol): Rol = actualizarRol(id, rol)

    override suspend fun eliminarRol(id: String): Rol = eliminarRol(id)

}