package com.example.tallermecanico_sebastian.datos.repos

import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.modelo.Vehiculo

interface VehiculoRepositorio {
    suspend fun obtenerVehiculos(): List<Vehiculo>
    suspend fun insertarVehiculo(vehiculo: Vehiculo): Vehiculo
    suspend fun actualizarVehiculo(id: Int, vehiculo: Vehiculo): Vehiculo
    suspend fun eliminarVehiculo(id: Int): Vehiculo
}

class ConexionVehiculoRepositorio(
    private val servicioApi: ServicioApi
) : VehiculoRepositorio {
    override suspend fun obtenerVehiculos(): List<Vehiculo> = servicioApi.obtenerVehiculos()

    override suspend fun insertarVehiculo(vehiculo: Vehiculo): Vehiculo =
        servicioApi.insertarVehiculo(vehiculo)

    override suspend fun actualizarVehiculo(id: Int, vehiculo: Vehiculo): Vehiculo =
        servicioApi.actualizarVehiculo(id, vehiculo)

    override suspend fun eliminarVehiculo(id: Int): Vehiculo = servicioApi.eliminarVehiculo(id)
}