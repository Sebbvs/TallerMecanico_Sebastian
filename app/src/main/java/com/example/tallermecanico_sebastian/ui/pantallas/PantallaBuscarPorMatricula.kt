package com.example.tallermecanico_sebastian.ui.pantallas

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Averia
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.modelo.Empleado
import com.example.tallermecanico_sebastian.modelo.Vehiculo
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.DatePickerModal
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.EstadoSwitch
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.convertMillisToDate
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaUIState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun PantallaBuscarPorMatricula(
    averia: Averia,
    onAveriasObtenidos: () -> Unit,
    onAceptar: () -> Unit,
    modifier: Modifier = Modifier
) {

    var tipo by remember { mutableStateOf(averia.tipo_averias ?: "") }
    var descripcion by remember { mutableStateOf(averia.descripcion ?: "") }
    var precio by remember { mutableStateOf(averia.precio ?: "") }
    var estado by remember { mutableStateOf(averia.estado ?: "") }
//    var cod_empleado by remember { mutableStateOf(averia.cod_empleado) }
    var fechaRecepcion by remember { mutableStateOf(averia.fecha_recepcion ?: "") }
    var fechaResolucion by remember { mutableStateOf(averia.fecha_resolucion ?: "") }
//    var cod_cliente by remember { mutableStateOf(averia.cod_cliente) }
    var observaciones by remember { mutableStateOf(averia.observaciones ?: "") }
//    var cod_vehiculo by remember { mutableStateOf(averia.cod_vehiculo) }
    var cliente by remember { mutableStateOf(averia.empleado ?: "") }
    var cliente_nom by remember { mutableStateOf(averia.cliente?.nombre ?: "") }
    var cliente_ape1 by remember { mutableStateOf(averia.cliente?.apellido1 ?: "") }
    var cliente_ape2 by remember { mutableStateOf(averia.cliente?.apellido2 ?: "") }
    var vehiculo by remember { mutableStateOf(averia.vehiculo ?: "") }
    var vehiculo_marca by remember { mutableStateOf(averia.vehiculo?.marca ?: "") }
    var vehiculo_modelo by remember { mutableStateOf(averia.vehiculo?.modelo ?: "") }
    var averia_piezas by remember { mutableStateOf(averia.averia_piezas) }
//    var tipo_averias by remember { mutableStateOf(averia.tipo_averias) }
    var empleado by remember { mutableStateOf(averia.empleado) }
    var empleado_nom by remember { mutableStateOf(averia.empleado?.nombre) }
    var empleado_ape1 by remember { mutableStateOf(averia.empleado?.apellido1) }
    var empleado_ape2 by remember { mutableStateOf(averia.empleado?.apellido2) }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf(false) }

    var fechaFormatRecepcion = fechaRecepcion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    var fechaFormatResolucion = fechaResolucion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

    var matriculaCliente by remember { mutableStateOf("") }
    var averiaEncontrada by remember { mutableStateOf<Averia?>(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        OutlinedTextField(
            value = matriculaCliente,
            onValueChange = { matriculaCliente = it },
            label = { Text(text = "Introducir matricula") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val normalizadaInput = normalizarMatricula(matriculaCliente)
//TODO ACABAR ESA LISTA
                /*                averiaEncontrada = listaAverias.find {
                                    val matriculaAveria = normalizarMatricula(it.vehiculo?.matricula ?: "")
                                    matriculaAveria == normalizadaInput
                                }*/

                if (averiaEncontrada == null) {
                    Toast.makeText(context, "Matrícula no encontrada", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(horizontal = 28.dp)
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        averiaEncontrada?.let { averia ->
//            TODO AÑADIR CAMPOS
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text(text = "Descripción") },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "$vehiculo_marca $vehiculo_modelo",
            onValueChange = { },
            label = { Text(text = "Vehiculo") },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = fechaFormatRecepcion,
            onValueChange = { fechaFormatRecepcion = it },
            label = { Text(text = "Fecha de recepción") },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = fechaFormatResolucion,
            onValueChange = { fechaFormatResolucion = it },
            label = { Text(text = "Fecha de resolución") },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = observaciones,
            onValueChange = { observaciones = it },
            label = { Text(text = "Observaciones") },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = estado,
            onValueChange = { estado = it },
            label = { Text(text = "Estado") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        /*        TextField(
                    value = "$empleado_nom $empleado_ape1 $empleado_ape2",
                    onValueChange = { },
                    label = { Text(text = "Empleado") },
                    readOnly = true,
                    colors = TextFieldDefaults.colors(
                        disabledLabelColor = Color.Black,
                        disabledTextColor = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp, end = 28.dp)
                )*/

//        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "$cliente_nom $cliente_ape1 $cliente_ape2",
            onValueChange = { },
            label = { Text(text = "Cliente") },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(onClick = onAceptar) {
                Text(stringResource(R.string.aceptar))
            }
        }
    }
}

fun normalizarMatricula(matricula: String): String {
    return matricula.uppercase().replace("\\s+".toRegex(), "")
}