package com.example.tallermecanico_sebastian.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.tallermecanico_sebastian.TallerAplicacion
import com.example.tallermecanico_sebastian.datos.repos.ClienteRepositorio
import com.example.tallermecanico_sebastian.modelo.Cliente
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ClienteUIState {
    data class ObtenerExito(val clientes: List<Cliente>) : ClienteUIState
    data class CrearExito(val cliente: Cliente) : ClienteUIState
    data class ActualizarExito(val cliente: Cliente) : ClienteUIState
    data class EliminarExito(val id: String) : ClienteUIState

    object Error : ClienteUIState
    object Cargando : ClienteUIState
//    data class ErrorMensaje(val mensaje: String) : ClienteUIState
}

class ClienteViewModel(private val clienteRepositorio: ClienteRepositorio) : ViewModel() {
    var clienteUIState: ClienteUIState by mutableStateOf(ClienteUIState.Cargando)
        private set

    var listaVehiculoClientes by mutableStateOf(listOf<Cliente>())
        private set

    var listaAveriaClientes by mutableStateOf(listOf<Cliente>())
        private set

    var clientePulsado: Cliente by mutableStateOf(
        Cliente(
            0,
            "",
            "",
            "",
            "",
            "",
        )
    )
        private set

    fun actualizarClientePulsado(cliente: Cliente) {
        clientePulsado = cliente
    }

    init {
        obtenerClientes()
    }

    fun obtenerClientes() {
        viewModelScope.launch {
            clienteUIState = ClienteUIState.Cargando
            clienteUIState = try {
                val listaClientes = clienteRepositorio.obtenerClientes()
                listaVehiculoClientes = listaClientes
                listaAveriaClientes = listaClientes
                ClienteUIState.ObtenerExito(listaClientes)
            } catch (e: IOException) {
                Log.v("ClienteViewModel IO", "Error de Conexion obtenerClientes", e)
                ClienteUIState.Error
            } catch (e: HttpException) {
                Log.v("ClienteViewModel HTTP", "Error HTTP %{e.code()} obtenerClientes", e)
                ClienteUIState.Error
            } catch (e: IOException) {
                Log.v("ClienteViewModel E", "Error desconocido obtenerClientes", e)
                ClienteUIState.Error
            }
        }
    }

    fun insertarCliente(cliente: Cliente) {
        viewModelScope.launch {
            clienteUIState = ClienteUIState.Cargando
            clienteUIState = try {
                val clienteInsertado = clienteRepositorio.insertarCliente(cliente)
                ClienteUIState.CrearExito(clienteInsertado)
            } catch (e: IOException) {
                Log.v("ClienteViewModel IO", "Error de Conexion insertarCliente", e)
                ClienteUIState.Error
            } catch (e: HttpException) {
                Log.v("ClienteViewModel HTTP", "Error HTTP %{e.code()} insertarCliente", e)
                ClienteUIState.Error
            } catch (e: IOException) {
                Log.v("ClienteViewModel E", "Error desconocido insertarCliente", e)
                ClienteUIState.Error
            }
        }
    }

    fun actualizarCliente(id: String, cliente: Cliente) {
        viewModelScope.launch {
            clienteUIState = ClienteUIState.Cargando
            clienteUIState = try {
                val clienteActualizado = clienteRepositorio.actualizarCliente(
                    id = id,
                    cliente = cliente
                )
                ClienteUIState.ActualizarExito(clienteActualizado)
            } catch (e: IOException) {
                Log.v("ClienteViewModel IO", "Error de Conexion actualizarCliente", e)
                ClienteUIState.Error
            } catch (e: HttpException) {
                Log.v("ClienteViewModel HTTP", "Error HTTP %{e.code()} actualizarCliente", e)
                ClienteUIState.Error
            } catch (e: IOException) {
                Log.v("ClienteViewModel E", "Error desconocido actualizarCliente", e)
                ClienteUIState.Error
            }
        }
    }

    fun eliminarCliente(id: String) {
        viewModelScope.launch {
            clienteUIState = ClienteUIState.Cargando
            clienteUIState = try {
                clienteRepositorio.eliminarCliente(id)
                ClienteUIState.EliminarExito(id)
            } catch (e: IOException) {
                Log.v("ClienteViewModel IO", "Error de Conexion eliminarCliente", e)
                ClienteUIState.Error
            } catch (e: HttpException) {
                Log.v("ClienteViewModel HTTP", "Error HTTP %{e.code()} eliminarCliente", e)
                ClienteUIState.Error
            } catch (e: IOException) {
                Log.v("ClienteViewModel E", "Error desconocido eliminarCliente", e)
                ClienteUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as TallerAplicacion)
                val clienteRepositorio = aplicacion.contenedor.clienteRepositorio
                ClienteViewModel(clienteRepositorio = clienteRepositorio)
            }
        }
    }
}