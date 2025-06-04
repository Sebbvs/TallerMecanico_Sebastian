package com.example.tallermecanico_sebastian.ui.pantallas.insertar

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
import androidx.compose.material3.Button
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
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.convertMillisToDate
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.formatFechaParaMostrar
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAnyadirAveria(
    viewModel: AveriaViewModel,
    onInsertar: (Averia) -> Unit,
    onCancelar: () -> Unit,
    onSeleccionarCliente: () -> Unit,
    onSeleccionarEmpleado: () -> Unit,
    onSeleccionarVehiculo: () -> Unit,
    modifier: Modifier = Modifier,
) {
    //OBJETOS
    val cliente = viewModel.clienteSeleccionado
    val empleado = viewModel.empleadoSeleccionado
    val vehiculo = viewModel.vehiculoSeleccionado
    val averiaProvisional = viewModel.provisional

    var descripcion by remember { mutableStateOf(averiaProvisional?.descripcion ?: "") }
    val precio by remember { mutableStateOf(averiaProvisional?.precio ?: "0.00") }
    var fechaRecepcion by remember {
        mutableStateOf(
            averiaProvisional?.fecha_recepcion ?: LocalDate.now()
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

    val datePickerStateRecepcion = rememberDatePickerState()

    var comprobarAnteriorOIgualAHoy by remember { mutableStateOf<Long?>(null) }

    val estadoTexto = "Sin reparar"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(
            value = descripcion,
            onValueChange = {
                if (it.length <= 250) descripcion = it
            },
            label = { Text(text = stringResource(R.string.averia_descripcion) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = observaciones,
            onValueChange = {
                if (it.length <= 220) observaciones = it
            },
            label = { Text(text = stringResource(R.string.averia_observaciones)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

//        Spacer(modifier = Modifier.height(8.dp))

//        EstadoSwitch(estado = estado, onEstadoChange = { estado = it })


        Spacer(modifier = Modifier.height(8.dp))

        Box {
            OutlinedTextField(
                value = formatFechaParaMostrar(fechaRecepcion),
                onValueChange = { },
                label = { Text(stringResource(R.string.averia_fecha_recepcion) + " *") },
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
                    .padding(horizontal = 16.dp)
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
                            if (comprobarAnteriorOIgualAHoy != null && comprobarAnteriorOIgualAHoy!! < it) {
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
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold
                )
            } ?: Text(
                text = stringResource(R.string.cliente_no_seleccionado),
                modifier = Modifier,
                fontStyle = FontStyle.Italic
            )

            Button(onClick = {
//                EMPLEADO SIN ROL (NI COD ROL)
                val averia = Averia(
                    descripcion = descripcion,
                    precio = precio,
                    estado = estadoTexto,
                    fecha_recepcion = fechaRecepcion,
                    fecha_resolucion = fechaResolucion,
                    observaciones = observaciones,
                    tipo_averias = emptyList(),
                    averia_piezas = emptyList(),
                )
                viewModel.seleccionarProvisional(averia)
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
                val averia = Averia(
                    descripcion = descripcion,
                    precio = precio,
                    estado = estadoTexto,
                    fecha_recepcion = fechaRecepcion,
                    fecha_resolucion = fechaResolucion,
                    observaciones = observaciones,
                    tipo_averias = emptyList(),
                    averia_piezas = emptyList(),
                )
                viewModel.seleccionarProvisional(averia)
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
                val averia = Averia(
                    descripcion = descripcion,
                    precio = precio,
                    estado = estadoTexto,
                    fecha_recepcion = fechaRecepcion,
                    fecha_resolucion = fechaResolucion,
                    observaciones = observaciones,
                    tipo_averias = emptyList(),
                    averia_piezas = emptyList(),
                )
                viewModel.seleccionarProvisional(averia)
                onSeleccionarVehiculo()
            }) {
                Text(text = stringResource(R.string.add_vehiculo))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(onClick = onCancelar) {
                Text(stringResource(R.string.cancelar))
            }
            Spacer(modifier = Modifier.padding(end = 20.dp))
//            val context = LocalContext.current
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
                            descripcion = descripcion,
                            precio = precio,
                            estado = estadoTexto,
                            fecha_recepcion = fechaRecepcion,
                            fecha_resolucion = fechaResolucion,
                            observaciones = observaciones,
                            averia_piezas = listOf(),
                            tipo_averias = listOf(),
                        )
                    )
                    val averia = viewModel.ensamblarAveria()
                    if (averia != null) {
                        onInsertar(averia)
                        Toast.makeText(
                            context, R.string.editar_averia_mensaje_3, Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context, R.string.averia_limite_6, Toast.LENGTH_SHORT
                        ).show()
                    }
                    Log.v("AVERIAINSERTAR", "LA AVERIA QUE SE INTENTA GUARDAR ES: $averia")
                }
            }) {
                Text(stringResource(R.string.btn_guardar))
            }
        }
    }
}