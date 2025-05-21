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
import com.example.tallermecanico_sebastian.modelo.Vehiculo
import com.example.tallermecanico_sebastian.ui.pantallas.PantallaCargando
import com.example.tallermecanico_sebastian.ui.pantallas.PantallaError
import com.example.tallermecanico_sebastian.ui.theme.AzulPrincipal
import com.example.tallermecanico_sebastian.ui.viewmodel.VehiculoUIState

@Composable
fun PantallaCoches(
    vehiculoUIState: VehiculoUIState,
    onVehiculosObtenidos: () -> Unit,
    onVehiculoClick: (Vehiculo) -> Unit,
    onVehiculoEditar: (Vehiculo) -> Unit,
    onVehiculoInsertar: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (vehiculoUIState) {
        is VehiculoUIState.Cargando -> PantallaCargando(modifier = modifier.fillMaxSize())
        is VehiculoUIState.Error -> PantallaError(modifier = modifier.fillMaxWidth())
        is VehiculoUIState.ObtenerExito -> PantallaExitoVehiculos(
            lista = vehiculoUIState.vehiculos,
            modifier = modifier.fillMaxWidth(),
            onVehiculoClick = onVehiculoClick,
            onVehiculoEditar = onVehiculoEditar,
            onVehiculoInsertar = onVehiculoInsertar
        )


        is VehiculoUIState.CrearExito -> onVehiculosObtenidos()
        is VehiculoUIState.ActualizarExito -> onVehiculosObtenidos()
        is VehiculoUIState.EliminarExito -> onVehiculosObtenidos()
    }
}

@Composable
fun PantallaExitoVehiculos(
    lista: List<Vehiculo>,
    modifier: Modifier,
    onVehiculoClick: (Vehiculo) -> Unit,
    onVehiculoEditar: (Vehiculo) -> Unit,
    onVehiculoInsertar: () -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(lista) { vehiculo ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
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
                    val especificaciones = if (vehiculo.especificaciones == null) {
                        "${stringResource(R.string.vehiculo_especificaciones)}:"
                    } else {
                        "${stringResource(R.string.vehiculo_especificaciones)}: ${vehiculo.especificaciones}"
                    }

                    Text(
                        text = "${stringResource(R.string.vehiculo_marca)}: ${vehiculo.marca} ${vehiculo.modelo}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = especificaciones,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "${stringResource(R.string.vehiculo_matricula)}: ${vehiculo.matricula}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "${stringResource(R.string.vehiculo_cliente)}: ${vehiculo.cliente?.nombre} ${vehiculo.cliente?.apellido1}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = {
                                onVehiculoClick(vehiculo)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Info"
                            )
                        }
                        OutlinedButton(
                            onClick = {
                                onVehiculoEditar(vehiculo)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Create,
                                contentDescription = "Editar"
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
            onClick = { onVehiculoInsertar() },
        ) {
            Icon(Icons.Filled.Add, "Insertar")
        }
    }

}