package com.example.tallermecanico_sebastian.datos.repos

import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.modelo.Acceso
import com.example.tallermecanico_sebastian.modelo.Empleado

interface EmpleadoRepositorio {
    suspend fun autenticarEmpleado(usuario: String, contrasenya: String): Empleado
    suspend fun obtenerEmpleados(): List<Empleado>
    suspend fun insertarEmpleado(empleado: Empleado): Empleado
    suspend fun actualizarEmpleado(id: String, empleado: Empleado): Empleado
    suspend fun eliminarEmpleado(id: String): Empleado
}

class ConexionEmpleadoRepositorio(
    private val servicioApi: ServicioApi
) : EmpleadoRepositorio {
    override suspend fun autenticarEmpleado(usuario: String, contrasenya: String): Empleado {
        val credenciales = Acceso(usuario, contrasenya)
        return servicioApi.autenticarUsuario(credenciales)
    }

    override suspend fun obtenerEmpleados(): List<Empleado> = servicioApi.obtenerEmpleados()

    override suspend fun insertarEmpleado(empleado: Empleado): Empleado =
        servicioApi.insertarEmpleado(empleado)

    override suspend fun actualizarEmpleado(id: String, empleado: Empleado): Empleado =
        servicioApi.actualizarEmpleado(id, empleado)

    override suspend fun eliminarEmpleado(id: String): Empleado = servicioApi.eliminarEmpleado(id)
}