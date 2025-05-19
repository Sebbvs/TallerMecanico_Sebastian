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
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Cliente

@Composable
fun PantallaEditarClientes(
    cliente: Cliente,
    onCancelar: () -> Unit,
    onBorrar: (String) -> Unit,
    onGuardar: (Cliente) -> Unit,
    modifier: Modifier = Modifier
) {

    var nombre by remember { mutableStateOf(cliente.nombre ?: "") }
    var apellido1 by remember { mutableStateOf(cliente.apellido1 ?: "") }
    var apellido2 by remember { mutableStateOf(cliente.apellido2 ?: "") }
    var email by remember { mutableStateOf(cliente.email ?: "") }
    var direccion by remember { mutableStateOf(cliente.direccion ?: "") }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = stringResource(R.string.editarCliente_nombre)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = apellido1,
            onValueChange = { apellido1 = it },
            label = { Text(text = stringResource(R.string.editarCliente_apellido1)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = apellido2,
            onValueChange = { apellido2 = it },
            label = { Text(text = stringResource(R.string.editarCliente_apellido2)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(R.string.editarCliente_email)) },
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
                    val clienteEditado = cliente.copy(
                        cod_cliente = cliente.cod_cliente,
                        nombre = nombre ?: "",
                        apellido1 = apellido1 ?: "",
                        apellido2 = apellido2 ?: "",
                        email = email ?: "",
                        direccion = direccion ?: ""
                    )
                    onGuardar(clienteEditado)
                    Toast.makeText(context, R.string.editarCliente_mensaje1, Toast.LENGTH_SHORT)
                        .show()
                }
            ) {
                Text(text = stringResource(R.string.btnGuardar))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
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
            AlertDialogClienteConfirmar(
                onDismissRequest = { abrirAlertDialog = false },
                onConfirmation = {
                    abrirAlertDialog = false
                    val clienteEditado = cliente.copy(
                        cod_cliente = cliente.cod_cliente,
                        nombre = nombre ?: "",
                        apellido1 = apellido1 ?: "",
                        apellido2 = apellido2 ?: "",
                        email = email ?: "",
                        direccion = direccion ?: ""
                    )
                    onBorrar(clienteEditado.cod_cliente.toString())
                    Toast.makeText(context, R.string.editarCliente_mensaje2, Toast.LENGTH_SHORT)
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
fun AlertDialogClienteConfirmar(
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