package com.example.tallermecanico_sebastian.datos.repos

import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.modelo.Vehiculo

interface VehiculoRepositorio {
    suspend fun obtenerVehiculos(): List<Vehiculo>
    suspend fun insertarVehiculo(vehiculo: Vehiculo): Vehiculo
    suspend fun actualizarVehiculo(id: String, vehiculo: Vehiculo): Vehiculo
    suspend fun eliminarVehiculo(id: String): Vehiculo
}

class ConexionVehiculoRepositorio(
    private val servicioApi: ServicioApi
) : VehiculoRepositorio {
    override suspend fun obtenerVehiculos(): List<Vehiculo> = servicioApi.obtenerVehiculos()

    override suspend fun insertarVehiculo(vehiculo: Vehiculo): Vehiculo =
        servicioApi.insertarVehiculo(vehiculo)

    override suspend fun actualizarVehiculo(id: String, vehiculo: Vehiculo): Vehiculo =
        servicioApi.actualizarVehiculo(id, vehiculo)

    override suspend fun eliminarVehiculo(id: String): Vehiculo = servicioApi.eliminarVehiculo(id)
}