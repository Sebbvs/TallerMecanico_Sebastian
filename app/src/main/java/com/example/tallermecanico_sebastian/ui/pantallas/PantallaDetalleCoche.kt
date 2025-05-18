package com.example.tallermecanico_sebastian.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Vehiculo

@Composable
fun PantallaDetalleCoche(
    vehiculo: Vehiculo,
    onAceptar: () -> Unit,
    modifier: Modifier = Modifier
) {

    var marca by remember { mutableStateOf(vehiculo.marca ?: "") }
    var modelo by remember { mutableStateOf(vehiculo.modelo ?: "") }
    var especificaciones by remember { mutableStateOf(vehiculo.especificaciones ?: "") }
    var matricula by remember { mutableStateOf(vehiculo.matricula ?: "") }
    var vin by remember { mutableStateOf(vehiculo.vin ?: "") }
    var cod_cliente by remember { mutableStateOf(vehiculo.cod_cliente ?: "") }
    var cliente by remember { mutableStateOf(vehiculo.cliente ?: "") }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
/*        TextField(
            value = vehiculo.cod_vehiculo.toString(),
            label = { Text(text = "Código") },
            onValueChange = {},
            enabled = false
        )*/

        Spacer(Modifier.height(16.dp))

        TextField(
            value = marca,
            onValueChange = { marca = it },
            label = { Text(text = "Marca") },
            enabled = false
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = modelo,
            onValueChange = { modelo = it },
            label = { Text(text = "Modelo") },
            enabled = false
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = especificaciones,
            onValueChange = { especificaciones = it },
            label = { Text(text = "Especificaciones") },
            enabled = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = matricula,
            onValueChange = { matricula = it },
            label = { Text(text = "Matricula") },
            enabled = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = vin,
            onValueChange = { vin = it },
            label = { Text(text = "Vin") },
            enabled = false
        )

        //TODO Anyadir CLIENTE o COD_CLIENTE

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