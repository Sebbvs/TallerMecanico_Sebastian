package com.example.tallermecanico_sebastian.conexion

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServicioApi {
    //    AVERIA
    @GET("averias")
    suspend fun obtenerAverias(): List<Averia>

    @POST("averia")
    suspend fun insertarAveria(
        @Body averia: Averia
    ): Averia

    @PUT("averia/{id}")
    suspend fun actualizarAveria(
        @Path("id") id: String,
        @Body averia: Averia
    ): Averia

    @DELETE("profesor/{id}")
    suspend fun eliminarAveria(
        @Path("id") id: String
    ): Averia

    //    COCHE
    @GET("coches")
    suspend fun obtenerCoches(): List<Coche>

    @POST("coche")
    suspend fun insertarAveria(
        @Body coche: Coche
    ): Coche

    @PUT("coche/{id}")
    suspend fun actualizarCoche(
        @Path("id") id: String,
        @Body coche: Coche
    ): Coche

    @DELETE("coche/{id}")
    suspend fun eliminarCoche(
        @Path("id") id: String
    ): Coche

    //    CLIENTE
    @GET("clientes")
    suspend fun obtenerClientes(): List<Cliente>

    @POST("cliente")
    suspend fun insertarCliente(
        @Body cliente: Cliente
    ): Cliente

    @PUT("cliente/{id}")
    suspend fun actualizarCliente(
        @Path("id") id: String,
        @Body cliente: Cliente
    ): Cliente

    @DELETE("cliente/{id}")
    suspend fun eliminarCliente(
        @Path("id") id: String
    ): Cliente

    //    PAGOS
    @GET("pagos")
    suspend fun obtenerPagos(): List<Pago>

    @POST("pago")
    suspend fun insertarPago(
        @Body pago: Pago
    ): Pago

    @PUT("averia/{id}")
    suspend fun actualizarPago(
        @Path("id") id: String,
        @Body pago: Pago
    ): Pago

    @DELETE("profesor/{id}")
    suspend fun eliminarPago(
        @Path("id") id: String
    ): Pago
}