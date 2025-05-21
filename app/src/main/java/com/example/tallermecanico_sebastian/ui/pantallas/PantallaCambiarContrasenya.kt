package com.example.tallermecanico_sebastian.ui.pantallas

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Empleado

@Composable
fun PantallaCambiarContrasenya(
    empleado: Empleado,
    onCancelar: () -> Unit,
    onCambiar: (Empleado) -> Unit,
    modifier: Modifier = Modifier,
) {

    var nombre by remember { mutableStateOf(empleado.nombre ?: "") }
    var apellido1 by remember { mutableStateOf(empleado.apellido1 ?: "") }
    var apellido2 by remember { mutableStateOf(empleado.apellido2 ?: "") }
    var email by remember { mutableStateOf(empleado.email ?: "") }
    var direccion by remember { mutableStateOf(empleado.direccion ?: "") }
    var user by remember { mutableStateOf(empleado.usuario ?: "") }
    var pass by remember { mutableStateOf("") }
    var passConfirm by remember { mutableStateOf("") }
    var context = LocalContext.current
    var abrirAlertDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(text = stringResource(R.string.cambiarContrasenya2) + " $nombre $apellido1")
        TextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_pass)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = passConfirm,
            onValueChange = { passConfirm = it },
            label = { Text(text = stringResource(R.string.editarEmpleado_pass2)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        )

        Spacer(Modifier.height(16.dp))

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
                    if (pass.isEmpty() || passConfirm.isEmpty()) {
                        Toast.makeText(context, R.string.warningFormulario, Toast.LENGTH_SHORT)
                            .show()
                    } else if (!pass.equals(passConfirm)) {
                        Toast.makeText(context, R.string.matchPassword, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val nuevaContrasenya = empleado.copy(
                            cod_empleado = empleado.cod_empleado,
                            nombre = nombre ?: "",
                            apellido1 = apellido1 ?: "",
                            apellido2 = apellido2 ?: "",
                            email = email ?: "",
                            direccion = direccion ?: "",
                            usuario = user ?: "",
                            contrasenya = pass ?: "",
                        )
                        onCambiar(nuevaContrasenya)
                        Toast.makeText(
                            context,
                            R.string.nuevaContrasenya_mensaje,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            ) {
                Text(text = stringResource(R.string.dialogoBtnConfirmar))
            }
        }
    }
}