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
import com.example.tallermecanico_sebastian.datos.repos.VehiculoRepositorio
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.modelo.Vehiculo
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface VehiculoUIState {
    data class ObtenerExito(val vehiculos: List<Vehiculo>) : VehiculoUIState
    data class CrearExito(val vehiculo: Vehiculo) : VehiculoUIState
    data class ActualizarExito(val vehiculo: Vehiculo) : VehiculoUIState
    data class EliminarExito(val id: String) : VehiculoUIState

    object Error : VehiculoUIState
    object Cargando : VehiculoUIState
//    data class ErrorMensaje(val mensaje: String) : VehiculoUIState
}

class VehiculoViewModel(private val vehiculoRepositorio: VehiculoRepositorio) : ViewModel() {
    var vehiculoUIState: VehiculoUIState by mutableStateOf(VehiculoUIState.Cargando)
        private set

    var listaAveriaVehiculos by mutableStateOf(listOf<Vehiculo>())
        private set

    var vehiculoPulsado: Vehiculo by mutableStateOf(
        Vehiculo(
            0,
            "",
            "",
            "",
            "",
            "",
            0,
            Cliente(),
        )
    )
        private set

    fun actualizarVehiculoPulsado(vehiculo: Vehiculo) {
        vehiculoPulsado = vehiculo
    }

    init {
        obtenerVehiculos()
    }

    fun obtenerVehiculos() {
        viewModelScope.launch {
            vehiculoUIState = VehiculoUIState.Cargando
            vehiculoUIState = try {
                val listaVehiculos = vehiculoRepositorio.obtenerVehiculos()
                listaAveriaVehiculos = listaVehiculos
                VehiculoUIState.ObtenerExito(listaVehiculos)
            } catch (e: IOException) {
                Log.v("VehiculoViewModel IO", "Error de Conexion obtenerVehiculos", e)
                VehiculoUIState.Error
            } catch (e: HttpException) {
                Log.v("VehiculoViewModel HTTP", "Error HTTP %{e.code()} obtenerVehiculos", e)
                VehiculoUIState.Error
            } catch (e: IOException) {
                Log.v("VehiculoViewModel E", "Error desconocido obtenerVehiculos", e)
                VehiculoUIState.Error
            }
        }
    }

    fun insertarVehiculo(vehiculo: Vehiculo) {
        viewModelScope.launch {
            vehiculoUIState = VehiculoUIState.Cargando
            vehiculoUIState = try {
                val vehiculoPulsado = vehiculoRepositorio.insertarVehiculo(vehiculo)
                VehiculoUIState.CrearExito(vehiculoPulsado)
            } catch (e: IOException) {
                Log.v("VehiculoViewModel IO", "Error de Conexion insertarVehiculo", e)
                VehiculoUIState.Error
            } catch (e: HttpException) {
                Log.v("VehiculoViewModel HTTP", "Error HTTP %{e.code()} insertarVehiculo", e)
                VehiculoUIState.Error
            } catch (e: IOException) {
                Log.v("VehiculoViewModel E", "Error desconocido insertarVehiculo", e)
                VehiculoUIState.Error
            }
        }
    }

    fun actualizarVehiculo(id: String, vehiculo: Vehiculo) {
        viewModelScope.launch {
            vehiculoUIState = VehiculoUIState.Cargando
            vehiculoUIState = try {
                val vehiculoActualizado = vehiculoRepositorio.actualizarVehiculo(
                    id = id, vehiculo = vehiculo
                )
                VehiculoUIState.ActualizarExito(vehiculoActualizado)
            } catch (e: IOException) {
                Log.v("VehiculoViewModel IO", "Error de Conexion actualizarVehiculo", e)
                VehiculoUIState.Error
            } catch (e: HttpException) {
                Log.v("VehiculoViewModel HTTP", "Error HTTP %{e.code()} actualizarVehiculo", e)
                VehiculoUIState.Error
            } catch (e: IOException) {
                Log.v("VehiculoViewModel E", "Error desconocido actualizarVehiculo", e)
                VehiculoUIState.Error
            }
        }
    }

    fun eliminarVehiculo(id: String) {
        viewModelScope.launch {
            vehiculoUIState = VehiculoUIState.Cargando
            vehiculoUIState = try {
                vehiculoRepositorio.eliminarVehiculo(id)
                VehiculoUIState.EliminarExito(id)
            } catch (e: IOException) {
                Log.v("VehiculoViewModel IO", "Error de Conexion eliminarVehiculo", e)
                VehiculoUIState.Error
            } catch (e: HttpException) {
                Log.v("VehiculoViewModel HTTP", "Error HTTP %{e.code()} eliminarVehiculo", e)
                VehiculoUIState.Error
            } catch (e: IOException) {
                Log.v("VehiculoViewModel E", "Error desconocido eliminarVehiculo", e)
                VehiculoUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as TallerAplicacion)
                val vehiculoRepositorio = aplicacion.contenedor.vehiculoRepositorio
                VehiculoViewModel(vehiculoRepositorio = vehiculoRepositorio)
            }
        }
    }

    var clienteSeleccionado by mutableStateOf<Cliente?>(null)
        private set

    fun seleccionarCliente(cliente: Cliente) {
        clienteSeleccionado = cliente
    }

    var provisional by mutableStateOf<Vehiculo?>(null)
        private set

    fun seleccionarProvisional(vehiculo: Vehiculo) {
        provisional = vehiculo
    }

    fun ensamblarVehiculo(): Vehiculo? {
        return provisional?.copy(
            cod_cliente = clienteSeleccionado?.cod_cliente, cliente = clienteSeleccionado
        )
    }

    fun limpiarFormularioVehiculo() {
        clienteSeleccionado = null
        provisional = null
    }
}