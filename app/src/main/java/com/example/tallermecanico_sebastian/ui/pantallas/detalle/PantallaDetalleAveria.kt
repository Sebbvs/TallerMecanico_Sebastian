package com.example.tallermecanico_sebastian.ui.pantallas.detalle

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Averia
import java.time.format.DateTimeFormatter

@Composable
fun PantallaDetalleAveria(
    averia: Averia, onAceptar: () -> Unit, modifier: Modifier = Modifier
) {
    val descripcion by remember { mutableStateOf(averia.descripcion ?: "") }
    val estado by remember { mutableStateOf(averia.estado ?: "") }
    val fechaRecepcion by remember { mutableStateOf(averia.fecha_recepcion ?: "") }
    val fechaResolucion by remember { mutableStateOf(averia.fecha_resolucion ?: "") }
    val observaciones by remember { mutableStateOf(averia.observaciones ?: "") }
    val clienteNom by remember { mutableStateOf(averia.cliente?.nombre ?: "") }
    val clienteApe1 by remember { mutableStateOf(averia.cliente?.apellido1 ?: "") }
    val clienteApe2 by remember { mutableStateOf(averia.cliente?.apellido2 ?: "") }
    val vehiculoMarca by remember { mutableStateOf(averia.vehiculo?.marca ?: "") }
    val vehiculoModelo by remember { mutableStateOf(averia.vehiculo?.modelo ?: "") }

    val fechaFormatRecepcion = fechaRecepcion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val fechaFormatResolucion = fechaResolucion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(averia.tipo_averias.orEmpty()) { tipo ->
                TextField(
                    value = tipo.nombre ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(text = stringResource(R.string.texto_tipo_averia)) },
                    colors = TextFieldDefaults.colors(
                        disabledLabelColor = Color.Black, disabledTextColor = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = descripcion,
            onValueChange = { },
            label = { Text(text = stringResource(R.string.averia_descripcion)) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black, disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "$vehiculoMarca $vehiculoModelo",
            onValueChange = { },
            label = { Text(text = stringResource(R.string.texto_vehiculo)) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black, disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = fechaFormatRecepcion,
            onValueChange = { },
            label = { Text(text = stringResource(R.string.averia_fecha_recepcion)) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black, disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = fechaFormatResolucion,
            onValueChange = { },
            label = { Text(text = stringResource(R.string.averia_fecha_recepcion)) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black, disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = observaciones,
            onValueChange = { },
            label = { Text(text = stringResource(R.string.averia_observaciones)) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black, disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = estado,
            onValueChange = { },
            label = { Text(text = stringResource(R.string.averia_estado)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black, disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "$clienteNom $clienteApe1 $clienteApe2",
            onValueChange = { },
            label = { Text(text = stringResource(R.string.texto_cliente)) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black, disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
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