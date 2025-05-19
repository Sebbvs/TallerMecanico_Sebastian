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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Empleado

@Composable
fun PantallaDetalleEmpleado(
    empleado: Empleado,
    onAceptar: () -> Unit,
    modifier: Modifier = Modifier
) {

    var nombre by remember { mutableStateOf(empleado.nombre ?: "") }
    var apellido1 by remember { mutableStateOf(empleado.apellido1 ?: "") }
    var apellido2 by remember { mutableStateOf(empleado.apellido2 ?: "") }
    var email by remember { mutableStateOf(empleado.email ?: "") }
    var direccion by remember { mutableStateOf(empleado.direccion ?: "") }
    var user by remember { mutableStateOf(empleado.usuario ?: "") }
    var pass by remember { mutableStateOf(empleado.contrasenya ?: "") }
    var rol by remember { mutableStateOf(empleado.rol?.nombre ?: "") }
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
            label = { Text(text = "Nombre") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = "${apellido1} ${apellido2}",
            onValueChange = { apellido1 = it },
            label = { Text(text = "Apellidos") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        /*        Spacer(Modifier.height(16.dp))

                TextField(
                    value = apellido2,
                    onValueChange = { apellido2 = it },
                    label = { Text(text = "Segundo apellido") },
                    readOnly = true,
                )*/

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)

        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text(text = "Dirección") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = user,
            onValueChange = { user = it },
            label = { Text(text = "Usuario") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = rol,
            onValueChange = {},
            label = { Text(text = "Rol") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

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