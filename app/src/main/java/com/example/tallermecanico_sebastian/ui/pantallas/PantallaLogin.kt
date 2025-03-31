package com.example.tallermecanico_sebastian.ui.pantallas

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Empleado
import com.example.tallermecanico_sebastian.ui.Pantallas
import com.example.tallermecanico_sebastian.ui.viewmodel.EmpleadoUIState

@Composable
fun PantallaLogin(
    onAutenticar: (usuario: String, contrasenya: String) -> Unit,
    empleadoUIState: EmpleadoUIState,
    navController: NavController,
    modifier: Modifier
) {
    var usuario by remember { mutableStateOf("") }
    var contrasenya by remember { mutableStateOf("") }

    //Este trozo de código ha sido con ayuda del chat
    LaunchedEffect(empleadoUIState) {
        if (empleadoUIState is EmpleadoUIState.CrearExito) {
            navController.navigate(Pantallas.Inicio.name) {
                popUpTo(Pantallas.Login.name) { inclusive = true }
            }
        } else if (empleadoUIState is EmpleadoUIState.ErrorMensaje) {
            Toast.makeText(
                navController.context,
                empleadoUIState.mensaje,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

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
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text(text = "Usuario") },
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = contrasenya,
            onValueChange = { contrasenya = it },
            label = { Text(text = "Contraseña") },
        )

        Button(
            onClick = {
                onAutenticar(usuario, contrasenya)
            }
        ) {
            Text(text = stringResource(R.string.boton_login))
        }
    }
}