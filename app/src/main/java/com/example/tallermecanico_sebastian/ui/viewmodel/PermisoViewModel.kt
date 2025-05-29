package com.example.tallermecanico_sebastian.ui.viewmodel

import com.example.tallermecanico_sebastian.datos.repos.PermisoRepositorio
import com.example.tallermecanico_sebastian.modelo.Permiso
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.tallermecanico_sebastian.TallerAplicacion
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface PermisoUIState {
    data class ObtenerExito(val permisos: List<Permiso>) : PermisoUIState

    object Error : PermisoUIState
    object Cargando : PermisoUIState
//    data class ErrorMensaje(val mensaje: String) : RolUIState
}

class PermisoViewModel(private val permisoRepositorio: PermisoRepositorio) : ViewModel() {
    var permisoUIState: PermisoUIState by mutableStateOf(PermisoUIState.Cargando)
        private set

    init {
        obtenerRoles()
    }

    fun obtenerRoles() {
        viewModelScope.launch {
            permisoUIState = PermisoUIState.Cargando
            permisoUIState = try {
                val listaPermisos = permisoRepositorio.obtenerPermisos()
                PermisoUIState.ObtenerExito(listaPermisos)
            } catch (e: IOException) {
                Log.v("PermisoViewModel IO", "Error de Conexion obtenerPermisos", e)
                PermisoUIState.Error
            } catch (e: HttpException) {
                Log.v("PermisoViewModel HTTP", "Error HTTP %{e.code()} obtenerPermisos", e)
                PermisoUIState.Error
            } catch (e: IOException) {
                Log.v("PermisoViewModel E", "Error desconocido obtenerPermisos", e)
                PermisoUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as TallerAplicacion)
                val permisoRepositorio = aplicacion.contenedor.permisoRepositorio
                PermisoViewModel(permisoRepositorio = permisoRepositorio)
            }
        }
    }
}