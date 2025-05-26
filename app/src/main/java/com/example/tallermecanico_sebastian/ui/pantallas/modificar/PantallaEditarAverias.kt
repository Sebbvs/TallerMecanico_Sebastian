package com.example.tallermecanico_sebastian.ui.pantallas.modificar

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Averia
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.DatePickerModal
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.EstadoSwitch
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.convertMillisToDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEditarAverias(
    averia: Averia,
    onCancelar: () -> Unit,
    onBorrar: (String) -> Unit,
    onGuardar: (Averia) -> Unit,
    modifier: Modifier = Modifier
) {

//    var tipoaveria by remember { mutableStateOf(averia.tipo_averias?.nombre ?: "") }
    var descripcion by remember { mutableStateOf(averia.descripcion ?: "") }
    var precio by remember { mutableStateOf(averia.precio ?: "") }
//    var estado by remember { mutableStateOf(averia.estado ?: "") }
//    var cod_empleado by remember { mutableStateOf(averia.cod_empleado) }
    var fechaRecepcion by remember { mutableStateOf(averia.fecha_recepcion ?: "") }
    var fechaResolucion by remember { mutableStateOf(averia.fecha_resolucion ?: "") }
//    var cod_cliente by remember { mutableStateOf(averia.cod_cliente) }
    var observaciones by remember { mutableStateOf(averia.observaciones ?: "") }
//    var cod_vehiculo by remember { mutableStateOf(averia.cod_vehiculo) }
    var empleado by remember { mutableStateOf(averia.empleado ?: "") }
    var cliente by remember { mutableStateOf(averia.cliente ?: "") }
    var vehiculo by remember { mutableStateOf(averia.vehiculo ?: "") }
//    var averia_piezas by remember { mutableStateOf(averia.averia_piezas) }
//    var tipo_averias by remember { mutableStateOf(averia.tipo_averias) }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf(false) }

    var showDatePicker1 by remember { mutableStateOf(false) }
    var showDatePicker2 by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    var comprobarRecepcion by remember { mutableStateOf<Long?>(null) }
    var comprobarResolucion by remember { mutableStateOf<Long?>(null) }

    var estado by remember { mutableStateOf(false) }
    val estadoTexto = if (estado) "Reparado" else "No reparado"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text(text = stringResource(R.string.averia_descripcion)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
//                .height(112.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        /*        TextField(
                    value = estado,
                    onValueChange = { estado = it },
                    label = { Text(text = stringResource(R.string.averia_estado)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp, end = 28.dp)
                )*/
        EstadoSwitch(
            estado = estado,
            onEstadoChange = { estado = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
//            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = fechaRecepcion,
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
                    .padding(start = 28.dp, end = 28.dp)
            )

            if (showDatePicker1) {
                DatePickerModal(
                    onDateSelected = {
                        if (it != null) {
                            fechaRecepcion = convertMillisToDate(it)
                            if (comprobarResolucion != null && comprobarResolucion!! < it) {
                                comprobarResolucion = null
                                fechaResolucion = ""
                            }
                        }
                        showDatePicker1 = false
                    },
                    onDismiss = {
                        showDatePicker1 = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
//            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = fechaResolucion,
                onValueChange = { },
                label = { Text(text = stringResource(R.string.averia_fecha_resolucion)) },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker2 = !showDatePicker2 }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = stringResource(R.string.editar_averia_seleccionar_fecha)
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(start = 28.dp, end = 28.dp)
            )

            if (showDatePicker2) {
                DatePickerModal(
                    onDateSelected = {
                        if (it != null) {
                            if (comprobarResolucion == null || it >= comprobarRecepcion!!) {
                                comprobarResolucion = it
                                fechaResolucion = convertMillisToDate(it)
                            } else {
                                Toast.makeText(
                                    context,
                                    R.string.editar_averia_mensaje_fecha,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            fechaResolucion = convertMillisToDate(it)
                        }
                        showDatePicker2 = false
                    },
                    onDismiss = {
                        showDatePicker2 = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text(text = stringResource(R.string.averia_precio)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(onClick = onCancelar) {
                Text(stringResource(R.string.cancelar))
            }
            Button(
                onClick = {
                    if (descripcion.isEmpty()) {
                        Toast.makeText(context, R.string.averia_obligatorio_1, Toast.LENGTH_SHORT)
                            .show()
                    } else if (estadoTexto.isEmpty()) {
                        Toast.makeText(context, R.string.averia_obligatorio_2, Toast.LENGTH_SHORT)
                            .show()
                    } else if (fechaRecepcion.isEmpty()) {
                        Toast.makeText(context, R.string.averia_obligatorio_3, Toast.LENGTH_SHORT)
                            .show()
//                        TODO ANYADIR CLIENTE, EMPLEADO y VEHICULO
                    } else if (fechaResolucion.isEmpty() && estado) {
                        Toast.makeText(context, R.string.averia_obligatorio_4, Toast.LENGTH_SHORT)
                            .show()
                    } else if (!fechaResolucion.isEmpty() && !estado) {
                        Toast.makeText(context, R.string.averia_obligatorio_5, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val averiaEditado = averia.copy(
                            cod_averia = averia.cod_averia,
                            descripcion = descripcion ?: "",
                            precio = precio ?: "",
                            estado = estadoTexto ?: "",
                            fecha_recepcion = fechaRecepcion ?: LocalDate.now()
                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(),
                            fecha_resolucion = fechaResolucion ?: ""
                        )
                        onGuardar(averiaEditado)
                        Toast.makeText(
                            context,
                            R.string.editar_averia_mensaje_averia_1,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            ) {
                Text(text = stringResource(R.string.btn_guardar))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                abrirAlertDialog = true
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(R.string.btn_borrar))
        }
        if (abrirAlertDialog) {
            AlertDialogAveriaConfirmar(
                onDismissRequest = { abrirAlertDialog = false },
                onConfirmation = {
                    abrirAlertDialog = false
                    val averiaEditado = averia.copy(
                        cod_averia = averia.cod_averia,
                        descripcion = descripcion ?: "",
                        precio = precio ?: "",
                        estado = estadoTexto ?: "",
                        fecha_recepcion = fechaRecepcion ?: LocalDate.now()
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(),
                        fecha_resolucion = fechaResolucion ?: "",
                        tipo_averias = emptyList()
                    )
                    onBorrar(averiaEditado.cod_averia.toString())
                    Toast.makeText(
                        context,
                        R.string.editar_averia_mensaje_averia_2,
                        Toast.LENGTH_SHORT
                    )
                        .show()
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
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Warning Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(R.string.dialogo_btn_confirmar))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(R.string.cancelar))
            }
        }
    )
}