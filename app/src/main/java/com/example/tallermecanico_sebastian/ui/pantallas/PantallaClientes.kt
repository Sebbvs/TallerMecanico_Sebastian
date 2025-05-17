package com.example.tallermecanico_sebastian.ui.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.example.tallermecanico_sebastian.modelo.Cliente
import com.example.tallermecanico_sebastian.ui.viewmodel.ClienteUIState

@Composable
fun PantallaClientes(
    clienteUIState: ClienteUIState,
    onClientesObtenidos: () -> Unit,
    onClienteClick: (Cliente) -> Unit,
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
    onClienteInsertar: () -> Unit,
) {
    /*    Text(
            text = stringResource(R.string.cliente_titulo),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )*/

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        items(lista) { cliente ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
                    .clickable {
                        onClienteClick(cliente)
                    },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(
                        text = "${stringResource(R.string.cliente_nombre)}: ${cliente.nombre} ${cliente.apellido1} ${cliente.apellido2}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${stringResource(R.string.cliente_email)}: ${cliente.email}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "${stringResource(R.string.cliente_direccion)}: ${cliente.direccion}",
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
            onClick = { onClienteInsertar() },
        ) {
            Icon(Icons.Filled.Add, "Insertar")
        }
    }

}