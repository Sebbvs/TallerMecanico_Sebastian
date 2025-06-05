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
import com.example.tallermecanico_sebastian.modelo.Tipopieza
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AveriapiezaUIState {
    data class CrearExito(val averiapieza: Averiapieza) : AveriapiezaUIState

    object Error : AveriapiezaUIState
    object Cargando : AveriapiezaUIState
//    data class ErrorMensaje(val mensaje: String) : VehiculoUIState
}

class AveriapiezaViewModel(private val averiapiezaRepositorio: AveriapiezaRepositorio) :
    ViewModel() {
    var averiapiezaUIState: AveriapiezaUIState by mutableStateOf(AveriapiezaUIState.Cargando)
        private set


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
