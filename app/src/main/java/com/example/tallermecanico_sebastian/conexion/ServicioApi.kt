package com.example.tallermecanico_sebastian.conexion

import com.example.tallermecanico_sebastian.modelo.Empleado
import com.example.tallermecanico_sebastian.modelo.Acceso
import com.example.tallermecanico_sebastian.modelo.Averia
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.modelo.Permiso
import com.example.tallermecanico_sebastian.modelo.Pieza
import com.example.tallermecanico_sebastian.modelo.Rol
import com.example.tallermecanico_sebastian.modelo.Tipoaveria
import com.example.tallermecanico_sebastian.modelo.Tipopieza
import com.example.tallermecanico_sebastian.modelo.Vehiculo
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

    //AVERIA
    @GET("averias")
    suspend fun obtenerAverias(): List<Averia>

    @GET("averias/{id}")
    suspend fun obtenerAveriaId(
        @Path("id") id: String
    ): Averia

    @POST("averia")
    suspend fun insertarAveria(
        @Body averia: Averia
    ): Averia

    @PUT("averia/{id}")
    suspend fun actualizarAveria(
        @Path("id") id: String, @Body averia: Averia
    ): Averia

    @DELETE("averia/{id}")
    suspend fun eliminarAveria(
        @Path("id") id: String
    ): Averia

    //EMPLEADO
    @GET("empleados")
    suspend fun obtenerEmpleados(): List<Empleado>

    @GET("empleados/{id}")
    suspend fun obtenerEmpeladoId(
        @Path("id") id: String
    ): Empleado

    @POST("empleado")
    suspend fun insertarEmpleado(
        @Body empleado: Empleado
    ): Empleado

    @PUT("empleado/{id}")
    suspend fun actualizarEmpleado(
        @Path("id") id: String, @Body empleado: Empleado
    ): Empleado

    @DELETE("empleado/{id}")
    suspend fun eliminarEmpleado(
        @Path("id") id: String
    ): Empleado


    //CLIENTE
    @GET("clientes")
    suspend fun obtenerClientes(): List<Cliente>

    @GET("clientes/{id}")
    suspend fun obtenerClienteId(
        @Path("id") id: String
    ): Cliente

    @POST("cliente")
    suspend fun insertarCliente(
        @Body cliente: Cliente
    ): Cliente

    @PUT("cliente/{id}")
    suspend fun actualizarCliente(
        @Path("id") id: String, @Body cliente: Cliente
    ): Cliente

    @DELETE("cliente/{id}")
    suspend fun eliminarCliente(
        @Path("id") id: String
    ): Cliente


    //ROL
    @GET("roles")
    suspend fun obtenerRoles(): List<Rol>

    @GET("roles/{id}")
    suspend fun obtenerRolId(
        @Path("id") id: String
    ): Rol

    @POST("rol")
    suspend fun insertarRol(
        @Body rol: Rol
    ): Rol

    @PUT("rol/{id}")
    suspend fun actualizarRol(
        @Path("id") id: String, @Body rol: Rol
    ): Rol

    @DELETE("rol/{id}")
    suspend fun eliminarRol(
        @Path("id") id: String
    ): Rol


    //VEHICULO
    @GET("vehiculos")
    suspend fun obtenerVehiculos(): List<Vehiculo>

    @GET("vehiculos/{id}")
    suspend fun obtenerVehiculoId(
        @Path("id") id: String
    ): Vehiculo

    @POST("vehiculo")
    suspend fun insertarVehiculo(
        @Body vehiculo: Vehiculo
    ): Vehiculo

    @PUT("vehiculo/{id}")
    suspend fun actualizarVehiculo(
        @Path("id") id: String, @Body vehiculo: Vehiculo
    ): Vehiculo

    @DELETE("vehiculo/{id}")
    suspend fun eliminarVehiculo(
        @Path("id") id: String
    ): Vehiculo

    //PIEZA
    @GET("piezas")
    suspend fun obtenerPiezas(): List<Pieza>

    @GET("piezas/{id}")
    suspend fun obtenerPiezaId(
        @Path("id") id: String
    ): Pieza

    @POST("pieza")
    suspend fun insertarPieza(
        @Body pieza: Pieza
    ): Pieza

    @PUT("pieza/{id}")
    suspend fun actualizarPieza(
        @Path("id") id: String, @Body pieza: Pieza
    ): Pieza

    @DELETE("pieza/{id}")
    suspend fun eliminarPieza(
        @Path("id") id: String
    ): Pieza

    //TIPOPIEZA
    @GET("tipospieza")
    suspend fun obtenerTipopiezas(): List<Tipopieza>

    @GET("tipospieza/{id}")
    suspend fun obtenerTipospiezaId(
        @Path("id") id: String
    ): Tipopieza

    @POST("tipospieza")
    suspend fun insertarTipopieza(
        @Body tipopieza: Tipopieza
    ): Tipopieza

    @PUT("tipospieza/{id}")
    suspend fun actualizarTipopieza(
        @Path("id") id: String, @Body tipopieza: Tipopieza
    ): Tipopieza

    @DELETE("tipospieza/{id}")
    suspend fun eliminarTipopieza(
        @Path("id") id: String
    ): Tipopieza

    //TIPOSAVERIA
    @GET("tiposaveria")
    suspend fun obtenerTipoaveria(): List<Tipoaveria>

    @GET("tiposaveria/{id}")
    suspend fun obtenerTipoaveriaId(
        @Path("id") id: String
    ): Tipoaveria

    @POST("tiposaveria")
    suspend fun insertarTipoaveria(
        @Body tipoaveria: Tipoaveria
    ): Tipoaveria

    @PUT("tiposaveria/{id}")
    suspend fun actualizarTipoaveria(
        @Path("id") id: String, @Body tipoaveria: Tipoaveria
    ): Tipoaveria

    @DELETE("tiposaveria/{id}")
    suspend fun eliminarTipoaveria(
        @Path("id") id: String
    ): Tipoaveria

    //PERMISOS
    @GET("permisos")
    suspend fun obtenerPermisos(): List<Permiso>
}