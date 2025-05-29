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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Vehiculo

@Composable
fun PantallaDetalleCoche(
    vehiculo: Vehiculo, onAceptar: () -> Unit, modifier: Modifier = Modifier
) {

    val marca by remember { mutableStateOf(vehiculo.marca ?: "") }
    val modelo by remember { mutableStateOf(vehiculo.modelo ?: "") }
    val especificaciones by remember { mutableStateOf(vehiculo.especificaciones ?: "") }
    val matricula by remember { mutableStateOf(vehiculo.matricula ?: "") }
    val vin by remember { mutableStateOf(vehiculo.vin ?: "") }
    val cliente_nom by remember { mutableStateOf(vehiculo.cliente?.nombre ?: "") }
    val cliente_ape1 by remember { mutableStateOf(vehiculo.cliente?.apellido1 ?: "") }
    val cliente_ape2 by remember { mutableStateOf(vehiculo.cliente?.apellido2 ?: "") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(
            value = "${marca} ${modelo}",
            onValueChange = { },
            label = { Text(text = stringResource(R.string.texto_marca)) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black, disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = especificaciones,
            onValueChange = { },
            label = { Text(text = stringResource(R.string.texto_especificaciones)) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black, disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
//                .height(112.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = matricula,
            onValueChange = { },
            label = { Text(text = stringResource(R.string.texto_matricula)) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black, disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = vin,
            onValueChange = { },
            label = { Text(text = stringResource(R.string.texto_vin)) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black, disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "$cliente_nom  $cliente_ape1  $cliente_ape2",
            onValueChange = { },
            label = { Text(text = stringResource(R.string.texto_cliente)) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black, disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onAceptar) {
                Text(stringResource(R.string.aceptar))
            }
        }
    }
}