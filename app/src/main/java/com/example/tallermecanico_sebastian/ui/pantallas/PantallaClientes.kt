package com.example.tallermecanico_sebastian.ui.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.ui.viewmodel.ClienteUIState

@Composable
fun PantallaClientes(
    clienteUIState: ClienteUIState,
    onClientesObtenidos: () -> Unit,
    onClienteClick: (Cliente) -> Unit,
    modifier: Modifier = Modifier
) {
    when (clienteUIState) {
        is ClienteUIState.Cargando -> PantallaCargando(modifier = modifier.fillMaxSize())
        is ClienteUIState.Error -> PantallaError(modifier = modifier.fillMaxWidth())
        is ClienteUIState.ObtenerExito -> PantallaExitoClientes(
            lista = clienteUIState.clientes,
            modifier = modifier.fillMaxWidth(),
            onClienteClick = onClienteClick
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
) {
    Text(
        text = stringResource(R.string.cliente_titulo),
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
    ) {
        items(lista) { cliente ->
            Card(
                modifier = Modifier.clickable {
                    onClienteClick(cliente)
                }
            ) {
                Column {
                    Text(
                        text = "${stringResource(R.string.cliente_nombre)}: ${cliente.nombre} ${cliente.apellido1} ${cliente.apellido2}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${stringResource(R.string.cliente_email)}: ${cliente.email}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${stringResource(R.string.cliente_direccion)}: ${cliente.direccion}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}