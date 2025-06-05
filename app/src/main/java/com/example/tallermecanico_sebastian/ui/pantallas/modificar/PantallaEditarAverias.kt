package com.example.tallermecanico_sebastian.ui.pantallas.modificar

import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Averia
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.DatePickerModal
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.EstadoSwitch
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.convertMillisToDate
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.formatFechaParaMostrar
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.formatearDecimalValidado
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.millisToLocalDate
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEditarAverias(
    viewModel: AveriaViewModel,
    onCancelar: () -> Unit,
    onGuardar: (Averia) -> Unit,
    onSeleccionarCliente: () -> Unit,
    onSeleccionarEmpleado: () -> Unit,
    onSeleccionarVehiculo: () -> Unit,
    onSeleccionarTipoaverias: () -> Unit,
    onSeleccionarAveriapiezas: () -> Unit,
    modifier: Modifier = Modifier
) {
    // OBJETOS
    val cliente = viewModel.clienteSeleccionado
    val empleado = viewModel.empleadoSeleccionado
    val vehiculo = viewModel.vehiculoSeleccionado
    val tipoaveria = viewModel.tipoaveriaSeleccionado.toList()
    val averiapieza = viewModel.averiapiezaSeleccionado.toList()
    val averiaProvisional = viewModel.provisional

    Log.v("AVERIA PARA EDITAR DATOS CARGADOS", "$averiaProvisional")

    val cod by remember { mutableIntStateOf(averiaProvisional?.cod_averia ?: 0) }
    var descripcion by remember { mutableStateOf(averiaProvisional?.descripcion ?: "") }
    var precio by remember { mutableStateOf(averiaProvisional?.precio ?: "0.00") }
    var fechaRecepcion by remember {
        mutableStateOf(
            averiaProvisional?.fecha_recepcion
                ?: LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        )
    }
    var fechaResolucion by remember {
        mutableStateOf(
            averiaProvisional?.fecha_resolucion ?: ""
        )
    }
    var observaciones by remember { mutableStateOf(averiaProvisional?.observaciones ?: "") }
    val context = LocalContext.current
    var showDatePicker1 by remember { mutableStateOf(false) }
    var showDatePicker2 by remember { mutableStateOf(false) }

    var comprobarAnteriorOIgualAHoy by remember { mutableStateOf<Long?>(null) }
    var comprobarPosteriorOIgualARecepcion by remember {
        mutableStateOf(
            averiaProvisional?.fecha_recepcion?.let {
                LocalDate.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    .toEpochDay() * 24 * 60 * 60 * 1000
            }
        )
    }

    val datePickerStateRecepcion =
        rememberDatePickerState(initialSelectedDateMillis = comprobarPosteriorOIgualARecepcion)
    val datePickerStateResolucion = rememberDatePickerState()

    var estado by remember { mutableStateOf(averiaProvisional?.estado == "Reparado") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        TextField(
            value = descripcion,
            onValueChange = { if (it.length <= 250) descripcion = it },
            label = { Text(text = stringResource(R.string.averia_descripcion)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Boton para añadir Tipoaveria
            tipoaveria.let {
                Text(
                    text = "${tipoaveria.size} ${stringResource(R.string.tipoaveria_seleccionado)}", //${vehiculo.marca} ${vehiculo.modelo}",
                    fontWeight = FontWeight.Bold
                )
            } ?: Text(
                text = stringResource(R.string.tipoaveria_no_seleccionado),
                fontStyle = FontStyle.Italic
            )

            Button(onClick = {
//                AVERIA SIN Tipoaveria
                val averiaEditada = Averia(
                    cod_averia = cod,
                    descripcion = descripcion,
                    precio = precio,
                    estado = if (estado) "Reparado" else "Sin reparar",
                    fecha_recepcion = fechaRecepcion,
                    fecha_resolucion = fechaResolucion,
                    observaciones = observaciones,
                )
                viewModel.seleccionarProvisional(averiaEditada)
                onSeleccionarTipoaverias()
            }) {
                Text(text = stringResource(R.string.add_tipo))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = observaciones,
            onValueChange = { if (it.length <= 220) observaciones = it },
            label = { Text(text = stringResource(R.string.averia_observaciones)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        EstadoSwitch(
            estado = estado,
            onEstadoChange = { nuevoEstado ->
                estado = nuevoEstado
                viewModel.provisional = viewModel.provisional?.copy(
                    estado = if (nuevoEstado) "Reparado" else "Sin reparar"
                )
//            estado = it
                if (!nuevoEstado) {
                    fechaResolucion = ""
                    comprobarAnteriorOIgualAHoy = null
                }
            })

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                OutlinedTextField(
                    value = formatFechaParaMostrar(fechaRecepcion),
                    onValueChange = { },
                    label = { Text(text = stringResource(R.string.averia_fecha_recepcion)) },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker1 = !showDatePicker1 }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = stringResource(R.string.editar_averia_seleccionar_fecha)
                            )
                        }
                    },
                    modifier = Modifier
                        .height(64.dp)
                )

                if (showDatePicker1) {
                    DatePickerModal(datePickerState = datePickerStateRecepcion, onDateSelected = {
                        if (it != null) {
                            val hoy = LocalDate.now().toEpochDay() * 24 * 60 * 60 * 1000
                            if (it > hoy) {
                                Toast.makeText(context, R.string.fecha_futura, Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                fechaRecepcion = convertMillisToDate(it)
                                comprobarPosteriorOIgualARecepcion = it
                                if (comprobarAnteriorOIgualAHoy != null && comprobarAnteriorOIgualAHoy!! <= it) {
                                    comprobarAnteriorOIgualAHoy = null
                                    fechaResolucion = ""
                                }
                            }
                        }
                        showDatePicker1 = false
                    }, onDismiss = {
                        showDatePicker1 = false
                    })
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(modifier = Modifier.weight(1f)) {
                OutlinedTextField(
                    value = formatFechaParaMostrar(fechaResolucion),
                    onValueChange = { },
                    label = { Text(text = stringResource(R.string.averia_fecha_resolucion)) },
                    readOnly = true,
                    enabled = estado,
                    trailingIcon = {
                        IconButton(
                            onClick = { showDatePicker2 = !showDatePicker2 },
                            enabled = estado
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = stringResource(R.string.editar_averia_seleccionar_fecha)
                            )
                        }
                    },
                    modifier = Modifier
                        .height(64.dp)
                )

                if (showDatePicker2) {
                    DatePickerModal(datePickerState = datePickerStateResolucion, onDateSelected = {

                        if (it != null) {
                            if (comprobarPosteriorOIgualARecepcion == null) {
                                Toast.makeText(
                                    context,
                                    R.string.fecha_resolucion_anterior,
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val hoy = LocalDate.now().toEpochDay() * 24 * 60 * 60 * 1000
                                if (it > hoy) {
                                    fechaResolucion = LocalDate.now()
                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                                        .toString()
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.fecha_futura_2),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    val fechaRecepcionDate =
                                        millisToLocalDate(comprobarPosteriorOIgualARecepcion!!)
                                    val fechaResolucionDate = millisToLocalDate(it)
                                    if (fechaResolucionDate.isBefore(fechaRecepcionDate)) {
                                        Toast.makeText(
                                            context,
                                            R.string.fecha_recepcion_primero,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        comprobarAnteriorOIgualAHoy = it
                                        fechaResolucion = convertMillisToDate(it)
                                        estado = true // Marca como reparado automáticamente
                                    }
                                }
                            }
                        }

                        showDatePicker2 = false
                    }, onDismiss = {
                        showDatePicker2 = false
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Boton para añadir Cliente
            cliente?.let {
                Text(
                    text = "${stringResource(R.string.cliente_seleccionado)}: ${cliente.nombre} ${cliente.apellido1}",
                    fontWeight = FontWeight.Bold
                )
            } ?: Text(
                text = stringResource(R.string.cliente_no_seleccionado),
                fontStyle = FontStyle.Italic
            )

            Button(onClick = {
//                EMPLEADO SIN ROL (NI COD ROL)
                val averiaEditada = Averia(
                    cod_averia = cod,
                    descripcion = descripcion,
                    precio = precio,
                    estado = if (estado) "Reparado" else "Sin reparar",
                    fecha_recepcion = fechaRecepcion,
                    fecha_resolucion = fechaResolucion,
                    observaciones = observaciones,
                )
                viewModel.seleccionarProvisional(averiaEditada)
                onSeleccionarCliente()
            }) {
                Text(text = stringResource(R.string.add_cliente))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Boton para añadir Empleado
            empleado?.let {
                Text(
                    text = "${stringResource(R.string.empleado_seleccionado)}: ${empleado.nombre} ${empleado.apellido1}",
                    fontWeight = FontWeight.Bold
                )
            } ?: Text(
                text = stringResource(R.string.empleado_no_seleccionado),
                fontStyle = FontStyle.Italic
            )

            Button(onClick = {
//                AVERIA SIN CLIENTE
                val averiaEditada = Averia(
                    cod_averia = cod,
                    descripcion = descripcion,
                    precio = precio,
                    estado = if (estado) "Reparado" else "Sin reparar",
                    fecha_recepcion = fechaRecepcion,
                    fecha_resolucion = fechaResolucion,
                    observaciones = observaciones,
                )
                viewModel.seleccionarProvisional(averiaEditada)
                onSeleccionarEmpleado()
            }) {
                Text(text = stringResource(R.string.add_empleado))
            }
        }


        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Boton para añadir Vehiculo
            vehiculo?.let {
                Text(
                    text = "${stringResource(R.string.vehiculo_seleccionado)}: ${vehiculo.matricula}", //${vehiculo.marca} ${vehiculo.modelo}",
                    fontWeight = FontWeight.Bold
                )
            } ?: Text(
                text = stringResource(R.string.vehiculo_no_seleccionado),
                fontStyle = FontStyle.Italic
            )

            Button(onClick = {
//                AVERIA SIN VEHICULO
                val averiaEditada = Averia(
                    cod_averia = cod,
                    descripcion = descripcion,
                    precio = precio,
                    estado = if (estado) "Reparado" else "Sin reparar",
                    fecha_recepcion = fechaRecepcion,
                    fecha_resolucion = fechaResolucion,
                    observaciones = observaciones,
                )
                viewModel.seleccionarProvisional(averiaEditada)
                onSeleccionarVehiculo()
            }) {
                Text(text = stringResource(R.string.add_vehiculo))
            }
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Boton para añadir Averiapieza
            averiapieza.let { piezas ->
                Text(
                    text = "${piezas.size} ${stringResource(R.string.averiapieza_seleccionado)}", //${vehiculo.marca} ${vehiculo.modelo}",
                    fontWeight = FontWeight.Bold
                )
            } ?: Text(
                text = stringResource(R.string.averiapieza_no_seleccionado),
                fontStyle = FontStyle.Italic
            )

            Button(onClick = {
//                AVERIA SIN Averiapieza
                val averiaEditada = Averia(
                    cod_averia = cod,
                    descripcion = descripcion,
                    precio = precio,
                    estado = if (estado) "Reparado" else "Sin reparar",
                    fecha_recepcion = fechaRecepcion,
                    fecha_resolucion = fechaResolucion,
                    observaciones = observaciones,
                )
                viewModel.seleccionarProvisional(averiaEditada)
                onSeleccionarAveriapiezas()
            }) {
                Text(text = stringResource(R.string.add_averiapieza))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = precio,
            onValueChange = {
                val valido = formatearDecimalValidado(it, context)
                if (valido != null) precio = it
            },
            label = { Text(text = stringResource(R.string.averia_precio) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxSize().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(onClick = onCancelar) {
                Text(stringResource(R.string.cancelar))
            }
            Button(onClick = {
                if (descripcion.isBlank()) {
                    Toast.makeText(context, R.string.averia_obligatorio_1, Toast.LENGTH_SHORT)
                        .show()
                    /*} else if (estado) {
                        Toast.makeText(context, R.string.averia_obligatorio_2, Toast.LENGTH_SHORT)
                            .show()*/
                } else if (fechaRecepcion.isBlank()) {
                    Toast.makeText(context, R.string.averia_obligatorio_3, Toast.LENGTH_SHORT)
                        .show()
                } else if (fechaResolucion.isBlank() && estado) {
                    Toast.makeText(context, R.string.averia_obligatorio_4, Toast.LENGTH_SHORT)
                        .show()
                } else if (fechaResolucion.isNotBlank() && !estado) {
                    Toast.makeText(context, R.string.averia_obligatorio_5, Toast.LENGTH_SHORT)
                        .show()
                } else if (descripcion.length > 250) {
                    Toast.makeText(context, R.string.averia_limite_1, Toast.LENGTH_SHORT).show()
                } else if (precio.length > 8) {
                    Toast.makeText(context, R.string.averia_limite_2, Toast.LENGTH_SHORT).show()
                } else if (cliente == null) {
                    Toast.makeText(context, R.string.averia_limite_3, Toast.LENGTH_SHORT).show()
                } else if (empleado == null) {
                    Toast.makeText(context, R.string.averia_limite_4, Toast.LENGTH_SHORT).show()
                } else if (vehiculo == null) {
                    Toast.makeText(context, R.string.averia_limite_5, Toast.LENGTH_SHORT).show()
                    /*                } else if (tipoaveria.size == 0) {
                                        Toast.makeText(context, R.string.averia_limite_7, Toast.LENGTH_SHORT).show()
                                    } else if (averiapieza.size == 0) {
                                        Toast.makeText(context, R.string.averia_limite_8, Toast.LENGTH_SHORT).show()
                                    } else if (precio.isBlank()) {
                                        Toast.makeText(context, R.string.averia_obligatorio_6, Toast.LENGTH_SHORT)
                                            .show()*/
                } else {
                    val convertirPrecio = precio.replace(",", ".").toBigDecimalOrNull()
                    viewModel.seleccionarProvisional(
                        Averia(
                            cod_averia = cod,
                            descripcion = descripcion,
                            precio = convertirPrecio.toString(),
                            estado = if (estado) "Reparado" else "Sin reparar",
                            fecha_recepcion = fechaRecepcion,
                            fecha_resolucion = fechaResolucion,
                            observaciones = observaciones,
//                            empleado = null,
//                            cliente = null,
//                            vehiculo = null,
//                            averia_piezas = emptyList(),
//                            tipo_averias = emptyList()
                        )
                    )
                    val averiaEditada = viewModel.ensamblarAveria()
                    if (averiaEditada != null) {
                        Log.v(
                            "EDITAR AVERIA",
                            "Averia que se intenta guardar es $averiaEditada"
                        )
                        onGuardar(averiaEditada)
                        Toast.makeText(
                            context, R.string.editar_averia_mensaje_averia_1, Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context, R.string.averia_limite_6, Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }) {
                Text(text = stringResource(R.string.btn_guardar))
            }
        }
    }
}