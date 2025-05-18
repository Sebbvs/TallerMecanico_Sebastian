package com.example.tallermecanico_sebastian.ui.pantallas

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Averia

@Composable
fun PantallaDetalleAveria(
    averia: Averia,
    onAceptar: () -> Unit,
    modifier: Modifier = Modifier
) {

    var descripcion by remember { mutableStateOf(averia.descripcion ?: "") }
    var precio by remember { mutableStateOf(averia.precio ?: "") }
    var estado by remember { mutableStateOf(averia.estado ?: "") }
//    var cod_empleado by remember { mutableStateOf(averia.cod_empleado) }
    var fechaRecepcion by remember { mutableStateOf(averia.fecha_recepcion ?: "") }
    var fechaResolucion by remember { mutableStateOf(averia.fecha_resolucion ?: "") }
//    var cod_cliente by remember { mutableStateOf(averia.cod_cliente) }
    var observaciones by remember { mutableStateOf(averia.observaciones ?: "") }
//    var cod_vehiculo by remember { mutableStateOf(averia.cod_vehiculo) }
    var empleado by remember { mutableStateOf(averia.empleado ?: "") }
    var cliente by remember { mutableStateOf(averia.cliente ?: "") }
    var vehiculo by remember { mutableStateOf(averia.vehiculo ?: "") }
    var averia_piezas by remember { mutableStateOf(averia.averia_piezas) }
//    var tipo_averias by remember { mutableStateOf(averia.tipo_averias) }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TextField(
            value = averia.cod_averia.toString(),
            label = { Text(text = "Código") },
            onValueChange = {},
            enabled = false
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text(text = "Descripción") },
            enabled = false
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text(text = "Precio") },
            enabled = false
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = estado,
            onValueChange = { estado = it },
            label = { Text(text = "Estado") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            enabled = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = fechaRecepcion,
            onValueChange = { fechaRecepcion = it },
            label = { Text(text = "Fecha de recepción") },
            enabled = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = fechaResolucion,
            onValueChange = { fechaResolucion = it },
            label = { Text(text = "Fecha de resolución") },
            enabled = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(onClick = onAceptar) {
                Text(stringResource(R.string.btnAceptar))
            }
        }
    }
}