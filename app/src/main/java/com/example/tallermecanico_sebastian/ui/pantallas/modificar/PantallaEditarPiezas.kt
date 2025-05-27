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
import com.example.tallermecanico_sebastian.modelo.Pieza

//TODO PANTALLA EDITAR PIEZAS
@Composable
fun PantallaEditarPiezas(
    pieza: Pieza,
    onCancelar: () -> Unit,
    onBorrar: (String) -> Unit,
    onGuardar: (Pieza) -> Unit,
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
            value = tipopieza,
            onValueChange = { tipopieza = it },
            label = { Text(text = stringResource(R.string.editar_pieza_tipopieza)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = descripcion,
            onValueChange = { if (it.length <= 250) descripcion = it },
            label = { Text(text = stringResource(R.string.averia_descripcion) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = cantidad,
            onValueChange = { if (it.length <= 11) cantidad = it },
            label = { Text(text = stringResource(R.string.editar_pieza_cantidad) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
            OutlinedButton(onClick = onCancelar) {
                Text(stringResource(R.string.cancelar))
            }

            Button(
                onClick = {
                    if (descripcion.isBlank()) {
                        Toast.makeText(context, R.string.pieza_obligatorio_1, Toast.LENGTH_SHORT)
                            .show()
                    } else if (cantidad.isBlank()) {
                        Toast.makeText(context, R.string.pieza_obligatorio_2, Toast.LENGTH_SHORT)
                            .show()
                    } else if (descripcion.length > 250) {
                        Toast.makeText(context, R.string.averia_limite_1, Toast.LENGTH_SHORT)
                            .show()
                    } else if (cantidad.length > 11) {
                        Toast.makeText(context, R.string.pieza_limite_2, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val piezaEditado = pieza.copy(
                            cod_pieza = pieza.cod_pieza,
                            descripcion = descripcion ?: "",
                            cantidad = cantidad.toInt() ?: 0,
                        )
                        onGuardar(piezaEditado)
                        Toast.makeText(context, R.string.editar_pieza_mensaje_1, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            ) {
                Text(text = stringResource(R.string.btn_guardar))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                abrirAlertDialog = true
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(R.string.btn_borrar))
        }

        if (abrirAlertDialog) {
            AlertDialogPiezaConfirmar(
                onDismissRequest = { abrirAlertDialog = false },
                onConfirmation = {
                    abrirAlertDialog = false
                    val piezaEditado = pieza.copy(
                        cod_pieza = pieza.cod_pieza,
                        descripcion = descripcion ?: "",
                        cantidad = cantidad.toInt() ?: 0,
                    )
                    onBorrar(piezaEditado.cod_pieza.toString())
                    Toast.makeText(context, R.string.editar_pieza_mensaje_2, Toast.LENGTH_SHORT)
                        .show()
                },
                dialogTitle = stringResource(R.string.dialogo_pieza_titulo),
                dialogText = stringResource(R.string.dialogo_pieza_texto),
                icon = Icons.Default.Warning
            )
        }
    }
}

@Composable
fun AlertDialogPiezaConfirmar(
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
                Text(stringResource(R.string.dialogo_btn_confirmar))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(R.string.cancelar))
            }
        }
    )
}