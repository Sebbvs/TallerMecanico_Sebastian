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
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.PantallaCargando
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.PantallaError
import com.example.tallermecanico_sebastian.ui.theme.AzulPrincipal
import com.example.tallermecanico_sebastian.ui.viewmodel.ClienteUIState

@Composable
fun PantallaClientes(
    clienteUIState: ClienteUIState,
    onClientesObtenidos: () -> Unit,
    onClienteClick: (Cliente) -> Unit,
    onClienteEditar: (Cliente) -> Unit,
    onClienteInsertar: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (clienteUIState) {
        is ClienteUIState.Cargando -> PantallaCargando(modifier = modifier.fillMaxSize())
        is ClienteUIState.Error -> PantallaError(modifier = modifier.fillMaxWidth())
        is ClienteUIState.ObtenerExito -> PantallaExitoClientes(
            lista = clienteUIState.clientes,
            modifier = modifier.fillMaxWidth(),
            onClienteClick = onClienteClick,
            onClienteEditar = onClienteEditar,
            onClienteInsertar = onClienteInsertar
        )


        is ClienteUIState.CrearExito -> onClientesObtenidos()
        is ClienteUIState.ActualizarExito -> onClientesObtenidos()
        is ClienteUIState.EliminarExito -> onClientesObtenidos()
    }
}

@Composable
fun PantallaExitoClientes(
    lista: List<Cliente>,
    modifier: Modifier,
    onClienteClick: (Cliente) -> Unit,
    onClienteEditar: (Cliente) -> Unit,
    onClienteInsertar: () -> Unit,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(lista) { cliente ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp)
                    .border(
                        width = 1.dp, color = AzulPrincipal, shape = RoundedCornerShape(16.dp)
                    ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(start = 9.dp, top = 3.dp, bottom = 3.dp)
                ) {
                    val nombreCompleto = if (cliente.apellido2 == null) {
                        "${stringResource(R.string.texto_nombre)}: ${cliente.nombre} ${cliente.apellido1}"
                    } else {
                        "${stringResource(R.string.texto_nombre)}: ${cliente.nombre} ${cliente.apellido1} ${cliente.apellido2}"
                    }
                    val direccion = if (cliente.direccion == null) {
                        "${stringResource(R.string.texto_direccion)}:"
                    } else {
                        "${stringResource(R.string.texto_direccion)}: ${cliente.direccion}"
                    }

                    Text(
                        text = nombreCompleto,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${stringResource(R.string.texto_email)}: ${cliente.email}",
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
                                onClienteClick(cliente)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info, contentDescription = "Info"
                            )
                        }
                        OutlinedButton(
                            onClick = {
                                onClienteEditar(cliente)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Create, contentDescription = "Editar"
                            )
                        }
                    }
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
            onClick = { onClienteInsertar() },
        ) {
            Icon(Icons.Filled.Add, "Insertar")
        }
    }

}