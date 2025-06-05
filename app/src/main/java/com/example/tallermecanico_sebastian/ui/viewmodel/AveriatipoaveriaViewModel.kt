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
import com.example.tallermecanico_sebastian.datos.repos.AveriatipoaveriaRepositorio
import com.example.tallermecanico_sebastian.modelo.Averiatipoaveria
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AveriatipoaveriaUIState {
    data class CrearExito(val averiatipoaveria: Averiatipoaveria) : AveriatipoaveriaUIState
    data class EliminarExito(val idA: Int, val idT: Int) : AveriatipoaveriaUIState

    object Error : AveriatipoaveriaUIState
    object Cargando : AveriatipoaveriaUIState
//    data class ErrorMensaje(val mensaje: String) : VehiculoUIState
}

class AveriatipoaveriaViewModel(private val averiatipoaveriaRepositorio: AveriatipoaveriaRepositorio) :
    ViewModel() {
    var averiatipoaveriaUIState: AveriatipoaveriaUIState by mutableStateOf(AveriatipoaveriaUIState.Cargando)
        private set


    fun insertarAveriatipoaveria(averiatipoaveria: Averiatipoaveria) {
        viewModelScope.launch {
            averiatipoaveriaUIState = AveriatipoaveriaUIState.Cargando
            averiatipoaveriaUIState = try {
                val averiatipoaveriaPulsado =
                    averiatipoaveriaRepositorio.insertarAveriatipoaveria(averiatipoaveria)
                AveriatipoaveriaUIState.CrearExito(averiatipoaveriaPulsado)
            } catch (e: IOException) {
                Log.v(
                    "AveriatipoaveriaViewModel IO",
                    "Error de Conexion insertarAveriatipoaveria",
                    e
                )
                AveriatipoaveriaUIState.Error
            } catch (e: HttpException) {
                Log.v(
                    "AveriatipoaveriaViewModel HTTP",
                    "Error HTTP %{e.code()} insertarAveriatipoaveria",
                    e
                )
                AveriatipoaveriaUIState.Error
            } catch (e: IOException) {
                Log.v(
                    "AveriatipoaveriaViewModel E",
                    "Error desconocido insertarAveriatipoaveria",
                    e
                )
                AveriatipoaveriaUIState.Error
            }
        }
    }

    fun eliminarAveriatipoaveria(idA: Int, idT: Int) {
        viewModelScope.launch {
            averiatipoaveriaUIState = AveriatipoaveriaUIState.Cargando
            averiatipoaveriaUIState = try {
                averiatipoaveriaRepositorio.eliminarAveriatipoaveria(idA, idT)
                AveriatipoaveriaUIState.EliminarExito(idA, idT)
            } catch (e: IOException) {
                Log.v(
                    "AveriatipoaveriaViewModel IO",
                    "Error de Conexion eliminarAveriatipoaveria",
                    e
                )
                AveriatipoaveriaUIState.Error
            } catch (e: HttpException) {
                Log.v(
                    "AveriatipoaveriaViewModel HTTP",
                    "Error HTTP %{e.code()} eliminarAveriatipoaveria",
                    e
                )
                AveriatipoaveriaUIState.Error
            } catch (e: IOException) {
                Log.v(
                    "AveriatipoaveriaViewModel E",
                    "Error desconocido eliminarAveriatipoaveria",
                    e
                )
                AveriatipoaveriaUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as TallerAplicacion)
                val averiatipoaveriaRepositorio = aplicacion.contenedor.averiatipoaveriaRepositorio
                AveriatipoaveriaViewModel(averiatipoaveriaRepositorio = averiatipoaveriaRepositorio)
            }
        }
    }
}