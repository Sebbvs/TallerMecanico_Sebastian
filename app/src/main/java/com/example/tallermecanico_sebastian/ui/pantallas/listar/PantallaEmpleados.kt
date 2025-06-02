package com.example.tallermecanico_sebastian.ui.pantallas.listar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.PantallaCargando
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.PantallaError
import com.example.tallermecanico_sebastian.ui.theme.AzulPrincipal
import com.example.tallermecanico_sebastian.ui.viewmodel.EmpleadoUIState

@Composable
fun PantallaEmpleados(
    empleadoUIState: EmpleadoUIState,
    onEmpleadosObtenidos: () -> Unit,
    onEmpleadoClick: (Empleado) -> Unit,
    onEmpleadoEditar: (Empleado) -> Unit,
    onEmpleadoInsertar: () -> Unit,
    onEmpleadoContrasenya: (Empleado) -> Unit,
    onAceptar: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (empleadoUIState) {
        is EmpleadoUIState.Cargando -> PantallaCargando(modifier = modifier.fillMaxSize())
        is EmpleadoUIState.Error -> PantallaError(modifier = modifier.fillMaxWidth())
        is EmpleadoUIState.ObtenerExito -> PantallaExitoEmpleados(
            lista = empleadoUIState.empleados,
            modifier = modifier.fillMaxWidth(),
            onEmpleadoClick = onEmpleadoClick,
            onEmpleadoEditar = onEmpleadoEditar,
            onEmpleadoInsertar = onEmpleadoInsertar,
            onEmpleadoContrasenya = onEmpleadoContrasenya,
            onAceptar = onAceptar
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
    onEmpleadoEditar: (Empleado) -> Unit,
    onEmpleadoInsertar: () -> Unit,
    onEmpleadoContrasenya: (Empleado) -> Unit,
    onAceptar: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp), // deja espacio para el botón
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(lista) { empleado ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
//                            .weight(1f)
                        .padding(3.dp)
                        .border(
                            width = 1.dp,
                            color = AzulPrincipal,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier.padding(start = 9.dp, top = 3.dp, bottom = 3.dp)
                    ) {
                        val nombreCompleto = if (empleado.apellido2 == null) {
                            "${stringResource(R.string.texto_nombre)}: ${empleado.nombre} ${empleado.apellido1}"
                        } else {
                            "${stringResource(R.string.texto_nombre)}: ${empleado.nombre} ${empleado.apellido1} ${empleado.apellido2}"
                        }
                        val direccion = if (empleado.direccion == null) {
                            "${stringResource(R.string.texto_direccion)}: "
                        } else {
                            "${stringResource(R.string.texto_direccion)}: ${empleado.direccion}"
                        }
                        Text(
                            text = nombreCompleto,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${stringResource(R.string.texto_email)}: ${empleado.email}",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = direccion, style = MaterialTheme.typography.titleSmall
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedButton(
                                onClick = {
                                    onEmpleadoClick(empleado)
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Info, contentDescription = "Info"
                                )
                            }
                            OutlinedButton(
                                onClick = {
                                    onEmpleadoEditar(empleado)
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Create,
                                    contentDescription = "Editar"
                                )
                            }
                            OutlinedButton(
                                onClick = {
                                    onEmpleadoContrasenya(empleado)
                                },
                            ) {
                                Text(text = stringResource(R.string.cambiar_contrasenya))
                            }
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = onAceptar) {
                Text(stringResource(R.string.volver))
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
}