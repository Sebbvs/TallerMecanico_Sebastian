package com.example.tallermecanico_sebastian.ui.pantallas.insertar

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Averia
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.modelo.Empleado
import com.example.tallermecanico_sebastian.modelo.Vehiculo
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.DatePickerModal
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.EstadoSwitch
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.convertMillisToDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAnyadirAveria(
    onInsertar: (Averia) -> Unit,
    onCancelar: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var descripcion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
//    var estado by remember { mutableStateOf("") }
    var fechaRecepcion by remember {
        mutableStateOf(
            LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()
        )
    }
    var fechaResolucion by remember { mutableStateOf("") }
    var observaciones by remember { mutableStateOf("") }
    var empleado by remember { mutableStateOf("") }
    var cliente by remember { mutableStateOf("") }
    var vehiculo by remember { mutableStateOf("") }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf("") }

    var showDatePicker1 by remember { mutableStateOf(false) }
    var showDatePicker2 by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    var comprobarRecepcion by remember { mutableStateOf<Long?>(null) }
    var comprobarResolucion by remember { mutableStateOf<Long?>(null) }

    var estado by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text(text = stringResource(R.string.editarAveria_descripcion) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
//                .height(112.dp)
        )

        Spacer(Modifier.height(16.dp))

        /*        TextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = { Text(text = stringResource(R.string.editarAveria_precio)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp, end = 28.dp)
                )*/

        Spacer(Modifier.height(16.dp))

        /*        TextField(
                    value = estado,
                    onValueChange = { estado = it },
                    label = { Text(text = stringResource(R.string.editarAveria_estado)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp, end = 28.dp)
                )*/
        EstadoSwitch(
            estado = estado,
            onEstadoChange = { estado = it }
        )


        Spacer(modifier = Modifier.height(16.dp))

        Box(
//            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = fechaRecepcion,
                onValueChange = { },
                label = { Text(stringResource(R.string.editarAveria_fechaRecepcion) + " +") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker1 = !showDatePicker1 }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = stringResource(R.string.editarAveria_seleccionarFecha)
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

        Spacer(modifier = Modifier.height(16.dp))

        Box(
//            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = fechaResolucion,
                onValueChange = { },
                label = { Text(stringResource(R.string.editarAveria_fechaResolucion)) },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker2 = !showDatePicker2 }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = stringResource(R.string.editarAveria_seleccionarFecha)
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
                                    R.string.editarAveria_mensajeFecha,
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

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(onClick = onCancelar) {
                Text(stringResource(R.string.dialogoBtnCancelar))
            }
            Spacer(modifier = Modifier.padding(end = 20.dp))
            val context = LocalContext.current
            val estadoTexto = if (estado) "Reparado" else "Sin reparar"
            Button(
                onClick = {
                    /*if (descripcion.isEmpty() || estadoTexto.isEmpty() || fechaRecepcion.isEmpty()) {
                        Toast.makeText(
                            context,
                            R.string.warningFormulario,
                            Toast.LENGTH_SHORT
                        ).show()
                    }*/
                    if (descripcion.isEmpty()) {
                        Toast.makeText(context, R.string.averiaObligatorio1, Toast.LENGTH_SHORT)
                            .show()
                    } else if (estadoTexto.isEmpty()) {
                        Toast.makeText(context, R.string.averiaObligatorio2, Toast.LENGTH_SHORT)
                            .show()
                    } else if (fechaRecepcion.isEmpty()) {
                        Toast.makeText(context, R.string.averiaObligatorio3, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val averia = Averia(
                            descripcion = descripcion,
                            precio = precio,
                            estado = estadoTexto,
                            fecha_recepcion = fechaRecepcion,
                            fecha_resolucion = fechaResolucion,
                            averia_piezas = emptyList(),
                            cliente = Cliente(),
                            empleado = Empleado(),
                            vehiculo = Vehiculo(),
                            tipo_averias = emptyList(),
                        )
                        onInsertar(averia)
                        Toast.makeText(
                            context,
                            R.string.editarAveria_mensaje3,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                Text(stringResource(R.string.btnGuardar))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaAnyadirAveriaPreview() {
    PantallaAnyadirAveria(
        onInsertar = {},
        onCancelar = {},
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}