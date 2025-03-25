package com.example.tallermecanico_sebastian.datos

import android.content.Context
import com.example.tallermecanico_sebastian.conexion.ServicioApi
import com.example.tallermecanico_sebastian.datos.repos.ConexionEmpleadoRepositorio
import com.example.tallermecanico_sebastian.datos.repos.EmpleadoRepositorio
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContenedorApp {
    val empleadoRepositorio: EmpleadoRepositorio
}

class TallerContenedorApp(private val context: Context) : ContenedorApp {
    private val baseUrl = "http://192.168.1.106:8000/api/"

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val servicioRetrofit: ServicioApi by lazy {
        retrofit.create(ServicioApi::class.java)
    }

    override val empleadoRepositorio: EmpleadoRepositorio by lazy {
        ConexionEmpleadoRepositorio(servicioRetrofit)
    }
}
