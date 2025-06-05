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
import com.example.tallermecanico_sebastian.datos.repos.PiezaRepositorio
import com.example.tallermecanico_sebastian.modelo.Empleado
import com.example.tallermecanico_sebastian.modelo.Pieza
import com.example.tallermecanico_sebastian.modelo.Rol
import com.example.tallermecanico_sebastian.modelo.Tipopieza
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface PiezaUIState {
    data class ObtenerExito(val piezas: List<Pieza>) : PiezaUIState
    data class CrearExito(val pieza: Pieza) : PiezaUIState
    data class ActualizarExito(val pieza: Pieza) : PiezaUIState
    data class EliminarExito(val id: Int) : PiezaUIState

    object Error : PiezaUIState
    object Cargando : PiezaUIState
//    data class ErrorMensaje(val mensaje: String) : PiezaUIState
}

class PiezaViewModel(private val piezaRepositorio: PiezaRepositorio) : ViewModel() {
    var piezaUIState: PiezaUIState by mutableStateOf(PiezaUIState.Cargando)
        private set

    var listaAveriaAveriapieza by mutableStateOf(listOf<Pieza>())
        private set

    var piezaPulsado: Pieza by mutableStateOf(
        Pieza(
            0,
            "",
            0,
            0,
            Tipopieza(),
        )
    )
        private set

    fun actualizarPiezaPulsado(pieza: Pieza) {
        piezaPulsado = pieza
    }

    init {
        obtenerPiezas()
    }

    fun obtenerPiezas() {
        viewModelScope.launch {
            piezaUIState = PiezaUIState.Cargando
            piezaUIState = try {
                val listaPiezas = piezaRepositorio.obtenerPiezas()
                listaAveriaAveriapieza = listaPiezas
                PiezaUIState.ObtenerExito(listaPiezas)
            } catch (e: IOException) {
                Log.v("PiezaViewModel IO", "Error de Conexion obtenerPiezas", e)
                PiezaUIState.Error
            } catch (e: HttpException) {
                Log.v("PiezaViewModel HTTP", "Error HTTP %{e.code()} obtenerPiezas", e)
                PiezaUIState.Error
            } catch (e: IOException) {
                Log.v("PiezaViewModel E", "Error desconocido obtenerPiezas", e)
                PiezaUIState.Error
            }
        }
    }

    fun insertarPieza(pieza: Pieza) {
        viewModelScope.launch {
            piezaUIState = PiezaUIState.Cargando
            piezaUIState = try {
                val piezaObtenida = piezaRepositorio.insertarPieza(pieza)
                PiezaUIState.CrearExito(piezaObtenida)
            } catch (e: IOException) {
                Log.v("PiezaViewModel IO", "Error de Conexion obtenerPieza", e)
                PiezaUIState.Error
            } catch (e: HttpException) {
                Log.v("PiezaViewModel HTTP", "Error HTTP %{e.code()} obtenerPieza", e)
                PiezaUIState.Error
            } catch (e: IOException) {
                Log.v("PiezaViewModel E", "Error desconocido obtenerPieza", e)
                PiezaUIState.Error
            }
        }
    }

    fun actualizarPieza(id: Int, pieza: Pieza) {
        viewModelScope.launch {
            piezaUIState = PiezaUIState.Cargando
            piezaUIState = try {
                val piezaActualizado = piezaRepositorio.actualizarPieza(
                    id = id, pieza = pieza
                )
                PiezaUIState.ActualizarExito(piezaActualizado)
            } catch (e: IOException) {
                Log.v("PiezaViewModel IO", "Error de Conexion actualizarPieza", e)
                PiezaUIState.Error
            } catch (e: HttpException) {
                Log.v("PiezaViewModel HTTP", "Error HTTP %{e.code()} actualizarPieza", e)
                PiezaUIState.Error
            } catch (e: IOException) {
                Log.v("PiezaViewModel E", "Error desconocido actualizarPieza", e)
                PiezaUIState.Error
            }
        }
    }

    fun eliminarPieza(id: Int) {
        viewModelScope.launch {
            piezaUIState = PiezaUIState.Cargando
            piezaUIState = try {
                piezaRepositorio.eliminarPieza(id)
                PiezaUIState.EliminarExito(id)
            } catch (e: IOException) {
                Log.v("PiezaViewModel IO", "Error de Conexion eliminarPPieza", e)
                PiezaUIState.Error
            } catch (e: HttpException) {
                Log.v("PiezaViewModel HTTP", "Error HTTP %{e.code()} eliminarPieza", e)
                PiezaUIState.Error
            } catch (e: IOException) {
                Log.v("PiezaViewModel E", "Error desconocido eliminarPieza", e)
                PiezaUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as TallerAplicacion)
                val piezaRepositorio = aplicacion.contenedor.piezaRepositorio
                PiezaViewModel(piezaRepositorio = piezaRepositorio)
            }
        }
    }

    var tipopiezaSeleccionado by mutableStateOf<Tipopieza?>(null)
        private set

    fun seleccionarTipopieza(tipopieza: Tipopieza) {
        tipopiezaSeleccionado = tipopieza
    }

    var provisional by mutableStateOf<Pieza?>(null)
        private set

    fun seleccionarProvisional(pieza: Pieza) {
        provisional = pieza
    }

    fun ensamblarPieza(): Pieza? {
        return provisional?.copy(
            cod_tipo = tipopiezaSeleccionado?.cod_tipo_pieza, tipo_pieza = tipopiezaSeleccionado
        )
    }

    fun limpiarFormularioPieza() {
        tipopiezaSeleccionado = null
        provisional = null
    }
}