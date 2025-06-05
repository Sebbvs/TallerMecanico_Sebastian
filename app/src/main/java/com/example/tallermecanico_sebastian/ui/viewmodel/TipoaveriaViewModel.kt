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
import com.example.tallermecanico_sebastian.datos.repos.TipoaveriaRepositorio
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.modelo.Tipoaveria
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface TipoaveriaUIState {
    data class ObtenerExito(val tipoaveria: List<Tipoaveria>) : TipoaveriaUIState
    data class CrearExito(val tipoaveria: Tipoaveria) : TipoaveriaUIState
    data class ActualizarExito(val tipoaveria: Tipoaveria) : TipoaveriaUIState
    data class EliminarExito(val id: Int) : TipoaveriaUIState

    object Error : TipoaveriaUIState
    object Cargando : TipoaveriaUIState
//    data class ErrorMensaje(val mensaje: String) : VehiculoUIState
}

class TipoaveriaViewModel(private val tipoaveriaRepositorio: TipoaveriaRepositorio) :
    ViewModel() {
    var tipoaveriaUIState: TipoaveriaUIState by mutableStateOf(TipoaveriaUIState.Cargando)
        private set

    var listaAveriaTipoaveria by mutableStateOf(listOf<Tipoaveria>())
        private set

    var tipoaveriaPulsado: Tipoaveria by mutableStateOf(
        Tipoaveria(
        )
    )
        private set

    fun actualizarTipoaveriaPulsado(tipoaveria: Tipoaveria) {
        tipoaveriaPulsado = tipoaveria
    }

    init {
        obtenerTipoaveria()
    }

    fun obtenerTipoaveria() {
        viewModelScope.launch {
            tipoaveriaUIState = TipoaveriaUIState.Cargando
            tipoaveriaUIState = try {
                val listaTipoaveria = tipoaveriaRepositorio.obtenerTipoaveria()
                listaAveriaTipoaveria = listaTipoaveria
                TipoaveriaUIState.ObtenerExito(listaTipoaveria)
            } catch (e: IOException) {
                Log.v("TipoaveriaViewModel IO", "Error de Conexion obtenerTipoaveria", e)
                TipoaveriaUIState.Error
            } catch (e: HttpException) {
                Log.v("TipoaveriaViewModel HTTP", "Error HTTP %{e.code()} obtenerTipoaveria", e)
                TipoaveriaUIState.Error
            } catch (e: IOException) {
                Log.v("TipoaveriaViewModel E", "Error desconocido obtenerTipoaveria", e)
                TipoaveriaUIState.Error
            }
        }
    }

    fun insertarTipoaveria(tipoaveria: Tipoaveria) {
        viewModelScope.launch {
            tipoaveriaUIState = TipoaveriaUIState.Cargando
            tipoaveriaUIState = try {
                val tipoaveriaPulsado = tipoaveriaRepositorio.insertarTipoaveria(tipoaveria)
                TipoaveriaUIState.CrearExito(tipoaveriaPulsado)
            } catch (e: IOException) {
                Log.v("TipoaveriaViewModel IO", "Error de Conexion insertarTipoaveria", e)
                TipoaveriaUIState.Error
            } catch (e: HttpException) {
                Log.v("TipoaveriaViewModel HTTP", "Error HTTP %{e.code()} insertarTipoaveria", e)
                TipoaveriaUIState.Error
            } catch (e: IOException) {
                Log.v("TipoaveriaViewModel E", "Error desconocido insertarTipoaveria", e)
                TipoaveriaUIState.Error
            }
        }
    }

    fun actualizarTipoaveria(id: Int, tipoaveria: Tipoaveria) {
        viewModelScope.launch {
            tipoaveriaUIState = TipoaveriaUIState.Cargando
            tipoaveriaUIState = try {
                val tipoaveriaActualizado = tipoaveriaRepositorio.actualizarTipoaveria(
                    id = id, tipoaveria = tipoaveria
                )
                TipoaveriaUIState.ActualizarExito(tipoaveriaActualizado)
            } catch (e: IOException) {
                Log.v("TipoaveriaViewModel IO", "Error de Conexion actualizarTipoaveria", e)
                TipoaveriaUIState.Error
            } catch (e: HttpException) {
                Log.v(
                    "TipoaveriaViewModel HTTP",
                    "Error HTTP %{e.code()} actualizarTipoaveria",
                    e
                )
                TipoaveriaUIState.Error
            } catch (e: IOException) {
                Log.v("TipoaveriaViewModel E", "Error desconocido actualizarTipoaveria", e)
                TipoaveriaUIState.Error
            }
        }
    }

    fun eliminarTipoaveria(id: Int) {
        viewModelScope.launch {
            tipoaveriaUIState = TipoaveriaUIState.Cargando
            tipoaveriaUIState = try {
                tipoaveriaRepositorio.eliminarTipoaveria(id)
                TipoaveriaUIState.EliminarExito(id)
            } catch (e: IOException) {
                Log.v("TipoaveriaViewModel IO", "Error de Conexion eliminarTipoaveria", e)
                TipoaveriaUIState.Error
            } catch (e: HttpException) {
                Log.v("TipoaveriaViewModel HTTP", "Error HTTP %{e.code()} eliminarTipoaveria", e)
                TipoaveriaUIState.Error
            } catch (e: IOException) {
                Log.v("TipoaveriaViewModel E", "Error desconocido eliminarTipoaveria", e)
                TipoaveriaUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as TallerAplicacion)
                val tipoaveriaRepositorio = aplicacion.contenedor.tipoaveriaRepositorio
                TipoaveriaViewModel(tipoaveriaRepositorio = tipoaveriaRepositorio)
            }
        }
    }
}