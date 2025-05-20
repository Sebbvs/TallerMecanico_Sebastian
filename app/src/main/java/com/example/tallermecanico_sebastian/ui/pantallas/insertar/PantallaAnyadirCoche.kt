package com.example.tallermecanico_sebastian.ui.pantallas.insertar

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Vehiculo

@Composable
fun PantallaAnyadirCoche(
    onInsertar: (Vehiculo) -> Unit,
    onCancelar: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var marca by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var especificaciones by remember { mutableStateOf("") }
    var matricula by remember { mutableStateOf("") }
    var vin by remember { mutableStateOf("") }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(Modifier.height(16.dp))

        TextField(
            value = marca,
            onValueChange = { marca = it },
            label = { Text(text = stringResource(R.string.editarCoche_marca)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = modelo,
            onValueChange = { modelo = it },
            label = { Text(text = stringResource(R.string.editarCoche_modelo)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = especificaciones,
            onValueChange = { especificaciones = it },
            label = { Text(text = stringResource(R.string.editarCoche_especificaciones)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
                .padding(112.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = matricula,
            onValueChange = { matricula = it },
            label = { Text(text = stringResource(R.string.editarCoche_matricula)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = vin,
            onValueChange = { vin = it },
            label = { Text(text = stringResource(R.string.editarCoche_vin)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

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
            Button(
                onClick = {
                    if (marca.isEmpty() || modelo.isEmpty() || matricula.isEmpty()) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.warningFormulario),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val coche = Vehiculo(
                            marca = marca,
                            modelo = modelo,
                            especificaciones = especificaciones,
                            matricula = matricula,
                            vin = vin,
                        )
                        onInsertar(coche)
                        Toast.makeText(
                            context,
                            R.string.editarCoche_mensaje3,
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
fun PantallaAnyadirCochePreview() {
    PantallaAnyadirCoche(
        onInsertar = {},
        onCancelar = {},
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}