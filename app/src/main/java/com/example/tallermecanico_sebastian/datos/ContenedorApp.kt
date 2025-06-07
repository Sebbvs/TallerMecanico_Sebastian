package com.example.tallermecanico_sebastian.datos

import android.content.Context
import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.datos.repos.AveriaRepositorio
import com.example.tallermecanico_sebastian.datos.repos.AveriapiezaRepositorio
import com.example.tallermecanico_sebastian.datos.repos.AveriatipoaveriaRepositorio
import com.example.tallermecanico_sebastian.datos.repos.ClienteRepositorio
import com.example.tallermecanico_sebastian.datos.repos.ConexionAveriaRepositorio
import com.example.tallermecanico_sebastian.datos.repos.ConexionAveriapiezaRepositorio
import com.example.tallermecanico_sebastian.datos.repos.ConexionAveriatipoaveriaRepositorio
import com.example.tallermecanico_sebastian.datos.repos.ConexionClienteRepositorio
import com.example.tallermecanico_sebastian.datos.repos.ConexionEmpleadoRepositorio
import com.example.tallermecanico_sebastian.datos.repos.ConexionPermisoRepositorio
import com.example.tallermecanico_sebastian.datos.repos.ConexionPiezaRepositorio
import com.example.tallermecanico_sebastian.datos.repos.ConexionRolRepositorio
import com.example.tallermecanico_sebastian.datos.repos.ConexionTipoaveriaRepositorio
import com.example.tallermecanico_sebastian.datos.repos.ConexionTipopiezaRepositorio
import com.example.tallermecanico_sebastian.datos.repos.ConexionVehiculoRepositorio
import com.example.tallermecanico_sebastian.datos.repos.EmpleadoRepositorio
import com.example.tallermecanico_sebastian.datos.repos.PermisoRepositorio
import com.example.tallermecanico_sebastian.datos.repos.PiezaRepositorio
import com.example.tallermecanico_sebastian.datos.repos.RolRepositorio
import com.example.tallermecanico_sebastian.datos.repos.TipoaveriaRepositorio
import com.example.tallermecanico_sebastian.datos.repos.TipopiezaRepositorio
import com.example.tallermecanico_sebastian.datos.repos.VehiculoRepositorio
import com.example.tallermecanico_sebastian.modelo.Tipoaveria
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContenedorApp {
    val averiaRepositorio: AveriaRepositorio
    val clienteRepositorio: ClienteRepositorio
    val empleadoRepositorio: EmpleadoRepositorio
    val rolRepositorio: RolRepositorio
    val permisoRepositorio: PermisoRepositorio
    val vehiculoRepositorio: VehiculoRepositorio
    val piezaRepositorio: PiezaRepositorio
    val tipopiezaRepositorio: TipopiezaRepositorio
    val averiapiezaRepositorio: AveriapiezaRepositorio
    val tipoaveriaRepositorio: TipoaveriaRepositorio
    val averiatipoaveriaRepositorio: AveriatipoaveriaRepositorio
}

class TallerContenedorApp : ContenedorApp {
    private val baseUrl = "http://192.168.1.106:8000/api/"
    //    private val baseUrl = "http://192.168.0.244:8000/api/"
//    private val baseUrl = "http://192.168.0.102:8000/api/"

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val servicioRetrofit: ServicioApi by lazy {
        retrofit.create(ServicioApi::class.java)
    }

    override val averiaRepositorio: AveriaRepositorio by lazy {
        ConexionAveriaRepositorio(servicioRetrofit)
    }

    override val clienteRepositorio: ClienteRepositorio by lazy {
        ConexionClienteRepositorio(servicioRetrofit)
    }

    override val empleadoRepositorio: EmpleadoRepositorio by lazy {
        ConexionEmpleadoRepositorio(servicioRetrofit)
    }

    override val rolRepositorio: RolRepositorio by lazy {
        ConexionRolRepositorio(servicioRetrofit)
    }

    override val permisoRepositorio: PermisoRepositorio by lazy {
        ConexionPermisoRepositorio(servicioRetrofit)
    }

    override val vehiculoRepositorio: VehiculoRepositorio by lazy {
        ConexionVehiculoRepositorio(servicioRetrofit)
    }

    override val piezaRepositorio: PiezaRepositorio by lazy {
        ConexionPiezaRepositorio(servicioRetrofit)
    }

    override val tipopiezaRepositorio: TipopiezaRepositorio by lazy {
        ConexionTipopiezaRepositorio(servicioRetrofit)
    }
    override val averiapiezaRepositorio: AveriapiezaRepositorio by lazy {
        ConexionAveriapiezaRepositorio(servicioRetrofit)
    }
    override val tipoaveriaRepositorio: TipoaveriaRepositorio by lazy {
        ConexionTipoaveriaRepositorio(servicioRetrofit)
    }
    override val averiatipoaveriaRepositorio: AveriatipoaveriaRepositorio by lazy {
        ConexionAveriatipoaveriaRepositorio(servicioRetrofit)
    }
}
