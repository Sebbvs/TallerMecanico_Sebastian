package com.example.tallermecanico_sebastian.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.tallermecanico_sebastian.TallerAplicacion
import com.example.tallermecanico_sebastian.datos.repos.EmpleadoRepositorio
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.modelo.Empleado
import com.example.tallermecanico_sebastian.modelo.Rol
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface EmpleadoUIState {
    data class ObtenerExito(val empleados: List<Empleado>) : EmpleadoUIState
    data class CrearExito(val empleado: Empleado) : EmpleadoUIState
    data class ActualizarExito(val empleado: Empleado) : EmpleadoUIState
    data class EliminarExito(val id: String) : EmpleadoUIState

    object Error : EmpleadoUIState
    object Cargando : EmpleadoUIState
    data class ErrorMensaje(val mensaje: String) : EmpleadoUIState
}

class EmpleadoViewModel(private val empleadoRepositorio: EmpleadoRepositorio) : ViewModel() {
    var empleadoUIState: EmpleadoUIState by mutableStateOf(EmpleadoUIState.Cargando)
        private set

    var listaAveriaEmpleados by mutableStateOf(listOf<Empleado>())
        private set

    // AUTENTICAR USUARIO
    fun autenticarUsuario(usuario: String, contrasenya: String) {
        empleadoUIState = EmpleadoUIState.Cargando
        viewModelScope.launch {
            try {
                val empleado = empleadoRepositorio.autenticarEmpleado(usuario, contrasenya)

                if (empleado.cod_empleado != 0) {
                    empleadoUIState = EmpleadoUIState.CrearExito(empleado)
                    iniciarSesion(empleado) //Añadido
                } else {
                    empleadoUIState =
                        EmpleadoUIState.ErrorMensaje("Error al introducir usuario y/o contraseña")
                }
            } catch (e: IOException) {
                Log.v("EmpleadoViewModel IO", "Error de Conexion autenticarUsuario", e)
                EmpleadoUIState.Error
            } catch (e: HttpException) {
                Log.v("EmpleadoViewModel HTTP", "Error HTTP %{e.code()} autenticarUsuario", e)
                EmpleadoUIState.Error
            } catch (e: IOException) {
                Log.v("EmpleadoViewModel E", "Error desconocido autenticarUsuario", e)
                EmpleadoUIState.Error
            }
        }
    }

    // CERRAR SESION
    fun cerrarSesion() {
        empleadoLogin = null
        empleadoUIState = EmpleadoUIState.Cargando
    }

    // PASO DE EMPLADO PARA PANTALLA DE INICIO
    var empleadoLogin by mutableStateOf<Empleado?>(null)
        private set

    fun iniciarSesion(empleado: Empleado) {
        empleadoLogin = empleado
    }

    var empleadoPulsado: Empleado by mutableStateOf(
        Empleado(
            0,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            0,
            Rol()
        )
    )
        private set

    fun actualizarEmpleadoPulsado(empleado: Empleado) {
        empleadoPulsado = empleado
    }

    init {
        obtenerEmpleados()
    }

    fun obtenerEmpleados() {
        viewModelScope.launch {
            empleadoUIState = EmpleadoUIState.Cargando
            empleadoUIState = try {
                val listaEmpleados = empleadoRepositorio.obtenerEmpleados()
                listaAveriaEmpleados = listaEmpleados
                EmpleadoUIState.ObtenerExito(listaEmpleados)
            } catch (e: IOException) {
                Log.v("EmpleadoViewModel IO", "Error de Conexion obtenerEmpleados", e)
                EmpleadoUIState.Error
            } catch (e: HttpException) {
                Log.v("EmpleadoViewModel HTTP", "Error HTTP %{e.code()} obtenerEmpleados", e)
                EmpleadoUIState.Error
            } catch (e: IOException) {
                Log.v("EmpleadoViewModel E", "Error desconocido obtenerEmpleados", e)
                EmpleadoUIState.Error
            }
        }
    }

    fun insertarEmpleado(empleado: Empleado) {
        viewModelScope.launch {
            empleadoUIState = EmpleadoUIState.Cargando
            empleadoUIState = try {
                val empleadoInsertado = empleadoRepositorio.insertarEmpleado(empleado)
                EmpleadoUIState.CrearExito(empleadoInsertado)
            } catch (e: IOException) {
                Log.v("EmpleadoViewModel IO", "Error de Conexion insertarEmpleado", e)
                EmpleadoUIState.Error
            } catch (e: HttpException) {
                Log.v("EmpleadoViewModel HTTP", "Error HTTP %{e.code()} insertarEmpleado", e)
                EmpleadoUIState.Error
            } catch (e: IOException) {
                Log.v("EmpleadoViewModel E", "Error desconocido insertarEmpleado", e)
                EmpleadoUIState.Error
            }
        }
    }

    fun actualizarEmpleado(id: String, empleado: Empleado) {
        viewModelScope.launch {
            empleadoUIState = EmpleadoUIState.Cargando
            empleadoUIState = try {
                val empleadoActualizado = empleadoRepositorio.actualizarEmpleado(
                    id = id,
                    empleado = empleado
                )
                EmpleadoUIState.ActualizarExito(empleadoActualizado)
            } catch (e: IOException) {
                Log.v("EmpleadoViewModel IO", "Error de Conexion actualizarEmpleado", e)
                EmpleadoUIState.Error
            } catch (e: HttpException) {
                Log.v("EmpleadoViewModel HTTP", "Error HTTP %{e.code()} actualizarEmpleado", e)
                EmpleadoUIState.Error
            } catch (e: IOException) {
                Log.v("EmpleadoViewModel E", "Error desconocido actualizarEmpleado", e)
                EmpleadoUIState.Error
            }
        }
    }

    fun eliminarEmpleado(id: String) {
        viewModelScope.launch {
            empleadoUIState = EmpleadoUIState.Cargando
            empleadoUIState = try {
                empleadoRepositorio.eliminarEmpleado(id)
                EmpleadoUIState.EliminarExito(id)
            } catch (e: IOException) {
                Log.v("EmpleadoViewModel IO", "Error de Conexion eliminarEmpleado", e)
                EmpleadoUIState.Error
            } catch (e: HttpException) {
                Log.v("EmpleadoViewModel HTTP", "Error HTTP %{e.code()} eliminarEmpleado", e)
                EmpleadoUIState.Error
            } catch (e: IOException) {
                Log.v("EmpleadoViewModel E", "Error desconocido eliminarEmpleado", e)
                EmpleadoUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as TallerAplicacion)
                val empleadoRepositorio = aplicacion.contenedor.empleadoRepositorio
                EmpleadoViewModel(empleadoRepositorio = empleadoRepositorio)
            }
        }
    }

    var rolSeleccionado by mutableStateOf<Rol?>(null)
        private set

    fun seleccionarRol(rol: Rol) {
        rolSeleccionado = rol
    }

    var provisional by mutableStateOf<Empleado?>(null)
        private set

    fun seleccionarProvisional(empleado: Empleado) {
        provisional = empleado
    }

    fun ensamblarEmpleado(): Empleado? {
        return provisional?.copy(
            cod_rol = rolSeleccionado?.cod_rol,
            rol = rolSeleccionado
        )
    }

    fun limpiarFormularioEmpleado() {
        rolSeleccionado = null
        provisional = null
    }
}