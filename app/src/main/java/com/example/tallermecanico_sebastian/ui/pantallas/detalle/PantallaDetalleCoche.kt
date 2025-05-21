package com.example.tallermecanico_sebastian.ui.pantallas.detalle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    var cliente_nom by remember { mutableStateOf(vehiculo.cliente?.nombre ?: "") }
    var cliente_ape1 by remember { mutableStateOf(vehiculo.cliente?.apellido1 ?: "") }
    var cliente_ape2 by remember { mutableStateOf(vehiculo.cliente?.apellido2 ?: "") }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(
            value = "${marca} ${modelo}",
            onValueChange = { marca = it },
            label = { Text(text = "Marca") },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        /*        Spacer(Modifier.height(16.dp))

                TextField(
                    value = modelo,
                    onValueChange = { modelo = it },
                    label = { Text(text = "Modelo") },
                    readOnly = true,
                    colors = TextFieldDefaults.colors(
                        disabledLabelColor = Color.Black,
                        disabledTextColor = Color.Black
                    )
                )*/

        Spacer(Modifier.height(16.dp))

        TextField(
            value = especificaciones,
            onValueChange = { especificaciones = it },
            label = { Text(text = "Especificaciones") },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
//                .height(112.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = matricula,
            onValueChange = { matricula = it },
            label = { Text(text = "Matricula") },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = vin,
            onValueChange = { vin = it },
            label = { Text(text = "Vin") },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = "$cliente_nom  $cliente_ape1  $cliente_ape2",
            onValueChange = {},
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

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onAceptar) {
                Text(stringResource(R.string.btnAceptar))
            }
        }
    }
}