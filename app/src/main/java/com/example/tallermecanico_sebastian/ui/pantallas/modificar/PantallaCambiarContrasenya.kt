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

    val nombre by remember { mutableStateOf(empleado.nombre ?: "") }
    val apellido1 by remember { mutableStateOf(empleado.apellido1 ?: "") }
    val apellido2 by remember { mutableStateOf(empleado.apellido2 ?: "") }
    val email by remember { mutableStateOf(empleado.email ?: "") }
    val direccion by remember { mutableStateOf(empleado.direccion ?: "") }
    val user by remember { mutableStateOf(empleado.usuario ?: "") }
    var pass by remember { mutableStateOf("") }
    var passConfirm by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(text = stringResource(R.string.cambiar_contrasenya) + stringResource(R.string.prep_de) + " $nombre $apellido1")
        TextField(
            value = pass,
            onValueChange = { if (it.length <= 100) pass = it },
            label = { Text(text = stringResource(R.string.texto_contrasenya)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = passConfirm,
            onValueChange = { if (it.length <= 100) passConfirm = it },
            label = { Text(text = stringResource(R.string.confirmar_contrasenya)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
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

            Button(onClick = {
                if (pass.isBlank() || passConfirm.isBlank()) {
                    Toast.makeText(context, R.string.warning_formulario, Toast.LENGTH_SHORT).show()
                } else if (pass != passConfirm) {
                    Toast.makeText(context, R.string.coincidir_contrasenya, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val nuevaContrasenya = empleado.copy(
                        cod_empleado = empleado.cod_empleado,
                        nombre = nombre,
                        apellido1 = apellido1,
                        apellido2 = apellido2,
                        email = email,
                        direccion = direccion,
                        usuario = user,
                        contrasenya = pass,
                    )
                    onCambiar(nuevaContrasenya)
                    Toast.makeText(
                        context, R.string.nueva_contrasenya_mensaje, Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Text(text = stringResource(R.string.dialogo_btn_confirmar))
            }
        }
    }
}