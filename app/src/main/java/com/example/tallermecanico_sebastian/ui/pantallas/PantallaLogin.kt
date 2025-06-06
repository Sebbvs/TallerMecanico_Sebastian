package com.example.tallermecanico_sebastian.ui.pantallas

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.ui.Pantallas
import com.example.tallermecanico_sebastian.ui.theme.Rojo
import com.example.tallermecanico_sebastian.ui.viewmodel.EmpleadoUIState

@Composable
fun PantallaLogin(
    onAutenticar: (usuario: String, contrasenya: String) -> Unit,
    empleadoUIState: EmpleadoUIState,
    onBuscar: () -> Unit,
    navController: NavController,
    modifier: Modifier
) {
    var usuario by remember { mutableStateOf("") }
    var contrasenya by remember { mutableStateOf("") }
    var contrasenyaVisible by remember { mutableStateOf(false) }

    //Este trozo de código ha sido con ayuda de chatgpt
    LaunchedEffect(empleadoUIState) {
        if (empleadoUIState is EmpleadoUIState.CrearExito) {
            navController.navigate(Pantallas.Averias.name) {
                popUpTo(Pantallas.Login.name) { inclusive = true }
            }
        } else if (empleadoUIState is EmpleadoUIState.ErrorMensaje) {
            Toast.makeText(
                navController.context, empleadoUIState.mensaje, Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.acceso),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 20.dp),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(R.drawable.lift_logo),
            contentDescription = stringResource(R.string.lift_logo),
            modifier = Modifier.size(300.dp)
        )

        Spacer(Modifier.height(30.dp))

        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text(text = stringResource(R.string.texto_usuario)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = contrasenya,
            onValueChange = { contrasenya = it },
            label = { Text(text = stringResource(R.string.texto_contrasenya)) },
            singleLine = true,
            visualTransformation = if (contrasenyaVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { contrasenyaVisible = !contrasenyaVisible }) {
                    Icon(
                        painter = if (contrasenyaVisible) painterResource(R.drawable.visibility) else painterResource(
                            R.drawable.visibility_off
                        ),
                        contentDescription = if (contrasenyaVisible) stringResource(R.string.contrasenya_oculta) else stringResource(
                            R.string.contrasenya_visible
                        )
                    )
                }
            })

        Spacer(Modifier.height(32.dp))

        Button(onClick = {
            onAutenticar(usuario, contrasenya)
        }) {
            Text(text = stringResource(R.string.boton_login), fontSize = 16.sp)
        }

        Spacer(Modifier.height(64.dp))

        OutlinedButton(
            onClick = {
                onBuscar()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent, contentColor = Rojo
            ),
        ) {
            Text(
                text = stringResource(R.string.soy_cliente),
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}