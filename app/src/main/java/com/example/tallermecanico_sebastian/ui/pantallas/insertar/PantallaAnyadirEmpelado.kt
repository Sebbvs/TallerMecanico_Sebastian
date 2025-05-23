package com.example.tallermecanico_sebastian.ui.pantallas.insertar

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.tallermecanico_sebastian.modelo.Empleado

@Composable
fun PantallaAnyadirEmpleado(
    onInsertar: (Empleado) -> Unit,
    onCancelar: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var nombre by remember { mutableStateOf("") }
    var apellido1 by remember { mutableStateOf("") }
    var apellido2 by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }
    var contrasenya by remember { mutableStateOf("") }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Spacer(Modifier.height(16.dp))

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_nombre) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = apellido1,
            onValueChange = { apellido1 = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_apellido1) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = apellido2,
            onValueChange = { apellido2 = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_apellido2)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_direccion)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_usu) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = contrasenya,
            onValueChange = { contrasenya = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_pass) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )

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
                    if (nombre.isEmpty()) {
                        Toast.makeText(
                            context,
                            R.string.empleadoObligatorio1,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (apellido1.isEmpty()) {
                        Toast.makeText(
                            context,
                            R.string.empleadoObligatorio2,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (usuario.isEmpty()) {
                        Toast.makeText(
                            context,
                            R.string.empleadoObligatorio3,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (contrasenya.isEmpty()) {
                        Toast.makeText(
                            context,
                            R.string.empleadoObligatorio4,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val empleado = Empleado(
                            nombre = nombre,
                            apellido1 = apellido1,
                            apellido2 = apellido2,
                            email = email,
                            direccion = direccion,
                            usuario = usuario,
                            contrasenya = contrasenya,
                        )
                        onInsertar(empleado)
                        Toast.makeText(
                            context,
                            R.string.editarEmpleado_mensaje3,
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
fun PantallaAnyadirEmpleadoPreview() {
    PantallaAnyadirAveria(
        onInsertar = {},
        onCancelar = {},
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}