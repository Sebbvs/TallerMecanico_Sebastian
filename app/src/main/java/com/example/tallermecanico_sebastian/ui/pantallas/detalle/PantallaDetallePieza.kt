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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Pieza

@Composable
fun PantallaDetallePieza(
    pieza: Pieza,
    onAceptar: () -> Unit,
    modifier: Modifier = Modifier
) {

    var descripcion by remember { mutableStateOf(pieza.descripcion ?: "") }
    var cantidad by remember { mutableStateOf(pieza.cantidad.toString() ?: "") }
    var tipopieza by remember { mutableStateOf(pieza.tipo_pieza?.nombre ?: "") }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf(false) }

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
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text(text = stringResource(R.string.editar_pieza_cantidad)) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = tipopieza,
            onValueChange = { tipopieza = it },
            label = { Text(text = stringResource(R.string.editar_pieza_tipopieza)) },
            readOnly = true,
            colors = TextFieldDefaults.colors(
                disabledLabelColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
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