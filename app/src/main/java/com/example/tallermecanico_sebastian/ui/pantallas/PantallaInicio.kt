package com.example.tallermecanico_sebastian.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Empleado
import com.example.tallermecanico_sebastian.ui.viewmodel.EmpleadoUIState
import com.example.tallermecanico_sebastian.ui.viewmodel.EmpleadoViewModel

@Composable
fun PantallaInicio(
    modifier: Modifier,
    empleadoViewModel: EmpleadoViewModel = viewModel()
) {
    val empleado = empleadoViewModel.empleadoLogin

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "${stringResource(R.string.mensaje)} ${empleado?.nombre ?: Empleado}!",
            fontWeight = FontWeight.Bold
        )

        Image(
            painter = painterResource(id = R.drawable.lift_logo),
            contentDescription = stringResource(id = R.string.lift_logo),
            modifier = Modifier.size(300.dp),
            alpha = 0.5F
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            ElevatedButton(onClick = {
//                onClick()
            }) {
                Text(text = stringResource(R.string.btnInicio1))
            }
            ElevatedButton(onClick = {
//                onClick()
            }) {
                Text(text = stringResource(R.string.btnInicio2))
            }
//            TODO tiposaveria | averiatipoaveria | averiapieza | tipospieza |
        }
    }
}

@Composable
fun PantallaCargando(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.cargando),
        contentDescription = stringResource(R.string.cargando)
    )
}

@Composable
fun PantallaError(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.error),
        contentDescription = stringResource(R.string.error_de_conexion)
    )
}