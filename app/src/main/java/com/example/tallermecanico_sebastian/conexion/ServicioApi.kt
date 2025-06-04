package com.example.tallermecanico_sebastian.conexion

import com.example.tallermecanico_sebastian.modelo.Empleado
import com.example.tallermecanico_sebastian.modelo.Acceso
import com.example.tallermecanico_sebastian.modelo.Averia
import com.example.tallermecanico_sebastian.modelo.Averiapieza
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
        @Path("id") id: Int
    ): Averia

    @POST("averia")
    suspend fun insertarAveria(
        @Body averia: Averia
    ): Averia

    @PUT("averia/{id}")
    suspend fun actualizarAveria(
        @Path("id") id: Int, @Body averia: Averia
    ): Averia

    @DELETE("averia/{id}")
    suspend fun eliminarAveria(
        @Path("id") id: Int
    ): Averia

    //EMPLEADO
    @GET("empleados")
    suspend fun obtenerEmpleados(): List<Empleado>

    @GET("empleados/{id}")
    suspend fun obtenerEmpeladoId(
        @Path("id") id: Int
    ): Empleado

    @POST("empleado")
    suspend fun insertarEmpleado(
        @Body empleado: Empleado
    ): Empleado

    @PUT("empleado/{id}")
    suspend fun actualizarEmpleado(
        @Path("id") id: Int, @Body empleado: Empleado
    ): Empleado

    @DELETE("empleado/{id}")
    suspend fun eliminarEmpleado(
        @Path("id") id: Int
    ): Empleado


    //CLIENTE
    @GET("clientes")
    suspend fun obtenerClientes(): List<Cliente>

    @GET("clientes/{id}")
    suspend fun obtenerClienteId(
        @Path("id") id: Int
    ): Cliente

    @POST("cliente")
    suspend fun insertarCliente(
        @Body cliente: Cliente
    ): Cliente

    @PUT("cliente/{id}")
    suspend fun actualizarCliente(
        @Path("id") id: Int, @Body cliente: Cliente
    ): Cliente

    @DELETE("cliente/{id}")
    suspend fun eliminarCliente(
        @Path("id") id: Int
    ): Cliente


    //ROL
    @GET("roles")
    suspend fun obtenerRoles(): List<Rol>

    @GET("roles/{id}")
    suspend fun obtenerRolId(
        @Path("id") id: Int
    ): Rol

    @POST("rol")
    suspend fun insertarRol(
        @Body rol: Rol
    ): Rol

    @PUT("rol/{id}")
    suspend fun actualizarRol(
        @Path("id") id: Int, @Body rol: Rol
    ): Rol

    @DELETE("rol/{id}")
    suspend fun eliminarRol(
        @Path("id") id: Int
    ): Rol


    //VEHICULO
    @GET("vehiculos")
    suspend fun obtenerVehiculos(): List<Vehiculo>

    @GET("vehiculos/{id}")
    suspend fun obtenerVehiculoId(
        @Path("id") id: Int
    ): Vehiculo

    @POST("vehiculo")
    suspend fun insertarVehiculo(
        @Body vehiculo: Vehiculo
    ): Vehiculo

    @PUT("vehiculo/{id}")
    suspend fun actualizarVehiculo(
        @Path("id") id: Int, @Body vehiculo: Vehiculo
    ): Vehiculo

    @DELETE("vehiculo/{id}")
    suspend fun eliminarVehiculo(
        @Path("id") id: Int
    ): Vehiculo

    //PIEZA
    @GET("piezas")
    suspend fun obtenerPiezas(): List<Pieza>

    @GET("piezas/{id}")
    suspend fun obtenerPiezaId(
        @Path("id") id: Int
    ): Pieza

    @POST("pieza")
    suspend fun insertarPieza(
        @Body pieza: Pieza
    ): Pieza

    @PUT("pieza/{id}")
    suspend fun actualizarPieza(
        @Path("id") id: Int, @Body pieza: Pieza
    ): Pieza

    @DELETE("pieza/{id}")
    suspend fun eliminarPieza(
        @Path("id") id: Int
    ): Pieza

    //TIPOPIEZA
    @GET("tipospieza")
    suspend fun obtenerTipopiezas(): List<Tipopieza>

    @GET("tipospieza/{id}")
    suspend fun obtenerTipospiezaId(
        @Path("id") id: Int
    ): Tipopieza

    @POST("tipospieza")
    suspend fun insertarTipopieza(
        @Body tipopieza: Tipopieza
    ): Tipopieza

    @PUT("tipospieza/{id}")
    suspend fun actualizarTipopieza(
        @Path("id") id: Int, @Body tipopieza: Tipopieza
    ): Tipopieza

    @DELETE("tipospieza/{id}")
    suspend fun eliminarTipopieza(
        @Path("id") id: Int
    ): Tipopieza

    //TIPOSAVERIA
    @GET("tiposaveria")
    suspend fun obtenerTipoaveria(): List<Tipoaveria>

    @GET("tiposaveria/{id}")
    suspend fun obtenerTipoaveriaId(
        @Path("id") id: Int
    ): Tipoaveria

    @POST("tiposaveria")
    suspend fun insertarTipoaveria(
        @Body tipoaveria: Tipoaveria
    ): Tipoaveria

    @PUT("tiposaveria/{id}")
    suspend fun actualizarTipoaveria(
        @Path("id") id: Int, @Body tipoaveria: Tipoaveria
    ): Tipoaveria

    @DELETE("tiposaveria/{id}")
    suspend fun eliminarTipoaveria(
        @Path("id") id: Int
    ): Tipoaveria

    //AVERIAPIEZA
    @GET("averiapieza")
    suspend fun obtenerAveriapieza(): List<Averiapieza>

    @GET("averiapieza/{id}")
    suspend fun obtenerAveriapiezaId(
        @Path("id") id: Int
    ): Averiapieza

    @POST("averiapieza")
    suspend fun insertarAveriapieza(
        @Body averiapieza: Averiapieza
    ): Averiapieza

    @PUT("averiapieza/{id}")
    suspend fun actualizarAveriapieza(
        @Path("id") id: Int, @Body averiapieza: Averiapieza
    ): Averiapieza

    @DELETE("averiapieza/{id}")
    suspend fun eliminarAveriapieza(
        @Path("id") id: Int
    ): Averiapieza

    //PERMISOS
    @GET("permisos")
    suspend fun obtenerPermisos(): List<Permiso>
}