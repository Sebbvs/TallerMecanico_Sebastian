package com.example.tallermecanico_sebastian.ui.pantallas.modificar

import android.util.Log
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.millisToLocalDate
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaUIState
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//TODO Añadir seleccionables de averiapieza y tipoaveria (listas)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEditarAverias(
    viewModel: AveriaViewModel,
    averia: Averia,
    onCancelar: () -> Unit,
    onBorrar: (String) -> Unit,
    onGuardar: (Averia) -> Unit,
    onSeleccionarCliente: () -> Unit,
    onSeleccionarEmpleado: () -> Unit,
    onSeleccionarVehiculo: () -> Unit,
    modifier: Modifier = Modifier
) {
    // OBJETOS
    val cliente = viewModel.clienteSeleccionado
    val empleado = viewModel.empleadoSeleccionado
    val vehiculo = viewModel.vehiculoSeleccionado
    val averiaProvisional = viewModel.provisional

    val cod by remember { mutableIntStateOf(averiaProvisional?.cod_averia ?: 0) }
    var descripcion by remember { mutableStateOf(averiaProvisional?.descripcion ?: "") }
    val precio by remember { mutableStateOf(averiaProvisional?.precio ?: "") }
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

//    var comprobarPosteriorOIgualARecepcion by remember { mutableStateOf<Long?>(null) }
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

    var estado by remember { mutableStateOf(averiaProvisional?.estado == "Reparar") }
    val estadoTexto = if (estado) "Reparado" else "Sin reparar"

    var abrirAlertDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(
            value = descripcion,
            onValueChange = { if (it.length <= 250) descripcion = it },
            label = { Text(text = stringResource(R.string.averia_descripcion)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = observaciones,
            onValueChange = { if (it.length <= 220) observaciones = it },
            label = { Text(text = stringResource(R.string.averia_observaciones)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        EstadoSwitch(estado = estado, onEstadoChange = {
            estado = it
            if (!estado) {
                fechaResolucion = ""
                comprobarAnteriorOIgualAHoy = null
            }
        })

        Spacer(modifier = Modifier.height(8.dp))

        Box {
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
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 28.dp)
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

        Spacer(modifier = Modifier.height(8.dp))

        Box {
            OutlinedTextField(
                value = formatFechaParaMostrar(fechaResolucion),
                onValueChange = { },
                label = { Text(text = stringResource(R.string.averia_fecha_resolucion)) },
                readOnly = true,
                enabled = estado,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker2 = !showDatePicker2 }, enabled = estado) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = stringResource(R.string.editar_averia_seleccionar_fecha)
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 28.dp)
            )

            if (showDatePicker2) {
                DatePickerModal(datePickerState = datePickerStateResolucion, onDateSelected = {
                    /*if (it != null) {
                        if (comprobarPosteriorOIgualARecepcion == null) {
                            Toast.makeText(
                                context,
                                R.string.fecha_recepcion_primero,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (it < comprobarPosteriorOIgualARecepcion!!) {
                            Toast.makeText(
                                context,
                                R.string.fecha_resolucion_anterior,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            comprobarAnteriorOIgualAHoy = it
                            fechaResolucion = convertMillisToDate(it)
                            estado = true // Marca como reparado automáticamente
                        }
                    }*/

                    if (it != null) {
                        if (comprobarPosteriorOIgualARecepcion == null) {
                            Toast.makeText(
                                context,
                                R.string.fecha_resolucion_anterior,
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

                    showDatePicker2 = false
                }, onDismiss = {
                    showDatePicker2 = false
                })
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        //Boton para añadir Cliente
        cliente?.let {
            Text(
                text = "${stringResource(R.string.cliente_seleccionado)}: ${cliente.nombre} ${cliente.apellido1}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                fontWeight = FontWeight.Bold
            )
        } ?: Text(
            text = stringResource(R.string.cliente_no_seleccionado),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            fontStyle = FontStyle.Italic
        )
        Button(onClick = {
//                EMPLEADO SIN ROL (NI COD ROL)
            val averiaEditada = Averia(
                cod_averia = cod,
                descripcion = descripcion,
                precio = precio,
                estado = estadoTexto,
                fecha_recepcion = fechaRecepcion,
                fecha_resolucion = fechaResolucion,
                observaciones = observaciones,
                tipo_averias = emptyList(),
                averia_piezas = emptyList(),
            )
            viewModel.seleccionarProvisional(averiaEditada)
            onSeleccionarCliente()
        }) {
            Text(text = stringResource(R.string.add_cliente))
        }

        Spacer(modifier = Modifier.height(8.dp))
//Boton para añadir Empleado
        empleado?.let {
            Text(
                text = "${stringResource(R.string.empleado_seleccionado)}: ${empleado.nombre} ${empleado.apellido1}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                fontWeight = FontWeight.Bold
            )
        } ?: Text(
            text = stringResource(R.string.empleado_no_seleccionado),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            fontStyle = FontStyle.Italic
        )
        Button(onClick = {
//                AVERIA SIN CLIENTE
            val averiaEditada = Averia(
                cod_averia = cod,
                descripcion = descripcion,
                precio = precio,
                estado = estadoTexto,
                fecha_recepcion = fechaRecepcion,
                fecha_resolucion = fechaResolucion,
                observaciones = observaciones,
                tipo_averias = emptyList(),
                averia_piezas = emptyList(),
            )
            viewModel.seleccionarProvisional(averiaEditada)
            onSeleccionarEmpleado()
        }) {
            Text(text = stringResource(R.string.add_empleado))
        }

        Spacer(modifier = Modifier.height(8.dp))
        //Boton para añadir Vehiculo
        vehiculo?.let {
            Text(
                text = "${stringResource(R.string.vehiculo_seleccionado)}: [${vehiculo.matricula}]${vehiculo.marca} ${vehiculo.modelo}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                fontWeight = FontWeight.Bold
            )
        } ?: Text(
            text = stringResource(R.string.vehiculo_no_seleccionado),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            fontStyle = FontStyle.Italic
        )
        Button(onClick = {
//                AVERIA SIN VEHICULO
            val averiaEditada = Averia(
                cod_averia = cod,
                descripcion = descripcion,
                precio = precio,
                estado = estadoTexto,
                fecha_recepcion = fechaRecepcion,
                fecha_resolucion = fechaResolucion,
                observaciones = observaciones,
                tipo_averias = emptyList(),
                averia_piezas = emptyList(),
            )
            viewModel.seleccionarProvisional(averiaEditada)
            onSeleccionarVehiculo()
        }) {
            Text(text = stringResource(R.string.add_vehiculo))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(onClick = onCancelar) {
                Text(stringResource(R.string.cancelar))
            }
            Button(onClick = {
                if (descripcion.isBlank()) {
                    Toast.makeText(context, R.string.averia_obligatorio_1, Toast.LENGTH_SHORT)
                        .show()
                } else if (estadoTexto.isBlank()) {
                    Toast.makeText(context, R.string.averia_obligatorio_2, Toast.LENGTH_SHORT)
                        .show()
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
                } else {
                    viewModel.seleccionarProvisional(
                        Averia(
                            cod_averia = cod,
                            descripcion = descripcion,
                            precio = precio,
                            estado = estadoTexto,
                            fecha_recepcion = fechaRecepcion,
                            fecha_resolucion = fechaResolucion,
                            observaciones = observaciones,
                            averia_piezas = emptyList(),
                            tipo_averias = emptyList(),
                        )
                    )
                    val averiaEditada = viewModel.ensamblarAveria()
                    if (averiaEditada != null) {
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
        Spacer(modifier = Modifier.height(8.dp))
/*        Button(
            onClick = {
                abrirAlertDialog = true
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red, contentColor = Color.White
            )
        ) {
            Text(text = stringResource(R.string.btn_borrar))
        }*/
        if (abrirAlertDialog) {
            AlertDialogAveriaConfirmar(
                onDismissRequest = { abrirAlertDialog = false },
                onConfirmation = {
                    abrirAlertDialog = false
                    val averiaEditado = averia.copy(
                        descripcion = descripcion,
                        precio = precio,
                        estado = estadoTexto,
                        fecha_recepcion = fechaRecepcion,
                        fecha_resolucion = fechaResolucion,
                        observaciones = observaciones,
                        tipo_averias = emptyList(),
                        averia_piezas = emptyList(),
                    )
                    onBorrar(averiaEditado.cod_averia.toString())
                    Toast.makeText(
                        context, R.string.editar_averia_mensaje_averia_2, Toast.LENGTH_SHORT
                    ).show()
                },
                dialogTitle = stringResource(R.string.dialogo_averia_titulo),
                dialogText = stringResource(R.string.dialogo_averia_texto),
                icon = Icons.Default.Warning
            )
        }
    }
}

@Composable
fun AlertDialogAveriaConfirmar(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(icon = {
        Icon(icon, contentDescription = "Warning Icon")
    }, title = {
        Text(text = dialogTitle)
    }, text = {
        Text(text = dialogText)
    }, onDismissRequest = {
        onDismissRequest()
    }, confirmButton = {
        TextButton(onClick = {
            onConfirmation()
        }) {
            Text(stringResource(R.string.dialogo_btn_confirmar))
        }
    }, dismissButton = {
        TextButton(onClick = {
            onDismissRequest()
        }) {
            Text(stringResource(R.string.cancelar))
        }
    })
}