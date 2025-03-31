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
import com.example.tallermecanico_sebastian.datos.repos.AveriaRepositorio
import com.example.tallermecanico_sebastian.modelo.Averia
import com.example.tallermecanico_sebastian.modelo.Averiapieza
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.modelo.Empleado
import com.example.tallermecanico_sebastian.modelo.Vehiculo
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AveriaUIState {
    data class ObtenerExito(val averias: List<Averia>) : AveriaUIState
    data class CrearExito(val averia: Averia) : AveriaUIState
    data class ActualizarExito(val averia: Averia) : AveriaUIState
    data class EliminarExito(val id: String) : AveriaUIState

    object Error : AveriaUIState
    object Cargando : AveriaUIState
    data class ErrorMensaje(val mensaje: String) : AveriaUIState
}

class AveriaViewModel(private val averiaRepositorio: AveriaRepositorio) : ViewModel() {
    var averiaUIState: AveriaUIState by mutableStateOf(AveriaUIState.Cargando)
        private set

    var averiaPulsado: Averia by mutableStateOf(
        Averia(
            0,
            "",
            "",
            "",
            0,
            "",
            "",
            0,
            "",
            0,
            Empleado(),
            Cliente(),
            Vehiculo(),
            emptyList(),
            emptyList(),
        )
    )
        private set

    init {
        obtenerAverias()
    }

    fun obtenerAverias() {
        viewModelScope.launch {
            averiaUIState = AveriaUIState.Cargando
            averiaUIState = try {
                val listaAverias = averiaRepositorio.obtenerAverias()
                AveriaUIState.ObtenerExito(listaAverias)
            } catch (e: IOException) {
                Log.v("AveriaViewModel IO", "Error de Conexion obtenerAverias", e)
                AveriaUIState.Error
            } catch (e: HttpException) {
                Log.v("AveriaViewModel HTTP", "Error HTTP %{e.code()} obtenerAverias", e)
                AveriaUIState.Error
            } catch (e: IOException) {
                Log.v("AveriaViewModel E", "Error desconocido obtenerAverias", e)
                AveriaUIState.Error
            }
        }
    }

    fun insertarAveria(averia: Averia) {
        viewModelScope.launch {
            averiaUIState = AveriaUIState.Cargando
            averiaUIState = try {
                val averiaInsertado = averiaRepositorio.insertarAveria(averia)
                AveriaUIState.CrearExito(averiaInsertado)
            } catch (e: IOException) {
                Log.v("AveriaViewModel IO", "Error de Conexion insertarAveria", e)
                AveriaUIState.Error
            } catch (e: HttpException) {
                Log.v("AveriaViewModel HTTP", "Error HTTP %{e.code()} insertarAveria", e)
                AveriaUIState.Error
            } catch (e: IOException) {
                Log.v("AveriaViewModel E", "Error desconocido insertarAveria", e)
                AveriaUIState.Error
            }
        }
    }

    fun actualizarAveria(id: String, averia: Averia) {
        viewModelScope.launch {
            averiaUIState = AveriaUIState.Cargando
            averiaUIState = try {
                val averiaActualizado = averiaRepositorio.actualizarAveria(
                    id = id,
                    averia = averia
                )
                AveriaUIState.ActualizarExito(averiaActualizado)
            } catch (e: IOException) {
                Log.v("AveriaViewModel IO", "Error de Conexion actualizarAveria", e)
                AveriaUIState.Error
            } catch (e: HttpException) {
                Log.v("AveriaViewModel HTTP", "Error HTTP %{e.code()} actualizarAveria", e)
                AveriaUIState.Error
            } catch (e: IOException) {
                Log.v("AveriaViewModel E", "Error desconocido actualizarAveria", e)
                AveriaUIState.Error
            }
        }
    }

    fun eliminarAveria(id: String) {
        viewModelScope.launch {
            averiaUIState = AveriaUIState.Cargando
            averiaUIState = try {
                averiaRepositorio.eliminarAveria(id)
                AveriaUIState.EliminarExito(id)
            } catch (e: IOException) {
                Log.v("AveriaViewModel IO", "Error de Conexion eliminarAveria", e)
                AveriaUIState.Error
            } catch (e: HttpException) {
                Log.v("AveriaViewModel HTTP", "Error HTTP %{e.code()} eliminarAveria", e)
                AveriaUIState.Error
            } catch (e: IOException) {
                Log.v("AveriaViewModel E", "Error desconocido eliminarAveria", e)
                AveriaUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as TallerAplicacion)
                val averiaRepositorio = aplicacion.contenedor.averiaRepositorio
                AveriaViewModel(averiaRepositorio = averiaRepositorio)
            }
        }
    }

    fun actualizarAveriaPulsado(averia: Averia) {
        averiaPulsado = averia
    }
}
