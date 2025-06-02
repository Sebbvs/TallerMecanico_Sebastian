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
import com.example.tallermecanico_sebastian.datos.repos.TipopiezaRepositorio
import com.example.tallermecanico_sebastian.modelo.Tipopieza
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface TipopiezaUIState {
    data class ObtenerExito(val tipopieza: List<Tipopieza>) : TipopiezaUIState
    data class CrearExito(val tipopieza: Tipopieza) : TipopiezaUIState
    data class ActualizarExito(val tipopieza: Tipopieza) : TipopiezaUIState
    data class EliminarExito(val id: String) : TipopiezaUIState

    object Error : TipopiezaUIState
    object Cargando : TipopiezaUIState
}

class TipopiezaViewModel(private val tipopiezaRepositorio: TipopiezaRepositorio) : ViewModel() {
    var tipopiezaUIState: TipopiezaUIState by mutableStateOf(TipopiezaUIState.Cargando)
        private set

    var listaPiezaTipopieza by mutableStateOf(listOf<Tipopieza>())
        private set

    init {
        obtenerTipospieza()
    }

    fun obtenerTipospieza() {
        viewModelScope.launch {
            tipopiezaUIState = TipopiezaUIState.Cargando
            tipopiezaUIState = try {
                val listaTipopieza = tipopiezaRepositorio.obtenerTipopieza()
                listaPiezaTipopieza = listaTipopieza
                TipopiezaUIState.ObtenerExito(listaTipopieza)
            } catch (e: IOException) {
                Log.v("TipopiezaViewModel IO", "Error de Conexion obtenerTipospiezas", e)
                TipopiezaUIState.Error
            } catch (e: HttpException) {
                Log.v("TipopiezaViewModel HTTP", "Error HTTP %{e.code()} obtenerTipopieza", e)
                TipopiezaUIState.Error
            } catch (e: IOException) {
                Log.v("TipopiezaViewModel E", "Error desconocido obtenerTipopieza", e)
                TipopiezaUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as TallerAplicacion)
                val tipopiezaRepositorio = aplicacion.contenedor.tipopiezaRepositorio
                TipopiezaViewModel(tipopiezaRepositorio = tipopiezaRepositorio)
            }
        }
    }
}