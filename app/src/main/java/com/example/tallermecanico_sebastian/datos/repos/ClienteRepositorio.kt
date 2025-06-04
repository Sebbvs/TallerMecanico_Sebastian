package com.example.tallermecanico_sebastian.datos.repos

import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.modelo.Cliente

interface ClienteRepositorio {
    suspend fun obtenerClientes(): List<Cliente>
    suspend fun insertarCliente(cliente: Cliente): Cliente
    suspend fun actualizarCliente(id: Int, cliente: Cliente): Cliente
    suspend fun eliminarCliente(id: Int): Cliente
}

class ConexionClienteRepositorio(
    private val servicioApi: ServicioApi
) : ClienteRepositorio {
    override suspend fun obtenerClientes(): List<Cliente> = servicioApi.obtenerClientes()

    override suspend fun insertarCliente(cliente: Cliente): Cliente =
        servicioApi.insertarCliente(cliente)

    override suspend fun actualizarCliente(id: Int, cliente: Cliente): Cliente =
        servicioApi.actualizarCliente(id, cliente)

    override suspend fun eliminarCliente(id: Int): Cliente = servicioApi.eliminarCliente(id)

}