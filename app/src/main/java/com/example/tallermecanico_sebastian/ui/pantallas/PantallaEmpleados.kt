package com.example.tallermecanico_sebastian.ui.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Empleado
import com.example.tallermecanico_sebastian.ui.viewmodel.EmpleadoUIState

@Composable
fun PantallaEmpleados(
    empleadoUIState: EmpleadoUIState,
    onEmpleadosObtenidos: () -> Unit,
    onEmpleadoClick: (Empleado) -> Unit,
    onEmpleadoInsertar: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (empleadoUIState) {
        is EmpleadoUIState.Cargando -> PantallaCargando(modifier = modifier.fillMaxSize())
        is EmpleadoUIState.Error -> PantallaError(modifier = modifier.fillMaxWidth())
        is EmpleadoUIState.ObtenerExito -> PantallaExitoEmpleados(
            lista = empleadoUIState.empleados,
            modifier = modifier.fillMaxWidth(),
            onEmpleadoClick = onEmpleadoClick,
            onEmpleadoInsertar = onEmpleadoInsertar
        )


        is EmpleadoUIState.CrearExito -> onEmpleadosObtenidos()
        is EmpleadoUIState.ActualizarExito -> onEmpleadosObtenidos()
        is EmpleadoUIState.EliminarExito -> onEmpleadosObtenidos()
        is EmpleadoUIState.ErrorMensaje -> onEmpleadosObtenidos()
    }
}

@Composable
fun PantallaExitoEmpleados(
    lista: List<Empleado>,
    modifier: Modifier,
    onEmpleadoClick: (Empleado) -> Unit,
    onEmpleadoInsertar: () -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
    ) {
        items(lista) { empleado ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable {
                        onEmpleadoClick(empleado)
                    },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            ) {
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(
                        text = "${stringResource(R.string.empleado_nombre)}: ${empleado.nombre} ${empleado.apellido1}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${stringResource(R.string.empleado_email)}: ${empleado.email}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "${stringResource(R.string.empleado_direccion)}: ${empleado.direccion}",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }

    Box(
        modifier.fillMaxSize()
    ) {
        SmallFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { onEmpleadoInsertar() },
        ) {
            Icon(Icons.Filled.Add, "Insertar")
        }
    }
}