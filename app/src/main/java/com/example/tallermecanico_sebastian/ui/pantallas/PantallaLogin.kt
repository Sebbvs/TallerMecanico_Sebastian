package com.example.tallermecanico_sebastian.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R

@Composable
fun PantallaLogin(
    modifier: Modifier
    //usuario: Usuario
    //onAutenticar: (Usuario) -> Unit
) {
    var usuario by remember {mutableStateOf("")}
    var contrasenya by remember {mutableStateOf("")}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.acceso),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(16.dp))

        TextField(
            value = usuario.usuario,
            onValueChange = { usuario = it },
            label = { Text(text = "Usuario") },
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = usuario.contraseña,
            onValueChange = { contrasenya = it },
            label = { Text(text = "Contraseña") },
        )

        Button(
            onClick = {
                val profesorEditado = profesor.copy(
                    id = profesor.id,
                    nombre = nombre ?: "",
                    apellidos = apellidos ?: "",
                    curso = curso ?: "",
                )
                onAutenticar(profesorEditado)
            }
        ) {
            Text(text = stringResource(R.string.guardar_profesor))
        }
    }
}