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
import com.example.tallermecanico_sebastian.datos.repos.AveriapiezaRepositorio
import com.example.tallermecanico_sebastian.modelo.Averiapieza
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AveriapiezaUIState {
    data class ObtenerExito(val averiapiezas: List<Averiapieza>) : AveriapiezaUIState
    data class CrearExito(val averiapieza: Averiapieza) : AveriapiezaUIState
    data class ActualizarExito(val averiapieza: Averiapieza) : AveriapiezaUIState
    data class EliminarExito(val id: Int) : AveriapiezaUIState

    object Error : AveriapiezaUIState
    object Cargando : AveriapiezaUIState
//    data class ErrorMensaje(val mensaje: String) : VehiculoUIState
}

class AveriapiezaViewModel(private val averiapiezaRepositorio: AveriapiezaRepositorio) :
    ViewModel() {
    var averiapiezaUIState: AveriapiezaUIState by mutableStateOf(AveriapiezaUIState.Cargando)
        private set

    var averiapiezaPulsado: Averiapieza by mutableStateOf(
        Averiapieza(
        )
    )
        private set

    fun actualizarAveriapiezaPulsado(averiapieza: Averiapieza) {
        averiapiezaPulsado = averiapieza
    }

    init {
        obtenerAveriapieza()
    }

    fun obtenerAveriapieza() {
        viewModelScope.launch {
            averiapiezaUIState = AveriapiezaUIState.Cargando
            averiapiezaUIState = try {
                val listaAveriapiezas = averiapiezaRepositorio.obtenerAveriapieza()
                AveriapiezaUIState.ObtenerExito(listaAveriapiezas)
            } catch (e: IOException) {
                Log.v("AveriapiezaViewModel IO", "Error de Conexion obtenerAveriapiezas", e)
                AveriapiezaUIState.Error
            } catch (e: HttpException) {
                Log.v("AveriapiezaViewModel HTTP", "Error HTTP %{e.code()} obtenerAveriapiezas", e)
                AveriapiezaUIState.Error
            } catch (e: IOException) {
                Log.v("AveriapiezaViewModel E", "Error desconocido obtenerAveriapiezas", e)
                AveriapiezaUIState.Error
            }
        }
    }

    fun insertarAveriapieza(averiapieza: Averiapieza) {
        viewModelScope.launch {
            averiapiezaUIState = AveriapiezaUIState.Cargando
            averiapiezaUIState = try {
                val averiapiezaPulsado = averiapiezaRepositorio.insertarAveriapieza(averiapieza)
                AveriapiezaUIState.CrearExito(averiapiezaPulsado)
            } catch (e: IOException) {
                Log.v("AveriapiezaViewModel IO", "Error de Conexion insertarAveriapieza", e)
                AveriapiezaUIState.Error
            } catch (e: HttpException) {
                Log.v("AveriapiezaViewModel HTTP", "Error HTTP %{e.code()} insertarAveriapieza", e)
                AveriapiezaUIState.Error
            } catch (e: IOException) {
                Log.v("AveriapiezaViewModel E", "Error desconocido insertarAveriapieza", e)
                AveriapiezaUIState.Error
            }
        }
    }

    fun actualizarAveriapieza(id: Int, averiapieza: Averiapieza) {
        viewModelScope.launch {
            averiapiezaUIState = AveriapiezaUIState.Cargando
            averiapiezaUIState = try {
                val averiapiezaActualizado = averiapiezaRepositorio.actualizarAveriapieza(
                    id = id, averiapieza = averiapieza
                )
                AveriapiezaUIState.ActualizarExito(averiapiezaActualizado)
            } catch (e: IOException) {
                Log.v("AveriapiezaViewModel IO", "Error de Conexion actualizarAveriapieza", e)
                AveriapiezaUIState.Error
            } catch (e: HttpException) {
                Log.v(
                    "AveriapiezaViewModel HTTP",
                    "Error HTTP %{e.code()} actualizarAveriapieza",
                    e
                )
                AveriapiezaUIState.Error
            } catch (e: IOException) {
                Log.v("AveriapiezaViewModel E", "Error desconocido actualizarAveriapieza", e)
                AveriapiezaUIState.Error
            }
        }
    }

    fun eliminarAveriapieza(id: Int) {
        viewModelScope.launch {
            averiapiezaUIState = AveriapiezaUIState.Cargando
            averiapiezaUIState = try {
                averiapiezaRepositorio.eliminarAveriapieza(id)
                AveriapiezaUIState.EliminarExito(id)
            } catch (e: IOException) {
                Log.v("AveriapiezaViewModel IO", "Error de Conexion eliminarAveriapieza", e)
                AveriapiezaUIState.Error
            } catch (e: HttpException) {
                Log.v("AveriapiezaViewModel HTTP", "Error HTTP %{e.code()} eliminarAveriapieza", e)
                AveriapiezaUIState.Error
            } catch (e: IOException) {
                Log.v("AveriapiezaViewModel E", "Error desconocido eliminarAveriapieza", e)
                AveriapiezaUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as TallerAplicacion)
                val averiapiezaRepositorio = aplicacion.contenedor.averiapiezaRepositorio
                AveriapiezaViewModel(averiapiezaRepositorio = averiapiezaRepositorio)
            }
        }
    }
}