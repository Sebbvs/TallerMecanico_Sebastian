package com.example.tallermecanico_sebastian.ui.viewmodel

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
import com.example.tallermecanico_sebastian.datos.repos.RolRepositorio
import com.example.tallermecanico_sebastian.modelo.Rol
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface RolUIState {
    data class ObtenerExito(val roles: List<Rol>) : RolUIState
    data class CrearExito(val rol: Rol) : RolUIState
    data class ActualizarExito(val rol: Rol) : RolUIState
    data class EliminarExito(val id: String) : RolUIState

    object Error : RolUIState
    object Cargando : RolUIState
//    data class ErrorMensaje(val mensaje: String) : RolUIState
}

class RolViewModel(private val rolRepositorio: RolRepositorio) : ViewModel() {
    var rolUIState: RolUIState by mutableStateOf(RolUIState.Cargando)
        private set

    var rolPulsado: Rol by mutableStateOf(
        Rol(
            0,
            "",
        )
    )
        private set

    fun actualizarRol(rol: Rol) {
        rolPulsado = rol
    }

    init {
        obtenerRoles()
    }

    fun obtenerRoles() {
        viewModelScope.launch {
            rolUIState = RolUIState.Cargando
            rolUIState = try {
                val listaRoles = rolRepositorio.obtenerRoles()
                RolUIState.ObtenerExito(listaRoles)
            } catch (e: IOException) {
                Log.v("RolViewModel IO", "Error de Conexion obtenerRoles", e)
                RolUIState.Error
            } catch (e: HttpException) {
                Log.v("RolViewModel HTTP", "Error HTTP %{e.code()} obtenerRoles", e)
                RolUIState.Error
            } catch (e: IOException) {
                Log.v("RolViewModel E", "Error desconocido obtenerRoles", e)
                RolUIState.Error
            }
        }
    }

    fun insertarRol(rol: Rol) {
        viewModelScope.launch {
            rolUIState = RolUIState.Cargando
            rolUIState = try {
                val rolInsertado = rolRepositorio.insertarRol(rol)
                RolUIState.CrearExito(rolInsertado)
            } catch (e: IOException) {
                Log.v("RolViewModel IO", "Error de Conexion insertarRol", e)
                RolUIState.Error
            } catch (e: HttpException) {
                Log.v("RolViewModel HTTP", "Error HTTP %{e.code()} insertarRol", e)
                RolUIState.Error
            } catch (e: IOException) {
                Log.v("RolViewModel E", "Error desconocido insertarRol", e)
                RolUIState.Error
            }
        }
    }

    fun actualizarRol(id: String, rol: Rol) {
        viewModelScope.launch {
            rolUIState = RolUIState.Cargando
            rolUIState = try {
                val rolActualizado = rolRepositorio.actualizarRol(
                    id = id,
                    rol = rol
                )
                RolUIState.ActualizarExito(rolActualizado)
            } catch (e: IOException) {
                Log.v("RolViewModel IO", "Error de Conexion actualizarRol", e)
                RolUIState.Error
            } catch (e: HttpException) {
                Log.v("RolViewModel HTTP", "Error HTTP %{e.code()} actualizarRol", e)
                RolUIState.Error
            } catch (e: IOException) {
                Log.v("RolViewModel E", "Error desconocido actualizarRol", e)
                RolUIState.Error
            }
        }
    }

    fun eliminarRol(id: String) {
        viewModelScope.launch {
            rolUIState = RolUIState.Cargando
            rolUIState = try {
                rolRepositorio.eliminarRol(id)
                RolUIState.EliminarExito(id)
            } catch (e: IOException) {
                Log.v("RolViewModel IO", "Error de Conexion eliminarRol", e)
                RolUIState.Error
            } catch (e: HttpException) {
                Log.v("RolViewModel HTTP", "Error HTTP %{e.code()} eliminarRol", e)
                RolUIState.Error
            } catch (e: IOException) {
                Log.v("RolViewModel E", "Error desconocido eliminarRol", e)
                RolUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as TallerAplicacion)
                val rolRepositorio = aplicacion.contenedor.rolRepositorio
                RolViewModel(rolRepositorio = rolRepositorio)
            }
        }
    }
}