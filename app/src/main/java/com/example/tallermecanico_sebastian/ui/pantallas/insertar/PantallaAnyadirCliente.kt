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
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.esEmailValido

@Composable
fun PantallaAnyadirCliente(
    onInsertar: (Cliente) -> Unit,
    onCancelar: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var nombre by remember { mutableStateOf("") }
    var apellido1 by remember { mutableStateOf("") }
    var apellido2 by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(value = nombre,
            onValueChange = {
                if (it.length <= 25) nombre = it
            },
            label = { Text(text = stringResource(R.string.texto_nombre) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = apellido1,
            onValueChange = { if (it.length <= 25) apellido1 = it },
            label = { Text(text = stringResource(R.string.texto_primer_apellido) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = apellido2,
            onValueChange = { if (it.length <= 25) apellido2 = it },
            label = { Text(text = stringResource(R.string.texto_segundo_apellido)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = email,
            onValueChange = { if (it.length <= 50) email = it },
            label = { Text(text = stringResource(R.string.texto_email) + " *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = direccion,
            onValueChange = { if (it.length <= 150) direccion = it },
            label = { Text(text = stringResource(R.string.texto_direccion)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(onClick = onCancelar) {
                Text(stringResource(R.string.cancelar))
            }
            Spacer(modifier = Modifier.padding(end = 20.dp))
            Button(onClick = {
                if (nombre.isBlank()) {
                    Toast.makeText(
                        context, R.string.cliente_obligatorio_1, Toast.LENGTH_SHORT
                    ).show()
                } else if (apellido1.isBlank()) {
                    Toast.makeText(
                        context, R.string.cliente_obligatorio_2, Toast.LENGTH_SHORT
                    ).show()
                } else if (email.isBlank()) {
                    Toast.makeText(
                        context, R.string.cliente_obligatorio_3, Toast.LENGTH_SHORT
                    ).show()
                } else if (nombre.length > 25) {
                    Toast.makeText(context, R.string.cliente_limite_1, Toast.LENGTH_SHORT).show()
                } else if (apellido1.length > 25) {
                    Toast.makeText(context, R.string.cliente_limite_2, Toast.LENGTH_SHORT).show()
                } else if (apellido2.length > 25) {
                    Toast.makeText(context, R.string.cliente_limite_3, Toast.LENGTH_SHORT).show()
                } else if (email.length > 50) {
                    Toast.makeText(context, R.string.cliente_limite_4, Toast.LENGTH_SHORT).show()
                } else if (direccion.length > 100) {
                    Toast.makeText(context, R.string.cliente_limite_5, Toast.LENGTH_SHORT).show()
                } else if (!esEmailValido(email)) {
                    Toast.makeText(context, R.string.validar_email, Toast.LENGTH_SHORT).show()
                } else {
                    if (apellido2.isBlank()) {
                        apellido2 = ""
                    } else if (direccion.isBlank()) {
                        direccion = ""
                    }
                    val cliente = Cliente(
                        nombre = nombre,
                        apellido1 = apellido1,
                        apellido2 = apellido2,
                        email = email,
                        direccion = direccion,
                    )
                    onInsertar(cliente)
                    Toast.makeText(
                        context, R.string.editar_cliente_mensaje_3, Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Text(stringResource(R.string.btn_guardar))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PantallaAnyadirClientePreview() {
    PantallaAnyadirCliente(onInsertar = {},
        onCancelar = {},
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}