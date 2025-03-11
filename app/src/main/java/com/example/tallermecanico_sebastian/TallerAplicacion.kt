package com.example.tallermecanico_sebastian

import android.app.Application
import com.example.tallermecanico_sebastian.datos.ContenedorApp
import com.example.tallermecanico_sebastian.datos.TallerContenedorApp

class TallerAplicacion: Application() {
    lateinit var contenedor: ContenedorApp
    override fun onCreate() {
        super.onCreate()
        contenedor = TallerContenedorApp(this)
    }
}