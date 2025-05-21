package com.example.tallermecanico_sebastian.ui.pantallas.modificar

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import com.example.tallermecanico_sebastian.modelo.Vehiculo

@Composable
fun PantallaEditarCoches(
    vehiculo: Vehiculo,
    onCancelar: () -> Unit,
    onBorrar: (String) -> Unit,
    onGuardar: (Vehiculo) -> Unit,
    modifier: Modifier = Modifier
) {

    var marca by remember { mutableStateOf(vehiculo.marca ?: "") }
    var modelo by remember { mutableStateOf(vehiculo.modelo ?: "") }
    var especificaciones by remember { mutableStateOf(vehiculo.especificaciones ?: "") }
    var matricula by remember { mutableStateOf(vehiculo.matricula ?: "") }
    var vin by remember { mutableStateOf(vehiculo.vin ?: "") }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
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
//                .height(112.dp)
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
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(onClick = onCancelar) {
                Text(stringResource(R.string.dialogoBtnCancelar))
            }

            Button(
                onClick = {
                    if (marca.isEmpty()) {
                        Toast.makeText(context, R.string.cocheObligatorio1, Toast.LENGTH_SHORT)
                            .show()
                    } else if (modelo.isEmpty()) {
                        Toast.makeText(context, R.string.cocheObligatorio2, Toast.LENGTH_SHORT)
                            .show()
                    } else if (matricula.isEmpty()) {
                        Toast.makeText(context, R.string.cocheObligatorio3, Toast.LENGTH_SHORT)
                            .show()
                    }
                    val vehiculoEditado = vehiculo.copy(
                        cod_vehiculo = vehiculo.cod_vehiculo,
                        marca = marca ?: "",
                        modelo = modelo ?: "",
                        especificaciones = especificaciones ?: "",
                        matricula = matricula ?: "",
                        vin = vin ?: ""
                    )
                    onGuardar(vehiculoEditado)
                    Toast.makeText(context, R.string.editarCoche_mensaje1, Toast.LENGTH_SHORT)
                        .show()
                }
            ) {
                Text(text = stringResource(R.string.btnGuardar))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                abrirAlertDialog = true
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(R.string.btnBorrar))
        }

        if (abrirAlertDialog) {
            AlertDialogVehiculoConfirmar(
                onDismissRequest = { abrirAlertDialog = false },
                onConfirmation = {
                    abrirAlertDialog = false
                    val vehiculoEditado = vehiculo.copy(
                        cod_vehiculo = vehiculo.cod_vehiculo,
                        marca = marca ?: "",
                        modelo = modelo ?: "",
                        especificaciones = especificaciones ?: "",
                        matricula = matricula ?: "",
                        vin = vin ?: ""
                    )
                    onBorrar(vehiculoEditado.cod_vehiculo.toString())
                    Toast.makeText(context, R.string.editarCoche_mensaje2, Toast.LENGTH_SHORT)
                        .show()
                },
                dialogTitle = stringResource(R.string.dialogoClienteTitulo),
                dialogText = stringResource(R.string.dialogoClienteTexto),
                icon = Icons.Default.Warning
            )
        }
    }
}

@Composable
fun AlertDialogVehiculoConfirmar(
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
                Text(stringResource(R.string.dialogoBtnConfirmar))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(R.string.dialogoBtnCancelar))
            }
        }
    )
}