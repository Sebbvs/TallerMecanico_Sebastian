package com.example.tallermecanico_sebastian.conexion

import com.example.tallermecanico_sebastian.modelo.Empleado
import com.example.tallermecanico_sebastian.modelo.Acceso
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServicioApi {
    //LOGIN
    @POST("autenticar")
    suspend fun autenticarUsuario(
        @Body acceso: Acceso,
    ): Empleado

    //EMPLEADO
    @GET("empleados")
    suspend fun obtenerEmpleados(): List<Empleado>

    @POST("empleado")
    suspend fun insertarEmpleado(
        @Body empleado: Empleado
    ): Empleado

    @PUT("empleado/{id}")
    suspend fun actualizarEmpleado(
        @Path("id") id: String,
        @Body empleado: Empleado
    ): Empleado

    @DELETE("empleado/{id}")
    suspend fun eliminarEmpleado(
        @Path("id") id: String
    ): Empleado

    //AVERIA

    //CLIENTE

    //ROL

    //VEHICULO
}