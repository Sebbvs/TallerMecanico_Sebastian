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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Empleado

@Composable
fun PantallaEditarEmpleados(
    empleado: Empleado,
    onCancelar: () -> Unit,
    onBorrar: (String) -> Unit,
    onGuardar: (Empleado) -> Unit,
//    onCambiar: (Empleado) -> Unit,
    modifier: Modifier = Modifier
) {

    var nombre by remember { mutableStateOf(empleado.nombre ?: "") }
    var apellido1 by remember { mutableStateOf(empleado.apellido1 ?: "") }
    var apellido2 by remember { mutableStateOf(empleado.apellido2 ?: "") }
    var email by remember { mutableStateOf(empleado.email ?: "") }
    var direccion by remember { mutableStateOf(empleado.direccion ?: "") }
    var user by remember { mutableStateOf(empleado.usuario ?: "") }
    var pass by remember { mutableStateOf(empleado.contrasenya ?: "") }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf(false) }

    var empeladoEditable by remember { mutableStateOf(empleado) }
    var mostrarPantallaCambiarContrasenya by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_nombre)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = apellido1,
            onValueChange = { apellido1 = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_apellido1)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = apellido2,
            onValueChange = { apellido2 = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_apellido2)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_direccion)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = user,
            onValueChange = { user = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_usu)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        /*        TextField(
                    value = pass,
                    onValueChange = { pass = it },
                    label = { Text(text = stringResource(R.string.editarEmpleado_pass)) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 28.dp, end = 28.dp)
                )*/

        /*        TextButton(
                    onClick = {
                        onCambiar(empleado)
                    }
                ) {
                    Text(text = stringResource(R.string.cambiarContrasenya))
                }*/

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
                    if (nombre.isEmpty()) {
                        Toast.makeText(context, R.string.empleadoObligatorio1, Toast.LENGTH_SHORT)
                            .show()
                    } else if (apellido1.isEmpty()) {
                        Toast.makeText(context, R.string.empleadoObligatorio2, Toast.LENGTH_SHORT)
                            .show()
                    } else if (user.isEmpty()) {
                        Toast.makeText(context, R.string.empleadoObligatorio3, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val empleadoEditado = empleado.copy(
                            cod_empleado = empleado.cod_empleado,
                            nombre = nombre ?: "",
                            apellido1 = apellido1 ?: "",
                            apellido2 = apellido2 ?: "",
                            email = email ?: "",
                            direccion = direccion ?: "",
                            usuario = user ?: "",
                            contrasenya = pass ?: "",
                        )
                        onGuardar(empleadoEditado)
                        Toast.makeText(
                            context,
                            R.string.editarEmpleado_mensaje1,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
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
            AlertDialogEmpleadoConfirmar(
                onDismissRequest = { abrirAlertDialog = false },
                onConfirmation = {
                    abrirAlertDialog = false
                    val empleadoEditado = empleado.copy(
                        cod_empleado = empleado.cod_empleado,
                        nombre = nombre ?: "",
                        apellido1 = apellido1 ?: "",
                        apellido2 = apellido2 ?: "",
                        email = email ?: "",
                        direccion = direccion ?: "",
                        usuario = user ?: "",
                        contrasenya = pass ?: "",
                    )
                    onBorrar(empleadoEditado.cod_empleado.toString())
                    Toast.makeText(context, R.string.editarEmpleado_mensaje2, Toast.LENGTH_SHORT)
                        .show()
                },
                dialogTitle = stringResource(R.string.dialogoAveriaTitulo),
                dialogText = stringResource(R.string.dialogoAveriaTexto),
                icon = Icons.Default.Warning
            )
        }
    }
}

@Composable
fun AlertDialogEmpleadoConfirmar(
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